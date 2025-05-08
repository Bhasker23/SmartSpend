package com.smartspend.service;

import com.smartspend.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO addMember(MemberDTO dto);
    MemberDTO updateMember(Long id, MemberDTO dto);
    void deleteMember(Long id);
    List<MemberDTO> getAllMembers();
    MemberDTO getMemberById(Long id);
}
