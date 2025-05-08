package com.smartspend.repo;

import com.smartspend.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByMemberId(Long memberId);

    @Query("SELECT l.remainingPrincipal FROM Loan l WHERE l.member.id = :memberId AND l.active = true")
    double findOutstandingLoanAmount(@Param("memberId") Long memberId);

}
