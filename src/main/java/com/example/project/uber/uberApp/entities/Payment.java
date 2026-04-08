package com.example.project.uber.uberApp.entities;

import com.example.project.uber.uberApp.entities.enums.PaymentMethod;
import com.example.project.uber.uberApp.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class Payment {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch= FetchType.LAZY)
    private Ride ride;

    private Double amount;

    @CreationTimestamp
    private LocalDateTime paymentTime;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
