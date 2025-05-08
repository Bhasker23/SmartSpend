package com.smartspend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private LocalDate joinDate;
    private boolean active;

    // Relationships
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MonthlyReport> reports;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Loan> loans;
}
