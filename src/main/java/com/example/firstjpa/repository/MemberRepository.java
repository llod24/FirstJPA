package com.example.firstjpa.repository;


import com.example.firstjpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//CrudRepository나 JpaRepository를 상속받아 사용
//엔터티 --> 테이블명인 Member와 같게해야
public interface  MemberRepository extends JpaRepository<Member, Integer> {


    //회원 검색용
    Page<Member> findByNameContaining(String name, Pageable pageable);

    Page<Member> findByIdContaining(String id, Pageable pageable);
    Page<Member> findByPhoneContaining(String phone, Pageable pageable);
}
