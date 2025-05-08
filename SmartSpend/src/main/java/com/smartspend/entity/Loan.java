package com.smartspend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "loans")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private Double loanAmount;
    private Double interestRate = 1.0; // reducing balance interest
    private LocalDate loanStartDate;
    private Integer tenureInMonths;
    private Double monthlyEmi;
    private Double outstandingAmount;   // This updates monthly
    private String status;

    // Useful for tracking EMI per month
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
