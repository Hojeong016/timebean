package com.hj.timebean;

import com.hj.timebean.controller.SignInController;
import com.hj.timebean.controller.SignInRestController;
import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.service.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@WebMvcTest(SignInRestController.class)
public class TestBlankMemberIdOrPassword {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    public void testBlankMemberId() throws Exception {
        //given(상황)
        //memberId : null , password : notNull
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setMemberId("");
        signInDTO.setPassword("123");

        //when(실행)
        // Mockito 메서드. memberService.login(signInDTO) 를 수행할 때 리턴값을 false로 반환한다.
        Mockito.when(memberService.login(signInDTO)).thenReturn(false);
        //then(결과)
        //
        mockMvc.perform(MockMvcRequestBuilders.post("/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"memberId\": \"\", \"password\": \"123\" }"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("아이디 또는 패스워드가 입력되지 않았습니다."));

 }

    @Test
    public void testBlankPassword() throws Exception {
        //given(상황)
        //memberId : notNull , password : null
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setMemberId("abc");
        signInDTO.setPassword("");

        //when(실행)

        // Mockito 메서드. memberService.login(signInDTO) 를 수행할 때 리턴값을 false로 반환한다.
        Mockito.when(memberService.login(signInDTO)).thenReturn(false);

        //then(결과)
        mockMvc.perform(MockMvcRequestBuilders.post("/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"memberId\":\"abc\", \"password\":\"\"  }"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("아이디 또는 패스워드가 입력되지 않았습니다."));

    }

    @Test
    public void test() throws Exception {
        //given(상황)
        //memberId : notNull , password : notNull
        SignInDTO signInDTO = new SignInDTO();
        signInDTO.setMemberId("abc");
        signInDTO.setPassword("1234");

        //when(실행)
        // Mockito 메서드. memberService.login(signInDTO) 를 수행할 때 리턴값을 true로 반환한다.
        Mockito.when(memberService.login(signInDTO)).thenReturn(true);

        //then(결과)
        mockMvc.perform(MockMvcRequestBuilders.post("/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"memberId\":\"abc\", \"password\":\"1234\"  }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("로그인 성공"));

    }
}
