package com.hj.timebean.auth;

//만든 이유
//시큐리티가 /login을 낚아챔 -> 로그인 진행 시킴
// 로그인 완료시 시큐리티 세션을 만들어 넣어줍니다. - 세션 공간은 동일하지만 시큐리티가
// 자신만의 세션 공간을 가지고 키값(Security ContextHolder)에다가 세션의 정보를 저장한다.
// 시큐리트의 세션에 들어갈 수 있는 오브젝트가 정해져있음 바로 Authentication 객체만 시큐리티의 세션 키값에 들어갈 수 있음
//Authentication 안에 User 정보가 있어야 함.
//User 오브젝트의 타입 -> UserDetails 타입의 객체로 Authentication에 저장 될 수 있음

// 그럼 어떻게 UserDetails를 꺼내야 할까? -- > 임플리먼트를 통해 타입 넣어 맞추기
//순서
//Security Session => Authentication => UserDetails(PrincipalDetails)  // 이게 콤포지션? : 즉 자동차와 엔진의 관계??


import com.hj.timebean.entity.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member;// 콤포지션
    private Map<String, Object> attributes;

    // 일반 로그인 시 사용 생성자
    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // 오어스 로그인 시 사용 생성자
    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("착을 수 없습니다.3");
       // user.getRole(); // r런데 권한이 String 타입,,, 그래서 이 아이를 리턴 불가능 따라서 타입을 맞춰줘야한다.
        Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
         //그럼 이제 스트링을 리펀할 수 있는 ,,, 메서드를 오버라이딩 할 수 있다..
        collect.add(()->{ return member.getRole();});
            return collect;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getAccountId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 너의 비번이 너무 오래 사용한 것은 아니니?
        return true;
    }

    @Override
    public boolean isEnabled() { //너의 계정을 활성화 시킬 것이니?
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public String getName() {
        return null;
    }
    //언제 f? = 예를 들어 회원이 1년간 로그인을 하지 않아 휴면 계정으로 하기로 함
    // 현재 시간 - 로깅 시간 => 1년 초과 시 리턴을 f로 한다..
}
