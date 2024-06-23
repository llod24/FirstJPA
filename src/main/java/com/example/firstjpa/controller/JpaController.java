package com.example.firstjpa.controller;

import com.example.firstjpa.dto.MemberDTO;
import com.example.firstjpa.entity.Member;
import com.example.firstjpa.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JpaController {

    //의존성 주입
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(value = "/jpa/memberWriteForm")
    public String memberWriteForm(
            @RequestParam(value = "num", required = false) Integer num, Model model
    ){

        if(num != null){
            System.out.println(num);
            //기존회원수정
            //1, db에서 num에해당하는 정보 가져오기
            Member member = memberRepository.findById(num).orElse(null);
            model.addAttribute("memberDTO", member);// memberDTO로 통일해서 view에 전달
            model.addAttribute("formTitle", "Modification");
            
        }
        else{
            System.out.println("null이네요");
            model.addAttribute("memberDTO", new MemberDTO());
            model.addAttribute("formTitle", "Registration");
        }

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

        return "redirect:/";
    }

    @GetMapping(value = "/jpa/memberList")
    public String memberList(Model model, Pageable pageable){

        // JPA 가 아닌 경우 서비스 계층에서 리스트를 받아오는 식으로 처리
//        List<Member> members = memberRepository.findAll();
        //JPA방식 + 페이징 --- page, pageable


        //page(몇페이지) size(출력될 요소 개수) sort 등의 정보를 파라미터로 넘겨줌
        //page번호는 0으로 시작 size deafult는 20
        //view 페이지에 적용할 값을 message.properties 파일에 정의하고 사용가능

        Page<Member> members = memberRepository.findAll(pageable);

        model.addAttribute("members", members);

        System.out.println(members.getTotalPages()); //총 페이지 리턴 메소드
        System.out.println(members.getTotalElements()); // 모든 레코드 개수
        System.out.println(members.getNumber());// 현재 페이지 번호
        System.out.println(members.getSize());//한페이지에 보여지는 레코드의 개수
        System.out.println(members.getSort());//정렬되었는지 여부 UNSORTED
        

        return "jpa/memberList";
    }


    @GetMapping(value = "/jpa/memberDelete")
    public String memberDelete(@RequestParam(value = "num", required = false) Integer num){

        System.out.println(num);

        //num을 받아서 삭제 메소드로 넘겨주기

        memberRepository.deleteById(num);

        return "redirect:/";
    }

    //회우너 상세 페이지 출력
    @GetMapping(value = "/jpa/memberDetail")
    public String memberDetail(@RequestParam(value = "num", required = false) Integer num, Model model){

        System.out.println(num);
        //num에 해당되는 회원 받아오기
        Member member = memberRepository.findById(num).orElse(null);
        model.addAttribute("member", member);

        return "jpa/memberDetail";
    }


}
