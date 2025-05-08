package com.smartspend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.YearMonth;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Loan loan;

    @ManyToOne
    private Member member;

    private YearMonth paymentMonth;
    private Double principalPaid;
    private Double interestPaid;
    private Double penaltyPaid;
    private LocalDate paymentDate;
}
