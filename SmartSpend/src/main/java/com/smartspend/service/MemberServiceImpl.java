package com.smartspend.service;

import com.smartspend.dto.MemberDTO;
import com.smartspend.entity.Member;
import com.smartspend.repo.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repo;

    @Override
    public MemberDTO addMember(MemberDTO dto) {
        Member member = new Member();
        BeanUtils.copyProperties(dto, member);
        member.setActive(true);
        return toDTO(repo.save(member));
    }

    @Override
    public MemberDTO updateMember(Long id, MemberDTO dto) {
        Member member = repo.findById(id).orElseThrow();
        BeanUtils.copyProperties(dto, member);
        return toDTO(repo.save(member));
    }

    @Override
    public void deleteMember(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        return toDTO(repo.findById(id).orElseThrow());
    }

    private MemberDTO toDTO(Member m) {
        MemberDTO dto = new MemberDTO();
        BeanUtils.copyProperties(m, dto);
        return dto;
    }
}
