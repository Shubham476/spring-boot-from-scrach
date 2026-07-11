package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p from Patient p LEFT JOIN FETCH p.appointments")
    List<Patient> getAllPatientsWithAppointment();
}
