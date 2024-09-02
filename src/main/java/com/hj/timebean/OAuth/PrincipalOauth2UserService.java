package com.hj.timebean.OAuth;

import com.hj.timebean.auth.PrincipalDetails;
import com.hj.timebean.entity.Member;
import com.hj.timebean.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


//1. 타입 맞춰주기 : DefaultOAuth2UserService
//2 후 처리 진행하기
//loadUser 함수에 대해 이해해 보자
// 1. 구글 로그인 버튼을 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code리턴-> oauth-client라이브러리가 받은 후 엑세스 토큰 방급
// -> 유저 리퀘스트 정보 -> 여기에서 회원 프로필을 받음 이때 사용하는 함수가 /loadUser 함수

//해당 함수 종료 시 @AuthenticationPrincipal 어노테이션이 만들어 진다.
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //userRequest에 코드가 담겨 있는게 아닌 정보가 담겨있음..
        System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // 서버의 기본 정보 //- get을 통해 어떤 사이트에서 로그인을 했는지 고유아이디를 가져와 확인이 가능하다..
        System.out.println("getAccessToken : " + userRequest.getAccessToken());


        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes :" +  super.loadUser(userRequest).getAttributes());
        // 받아온 정보로 강제 회원가입 진행

        String provider = userRequest.getClientRegistration().getClientId(); //"google"
        String providerId = oAuth2User.getAttribute("sub");
        String accountId = provider+"_"+providerId;
        String email = oAuth2User.getAttribute("email");
/*        String password = oAuth2User.getAttribute(bCryptPasswordEncoder.encode("겟인데어"));*/
        String role = "ROLE_USER";

       Member memberEntity = memberRepository.findByAccountId(accountId);

        if(memberEntity == null){
           memberEntity = Member.builder()
                   .accountId(accountId)
                   .email(email)
                   .role(role)
                   .provider(provider)
                   .providerId(providerId)
                   .build();

           memberRepository.save(memberEntity);
        }
        return new PrincipalDetails(memberEntity,oAuth2User.getAttributes());
    }
}
