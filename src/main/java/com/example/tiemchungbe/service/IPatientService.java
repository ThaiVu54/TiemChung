package com.example.tiemchungbe.service;

public interface IPatientService {
    void addPatient(String name, String dateOfBirth, String gender, String guardian, String phone, String address, String email,Integer accountId,Boolean deleteFlag);
}
