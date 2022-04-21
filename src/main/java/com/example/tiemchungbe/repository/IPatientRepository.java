package com.example.tiemchungbe.repository;

import com.example.tiemchungbe.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer> {
    @Query(value = "insert into patient(name,date_of_birth,gender,guardian,phone,address,email,account_id,delete_flag) values (?1,?2,?3,?4,?5,?6,?7,?8,?9))",nativeQuery = true)
    void savePatientToRegister(String name, String dateOfBirth, String gender, String guardian, String phone, String address, String email, Integer accountId, Boolean deleteFlag);
}
