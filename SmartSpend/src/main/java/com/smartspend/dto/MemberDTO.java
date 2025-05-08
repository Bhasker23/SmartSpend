package com.smartspend.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private LocalDate joinDate;
    private boolean active;
    private String remarks;
}
