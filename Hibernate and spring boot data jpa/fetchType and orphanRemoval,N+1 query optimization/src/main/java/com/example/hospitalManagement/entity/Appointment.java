package com.example.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.print.Doc;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne //owning side
    @ToString.Exclude
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(nullable = false)
    private Doctor doctor;
}
