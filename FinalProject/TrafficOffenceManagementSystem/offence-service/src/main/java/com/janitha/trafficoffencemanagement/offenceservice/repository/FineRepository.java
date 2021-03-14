package com.janitha.trafficoffencemanagement.offenceservice.repository;

import com.janitha.trafficoffencemanagement.model.offenceservice.Fine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface FineRepository extends JpaRepository<Fine, Integer> {

    @Query("SELECT fine FROM Fine fine WHERE fine.licenseNo = ?1 AND fine.status = 'not-paid'")
    Optional<List<Fine>> findFineByLicenseNo(String licenseNo);


    @Modifying
    @Transactional
    @Query("UPDATE Fine fine SET fine.status = ?2 WHERE fine.licenseNo = ?1")
    int updateFineStatus(String licenseNo, String status);

    @Query("SELECT fine.offenceId FROM Fine fine WHERE fine.licenseNo = ?1 AND fine.status = 'not-paid'")
    public List<Integer> getUnpaidOffenceList(String licenseNo);

    @Query("SELECT fine FROM Fine fine WHERE fine.status = 'not-paid'")
    public List<Fine> getAllUnpaidFines();

}
