package com.example.tiemchungbe.service;

import com.example.tiemchungbe.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements IPatientService{
    @Autowired
    private IPatientRepository patientRepository;
    @Override
    public void addPatient(String name, String dateOfBirth, String gender, String guardian, String phone, String address, String email, Integer accountId, Boolean deleteFlag) {
        patientRepository.savePatientToRegister(name, dateOfBirth, gender, guardian, phone, address, email, accountId, deleteFlag);
    }
}
