package com.example.hospitalManagement.service;

import com.example.hospitalManagement.entity.Appointment;
import com.example.hospitalManagement.entity.Doctor;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.AppointmentRepository;
import com.example.hospitalManagement.repository.DoctorRepository;
import com.example.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId){
        Patient patient = patientRepository.findById(patientId).orElseThrow(()-> new RuntimeException("patient not found"));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new RuntimeException("patient not found"));
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
        return appointment;
    }


}
