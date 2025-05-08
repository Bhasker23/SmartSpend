package com.smartspend.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.YearMonth;

@Entity
public class MonthlyReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private YearMonth reportMonth; // Example: 2025-02

    private Double principalAmount;      // Monthly collection
    private Double loanEMI;              // This month’s EMI paid
    private Double loanInterest;         // Interest paid
    private Double penalty;              // Penalty for late payments
    private Double total;                // Sum of above

    private Double totalLoanAmount;      // Loan taken by member
    private Double outstandingLoan;      // Pending amount
    private Double cumulativePrincipal;  // Till previous month
    private Double cumulativeInterest;   // Till date interest earned
    private String remark;

    private LocalDate createdDate = LocalDate.now();
}
