package com.example.firstjpa.controller;

import com.example.firstjpa.dto.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JpaController {

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
        }
        catch (Exception e){

        }

        return "";
    }
}
