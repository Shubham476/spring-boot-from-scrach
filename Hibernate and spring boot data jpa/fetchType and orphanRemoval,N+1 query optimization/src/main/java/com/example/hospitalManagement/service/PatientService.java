package com.example.hospitalManagement.service;

import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public void deletePatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("patient not found"));
        patientRepository.delete(patient);
    }
}
