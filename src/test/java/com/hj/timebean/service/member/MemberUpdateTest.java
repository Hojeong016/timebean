package com.hj.timebean.service.member;

import com.hj.timebean.auth.PrincipalDetails;
import com.hj.timebean.auth.PrincipalDetailsService;
import com.hj.timebean.dto.UpdateDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class MemberUpdateTest {

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;


    //테스트 케이스
    @BeforeEach
    public void set(){

        Member member = new Member();
        member.setAccountId("test1");
        member.setEmail("test1@email.com");
        member.setPassword("123456test");
        member.setNickname ("test1");
        member.setTimerPassword(12234);
        member.setUpdatedDate(LocalDate.now());

        //when(memberRepository.findByAccountId("test1")).thenReturn(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getAccountId(), member.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void update(){

        UpdateDTO updateDTO = new UpdateDTO("test@email.com","test123456","testuser",123456,LocalDate.now());

        memberService.update(updateDTO);

        // Mock된 repository를 통해 검증
   /*     verify(memberRepository).findByAccountId("test1");*/

        Member updatedMember = memberRepository.findByAccountId("test1"); // 수정된 계정 ID로 검색
       /* assertNotNull(updatedMember);*/
        assertEquals("test@email.com", updatedMember.getEmail());
        assertEquals("test123456", updatedMember.getPassword());
        assertEquals(123456, updatedMember.getTimerPassword());
        assertEquals("testuser", updatedMember.getNickname());


    }

}