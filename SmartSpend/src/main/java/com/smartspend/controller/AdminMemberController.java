package com.smartspend.controller;

import com.smartspend.dto.MemberDTO;
import com.smartspend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/members")
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @PostMapping
    public MemberDTO add(@RequestBody MemberDTO dto) {
        return memberService.addMember(dto);
    }

    @PutMapping("/{id}")
    public MemberDTO update(@PathVariable Long id, @RequestBody MemberDTO dto) {
        return memberService.updateMember(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    @GetMapping
    public List<MemberDTO> getAll() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public MemberDTO getOne(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }
}
