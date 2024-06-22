package com.example.firstjpa.repository;


import com.example.firstjpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//CrudRepository나 JpaRepository를 상속받아 사용
//엔터티 --> 테이블명인 Member와 같게해야
public interface  MemberRepository extends JpaRepository<Member, Integer> {

}
