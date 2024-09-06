package com.hj.timebean.dto;

import com.hj.timebean.validation.PasswordMatches;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@PasswordMatches
public class UpdateDTO {
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "타이머에서 사용할 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{6}$", message = "타이머 비밀번호는 6자리 숫자로만 입력해주세요.")
    private int timerPassword;

    public UpdateDTO(String email, String password, String nickname, int timerPassword, LocalDate updatedDate) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.timerPassword = timerPassword;
        this.updatedDate = updatedDate;
    }
    private LocalDate updatedDate;

    public @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.") @NotBlank(message = "이메일을 입력해주세요.") String getEmail() {
        return email;
    }

    public void setEmail(@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.") @NotBlank(message = "이메일을 입력해주세요.") String email) {
        this.email = email;
    }

    public @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.") @NotBlank(message = "비밀번호를 입력해주세요.") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.") @NotBlank(message = "비밀번호를 입력해주세요.") String password) {
        this.password = password;
    }


    public @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.") @NotBlank(message = "닉네임을 입력해주세요.") String getNickname() {
        return nickname;
    }

    public void setNickname(@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.") @NotBlank(message = "닉네임을 입력해주세요.") String nickname) {
        this.nickname = nickname;
    }

    @NotBlank(message = "타이머에서 사용할 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{6}$", message = "타이머 비밀번호는 6자리 숫자로만 입력해주세요.")
    public int getTimerPassword() {
        return timerPassword;
    }

    public void setTimerPassword(@NotBlank(message = "타이머에서 사용할 비밀번호를 입력해주세요.") @Pattern(regexp = "^\\d{6}$", message = "타이머 비밀번호는 6자리 숫자로만 입력해주세요.") int timerPassword) {
        this.timerPassword = timerPassword;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}
