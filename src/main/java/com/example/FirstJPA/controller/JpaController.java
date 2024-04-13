package com.example.FirstJPA.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class JpaController {

    @GetMapping(value = "/jpa/memberWriteForm")
    public String memberWriteForm(){

        return "jpa/memberWriteForm";
    }
}
