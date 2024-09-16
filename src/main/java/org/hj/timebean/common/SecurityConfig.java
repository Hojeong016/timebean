package org.hj.timebean.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.hj.timebean.OAuth.PrincipalOauth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableWebSecurity//시큐리티 황성화 -> 스프링 시큐리티 필터가 스프링 필터 체인에 등록이 된다. //secured 어노테이션 활성화
@EnableGlobalMethodSecurity(securedEnabled = true) //securedEnabled 이걸 설정을 해주어야 @Secured("ROLE_ADMIN")을 인식하여 적용가능해진다
public class SecurityConfig { //extends WebSecurityConfigurerAdapter -> 지원 중단

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;
    //해당 메서드의 리턴되는 오브젝트를 ioc 로 등록해준다.
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        logger.info("Security logger name -> {}",logger.getName());

        //시큐리티 인증 비활성화
        http.csrf((auth -> auth.disable()));
        //접근제한
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")//access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll());

        http.formLogin(formLogin -> formLogin // 커스텀 마이징한 로그인 설정을 인자로 fromLogin() 메서드에 넘긴다.
                        .loginPage("/member/signIn")// 설정 안하면 접근 제한 페이지 호출 시 -- 접근 에러 .. 이 거 설정하면 자격이 없는 경우 login으로 리다리렉션 됨
                        .usernameParameter("accountId")
                        .passwordParameter("password")
                        .permitAll()
                        .defaultSuccessUrl("/ranking/", true)
                // 요청처리 url 설정 //login 호출이 된다 즉 시쿠리티가 낚아채서 대신 로그인 진행 -> 이에 따라 controller에 로그인을 만들지 않아도 된다.
                // 이때 우리가 해주어야하는 일이 있다 = auth
        );

        http.oauth2Login(oauth2 ->
                oauth2
                        .loginPage("/member/signIn")
                        .userInfoEndpoint(userinfo -> // 사용자의 정보를 가져오기 위해 사용됨,,,
                                userinfo
                                        .userService(principalOauth2UserService)
                        )
                        .defaultSuccessUrl("/ranking/", true)

        ); // 성공을했는데 그럼 그 로그인 한 가람의 정보... 회원가임.... 우리 db에 정보가 있는지 비교후에 성공 url을 보내줘야,,,하는거 아니야>>??

        http.logout(logout -> logout
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/ranking/")
                .invalidateHttpSession(true)  // 세션 무효화
                .deleteCookies("JSESSIONID")  // 쿠키 삭제
        );
        return http.build();
    }
}
//메서드 체이닝: 기존 방식은 메서드 체이닝을 통해 구성하며, .and()로 설정 구역을 연결했습니다.
//새로운 DSL: 새로운 방식에서는 HttpSecurity 객체를 메서드 인자로 받아 설정을 구성하며,
//메서드 체이닝 없이 람다 표현식으로 각 설정을 구분합니다.
