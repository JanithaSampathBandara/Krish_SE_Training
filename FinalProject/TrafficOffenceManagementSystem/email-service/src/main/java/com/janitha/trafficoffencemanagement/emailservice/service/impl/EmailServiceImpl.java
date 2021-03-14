package com.janitha.trafficoffencemanagement.emailservice.service.impl;
// import com.janitha.trafficoffencemanagement.emailservice.config.Token;
import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.emailservice.model.Token;
import com.janitha.trafficoffencemanagement.emailservice.service.EmailService;
import com.janitha.trafficoffencemanagement.model.driverservice.Driver;
import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;
import com.janitha.trafficoffencemanagement.model.offenceservice.Offence;
import com.janitha.trafficoffencemanagement.model.officerservice.Officer;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;


@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

  //  @LoadBalanced
    @Bean(name="noLoad")
    RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean(name="Loaded")
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

  /*  @LoadBalanced
    @Bean
    RestOperations rest(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.basicAuthentication("mobile", "pin").build();
    }
*/
    @Autowired
    @Qualifier(value = "noLoad")
    RestTemplate restTemplate;

    @Autowired
    @Qualifier(value = "Loaded")
    private RestTemplate restTemplate2;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Scheduled(fixedRate = 10000)
  //  @Scheduled(cron = "@daily")
  //  @Scheduled(cron = "0 0 0 * * *")
    public void sendReminderEmails() {

        try {

            String username = "mobile";
            String password = "pin";
            //    String password = passwordEncoder.encode("pin");
            System.out.println(password);
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(username, password);
            //   $2a$10$g2XaOBhSgYBx7GtmJmyaK.06Ckr2h2hGVOuhYR6FxNqG9aMHj90kK'
            MultiValueMap<String, String> bodyParamMap = new LinkedMultiValueMap<>();
            bodyParamMap.add("username", "janitha");
            bodyParamMap.add("password", "janitha@1234");
            bodyParamMap.add("grant_type", "password");

            HttpEntity entity = new HttpEntity<>(bodyParamMap, headers);

            Token token = restTemplate.exchange("http://localhost:9191/oauth/token", HttpMethod.POST, entity, Token.class).getBody();

            System.out.println(token.getAccess_token());

            HttpHeaders headers2 = new HttpHeaders();
            headers2.add("Authorization", "bearer " + token.getAccess_token());
            HttpEntity entity2 = new HttpEntity<>(headers2);

            ParameterizedTypeReference<List<Fine>> reference = new ParameterizedTypeReference<List<Fine>>() {
            };
            List<Fine> unpaidFines = restTemplate2.exchange("http://offence/services/fines/unpaid", HttpMethod.GET, entity2, reference).getBody();
            System.out.println(unpaidFines);

            ObjectMapper objectMapper = new ObjectMapper();

            for (int i = 0; i < unpaidFines.size(); i++) {

                Fine fine = unpaidFines.get(i);

                String licenseNo = fine.getLicenseNo();
                Response driverResponse = restTemplate2.exchange("http://driver/services/drivers/" + licenseNo, HttpMethod.GET, entity2, Response.class).getBody();
                //     Driver driver = (Driver) driverResponse.getData();
                Driver driver = objectMapper.convertValue(driverResponse.getData(), Driver.class);
                System.out.println(driver.getName());

                int officerId = fine.getIssuingOfficer();
                Response officerResponse = restTemplate2.exchange("http://officer/services/officers/" + officerId, HttpMethod.GET, entity2, Response.class).getBody();
                //    Officer officer = (Officer) officerResponse.getData();
                Officer officer = objectMapper.convertValue(officerResponse.getData(), Officer.class);
                System.out.println(officer.getEmail());

                int offenceId = fine.getOffenceId();
                Offence offence = restTemplate2.exchange("http://offence/services/offences/" + offenceId, HttpMethod.GET, entity2, Offence.class).getBody();
                System.out.println(offence.getOffence());

                if (fine.validTo.isAfter(LocalDate.now())) {
                    System.out.println("email settings - overdue date");

                    //email settings - overdue date
                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setTo(driver.getEmail());
                    simpleMailMessage.setFrom("ruranasinghe123@gmail.com");
                    simpleMailMessage.setSubject("Regarding Violated Offences!!!");
                    simpleMailMessage.setText("You have violated -> " + offence.getOffence() + " offence and need to pay : Rs." + offence.getFine() + " before --> " + fine.getValidTo());
                    javaMailSender.send(simpleMailMessage);

                } else if (fine.validTo.isBefore(LocalDate.now()) && fine.courtDate.isAfter(LocalDate.now())) {
                    System.out.println("email settings - court date");

                    //email settings - court date
                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setTo(driver.getEmail());
                    simpleMailMessage.setFrom("ruranasinghe123@gmail.com");
                    simpleMailMessage.setSubject("Regarding Violated Offences!!!");
                    simpleMailMessage.setText("Your grace period is exceeded for the violated offence --> " + offence.getOffence() + "!!! Your court date is --> " + fine.getCourtDate() + ". Court --> " + fine.getCourt());
                    javaMailSender.send(simpleMailMessage);

                } else if (fine.courtDate.isBefore(LocalDate.now())) {
                    System.out.println("email to officer for arrest driver");

                    //email to officer for arrest driver
                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setTo(officer.getEmail());
                    simpleMailMessage.setFrom("ruranasinghe123@gmail.com");
                    simpleMailMessage.setSubject("An arrest to be performed!!!");
                    simpleMailMessage.setText("This driver was unable to present on the court date for the violated offence. Arrest the driver and do the needful!!!. Violated offence --> " + offence.getOffence() + ". License No --> " + driver.getLicenseNo() + ". Name --> " + driver.getName() + ". Address --> " + driver.getAddress() + ". Contact No --> " + driver.getPhone());
                    javaMailSender.send(simpleMailMessage);

                }

            }

        } catch (RestClientException restClientException) {

            logger.error(restClientException.getMessage());

        } catch (MailException mailException) {

            logger.error(mailException.getMessage());

        } catch (NullPointerException nullPointerException) {

            logger.error(nullPointerException.getMessage());

        } catch (Exception exception) {

            logger.error(exception.getMessage());

        }
    }

    public void sendUserCredentialsEmail(String userName, String email, String password) throws MailException{

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setFrom("ruranasinghe123@gmail.com");
            simpleMailMessage.setSubject("Login Credentials to Traffic Offence Management System.");
            simpleMailMessage.setText("Please use these credentials to log in. User Name --> " + userName + " Password --> " + password);
            javaMailSender.send(simpleMailMessage);

    }




}
