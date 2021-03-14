


package com.janitha.trafficoffencemanagement.oauthserver.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

 //   @Autowired
  //  UserDetailsService userDetailsService;

 //   @Bean
 //   public UserDetailsService userDetailsService() {
 //       return super.userDetailsService();
//    }

    @Autowired
    UserDetailsService userDetailsService;

 //   @Autowired
 //   JwtAuthEntryPoint unauthorizedHandler;


    @Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    /*    http.cors().and().csrf().disable().
                authorizeRequests()
             //   .antMatchers("/services/auth/**").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated();
*/
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/oauthuser/**").permitAll()
                .anyRequest().authenticated();
        /* working code before oauthuser change
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated();
         */
               /* .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
*/
      //  http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
/*
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/oauth/token");
    }
*/
}

