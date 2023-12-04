package com.example.springsecurity.member.service

import com.example.springsecurity.member.dto.MemberDtoRequest
import com.example.springsecurity.member.entity.Member
import com.example.springsecurity.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberService(
  private val memberRepository: MemberRepository
) {
  /**
   * 회원가입
   */
  fun signUp(memberDtoRequest: MemberDtoRequest): String {
    // ID 중복 검사
    var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
    if (member != null) {
      return "이미 등록된 ID 입니다."
    }

    member = Member(
      id = null,
      loginId = memberDtoRequest.loginId,
      password = memberDtoRequest.password,
      name = memberDtoRequest.name,
      birthDate = memberDtoRequest.birthDate,
      gender = memberDtoRequest.gender,
      email = memberDtoRequest.email
    )

    memberRepository.save(member)

    return "회원가입이 완료되었습니다."
  }
}
