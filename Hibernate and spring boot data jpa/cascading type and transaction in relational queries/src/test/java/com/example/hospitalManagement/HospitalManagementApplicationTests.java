package com.example.hospitalManagement;

import com.example.hospitalManagement.entity.Appointment;
import com.example.hospitalManagement.entity.Insurance;
import com.example.hospitalManagement.service.AppointmentService;
import com.example.hospitalManagement.service.InsuranceService;
import com.example.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class HospitalManagementApplicationTests {

	@Autowired
	private InsuranceService insuranceService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private PatientService patientService;

	@Test
	void contextLoads() {
	}

	@Test
	void testAssignInsuranceToPatient() {
		Insurance insurance = Insurance.builder()
				.provider("Star Health Insurance")
				.policyNumber("POL1001")
				.validUntil(LocalDate.of(2030, 1, 1))
				.build();
		var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance, 1L);
		System.out.println(updatedInsurance);
	}

	@Test
	void deleteInsuranceToId(){
		insuranceService.deleteInsurance(1L);
	}

	@Test
	void testCreateAppointment() {
		Appointment appointment = Appointment.builder()
				.appointmentTime(LocalDate.from(LocalDateTime.of(2026, 7, 10, 20, 30, 0)))
				.reason("cough and runny nose")
				.build();

		var updatedAppointment = appointmentService.createNewAppointment(appointment, 1L, 1L);
		System.out.println(updatedAppointment);
	}

	@Test
	void deletePatientAfterTestCreateAppointment() {
		Appointment appointment = Appointment.builder()
				.appointmentTime(LocalDate.from(LocalDateTime.of(2026, 7, 10, 20, 30, 0)))
				.reason("cough and runny nose")
				.build();

		var updatedAppointment = appointmentService.createNewAppointment(appointment, 1L, 1L);

		patientService.deletePatient(1L);
	}

}
