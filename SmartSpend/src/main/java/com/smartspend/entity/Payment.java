package com.smartspend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@Entity
@Table(name = "payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Loan loan;

    @ManyToOne
    private Member member;

    private Double principalPaid;
    private Double interestPaid;
    private Double penaltyPaid;
    private LocalDate paymentDate;
    private Integer emiNumber;
    private boolean isLate;

}
