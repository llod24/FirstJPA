package com.example.firstjpa.controller;

import com.example.firstjpa.dto.MemberDTO;
import com.example.firstjpa.entity.Member;
import com.example.firstjpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JpaController {

    //의존성 주입
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(value = "/jpa/memberWriteForm")
    public String memberWriteForm(){

        return "jpa/memberWriteForm";
    }

    @PostMapping(value = "/jpa/memberWriteOk")
    public String insertMember(MemberDTO memberDTO, Model model){

        try{
            System.out.println(memberDTO.getName());
            System.out.println(memberDTO.getId());
            System.out.println(memberDTO.getPhone());
            System.out.println(memberDTO.toString());

//          1.DTO를 Entity로 변환
            Member member = memberDTO.toEntity();
            System.out.println(member.toString());

//          2. Repository -> entity -> db
            //save method : CrudRepository에 있는 메소드 / entity를 db에 저장
            memberRepository.save(member);



        }
        catch (Exception e){

        }

        return "/jpa/memberWriteForm";
    }
}
