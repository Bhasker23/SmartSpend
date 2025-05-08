package com.smartspend.service;

import com.smartspend.dto.LoanRequestDTO;
import com.smartspend.dto.LoanResponseDTO;

import java.util.List;

public interface LoanService {

    LoanResponseDTO createLoan(LoanRequestDTO dto);

    List<LoanResponseDTO> getLoansByMemberId(Long memberId);

    LoanResponseDTO getLoanById(Long id);
    void closeLoan(Long loanId);
}
