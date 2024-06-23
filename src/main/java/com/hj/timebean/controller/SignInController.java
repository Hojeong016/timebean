package com.hj.timebean.controller;

import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.service.member.MemberService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    private final MemberService memberService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Value("${spring.security.oauth2.client.provider.google.authorizationUri}")
    private static final String authorizationRequestBaseUri = "oauth2/authorization";
    private Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    @Autowired
    public SignInController(MemberService memberService, ClientRegistrationRepository clientRegistrationRepository) {
        this.memberService = memberService;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }
    @GetMapping("/signInView")
    public String signInView(){
        return "signIn/signIn";
    }
    @GetMapping("/User")
    public  String user(){
        return "User";
    }

    @PostMapping("/signIn")
    public String signIn(@Valid @ModelAttribute SignInDTO signInDTO, RedirectAttributes redirectAttributes) {

        if (StringUtils.isBlank(signInDTO.getMemberId()) || StringUtils.isBlank(signInDTO.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "모든 정보를 입력해주세요.");
            return "redirect:/signIn/signInView";
        }
        boolean result = memberService.login(signInDTO);

        if (result == true) {
            //랭킹창 반환
            return "redirect:User";
        } else {
            //로그인 창 반환
            return "signIn/signIn";
        }
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/oauthlogin")
    public String getLoginPage(Model model) throws Exception {
        System.out.println("fffffffffffffffffffffff");
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        assert clientRegistrations != null;
        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "signIn/signIn";
    }
}
