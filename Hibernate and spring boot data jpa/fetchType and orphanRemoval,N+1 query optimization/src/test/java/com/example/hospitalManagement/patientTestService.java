package com.example.hospitalManagement;

import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class patientTestService {

    @Autowired
    private PatientRepository patientRepository; //not the correct way has to access through the service layer

/*
    The N+1 Query Problem is a performance issue in Hibernate/JPA where:
        1 query is executed to fetch the parent entities.
        N additional queries are executed to fetch the child entities for each parent.

    here what is n+1 query problem
    1 query  -> Fetch all patients
    100 queries -> Fetch appointments for each patient
    Total = 101 queries

    Solution: Here need to create the custom query to fetch the data
*/

    @Test
    public void testPatient(){
        List<Patient> patients = patientRepository.findAll();
        for(var p: patients){
            System.out.println(p);
        }
    }

    @Test
    public void getAllPatientsWithAppointment(){
        List<Patient> patients = patientRepository.getAllPatientsWithAppointment();
        for(var p: patients){
            System.out.println(p);
        }
    }
}
