package com.smartspend.service;

import com.smartspend.dto.LoanRequestDTO;
import com.smartspend.dto.LoanResponseDTO;
import com.smartspend.entity.Loan;
import com.smartspend.entity.Member;
import com.smartspend.exception.ResourceNotFoundException;
import com.smartspend.repo.LoanRepository;
import com.smartspend.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final MemberRepository memberRepo;
    private final LoanRepository loanRepo;

    @Override
    public LoanResponseDTO createLoan(LoanRequestDTO dto) {
        Member member = memberRepo.findById(dto.getMemberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        double rate = 0.01; // 1% reducing
        double principal = dto.getLoanAmount();
        int months = dto.getTenureInMonths();

        double emi = calculateReducingEMI(principal, rate, months);

        Loan loan = Loan.builder()
                .member(member)
                .loanAmount(principal)
                .tenureInMonths(months)
                .monthlyEmi(emi)
                .outstandingAmount(principal)
                .loanStartDate(dto.getStartDate())
                .status("ACTIVE")
                .build();

        Loan saved = loanRepo.save(loan);
        return toDTO(saved);
    }

    @Override
    public List<LoanResponseDTO> getLoansByMemberId(Long memberId) {
        return loanRepo.findByMemberId(memberId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LoanResponseDTO getLoanById(Long id) {
        Loan loan = loanRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        return toDTO(loan);
    }

    @Override
    public void closeLoan(Long loanId) {
        Loan loan = loanRepo.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        loan.setStatus("CLOSED");
        loan.setOutstandingAmount(0.0);
        loanRepo.save(loan);
    }

    private double calculateReducingEMI(double principal, double monthlyRate, int months) {
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
    }

    private LoanResponseDTO toDTO(Loan loan) {
        return LoanResponseDTO.builder()
                .loanId(loan.getId())
                .memberId(loan.getMember().getId())
                .memberName(loan.getMember().getName())
                .loanAmount(loan.getLoanAmount())
                .monthlyEmi(loan.getMonthlyEmi())
                .outstandingAmount(loan.getOutstandingAmount())
                .status(loan.getStatus())
                .startDate(loan.getLoanStartDate())
                .build();
    }
}
