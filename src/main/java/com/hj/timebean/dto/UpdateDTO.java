package com.hj.timebean.dto;

import com.hj.timebean.validation.PasswordMatches;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@PasswordMatches
public class UpdateDTO {
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.")
    private String password;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickname;


    public UpdateDTO() {
    }

    public UpdateDTO(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.") String getEmail() {
        return email;
    }

    public void setEmail(@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.") String email) {
        this.email = email;
    }

    public @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.") String password) {
        this.password = password;
    }

    public @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.") String getNickname() {
        return nickname;
    }

    public void setNickname(@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.") String nickname) {
        this.nickname = nickname;
    }
}
