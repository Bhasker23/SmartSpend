package com.smartspend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDTO {
    private Long memberId;
    private Double loanAmount;
    private Integer tenureInMonths;
    private LocalDate startDate;
}
