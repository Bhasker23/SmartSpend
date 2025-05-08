package com.smartspend.controller;

import com.smartspend.dto.LoanRequestDTO;
import com.smartspend.dto.LoanResponseDTO;
import com.smartspend.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponseDTO> createLoan(@RequestBody LoanRequestDTO dto) {
        return new ResponseEntity<>(loanService.createLoan(dto), HttpStatus.CREATED);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<LoanResponseDTO>> getLoansByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(loanService.getLoansByMemberId(memberId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> getLoan(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    @PutMapping("/close/{loanId}")
    public ResponseEntity<Void> closeLoan(@PathVariable Long loanId) {
        loanService.closeLoan(loanId);
        return ResponseEntity.noContent().build();
    }
}
