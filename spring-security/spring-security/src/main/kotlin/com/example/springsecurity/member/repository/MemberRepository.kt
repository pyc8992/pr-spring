package com.example.springsecurity.member.repository

import com.example.springsecurity.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
  fun findByLoginId(loginId: String): Member?
}
