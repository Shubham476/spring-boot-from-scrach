package com.example.hospitalManagement.service;

import com.example.hospitalManagement.entity.Insurance;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.InsuranceRepository;
import com.example.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("patient not found"));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return insurance;
    }

    @Transactional
    public Insurance updateInsuranceOfAPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("patient not found"));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return insurance;
    }

    @Transactional
    public Patient removeInsuranceOfAPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("patient not found"));
        patient.setInsurance(null);
        return patient;
    }

    @Transactional
    public void deleteInsurance(Long patientId){
        patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("patient not found"));
        patientRepository.deleteById(patientId);
    }
}
