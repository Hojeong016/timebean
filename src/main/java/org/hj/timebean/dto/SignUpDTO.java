package org.hj.timebean.dto;

import org.hj.timebean.validation.PasswordMatches;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@PasswordMatches // 커스텀 유효성 검사 어노테이션 적용
public class SignUpDTO {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String accountId;

    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 비워둘 수 없습니다.")
    private String passwordConfirm;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "타이머에서 사용할 비밀번호를 입력해주세요.")
    @Pattern(regexp = "^\\d{6}$", message = "타이머 비밀번호는 6자리 숫자로만 입력해주세요.")
    private String timerPassword;

    public SignUpDTO() {
    }

    public SignUpDTO(String accountId, String password, String passwordConfirm, String nickname, String email, String timerPassword) {
        this.accountId = accountId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.nickname = nickname;
        this.email = email;
        this.timerPassword = timerPassword;
    }

    public @NotBlank(message = "아이디를 입력해주세요.") String getAccountId() {
        return accountId;
    }

    public void setAccountId(@NotBlank(message = "아이디를 입력해주세요.") String accountId) {
        this.accountId = accountId;
    }

    public @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.") @NotBlank(message = "비밀번호를 입력해주세요.") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하여야 합니다.") @NotBlank(message = "비밀번호를 입력해주세요.") String password) {
        this.password = password;
    }

    public @NotBlank(message = "비밀번호 확인은 비워둘 수 없습니다.") String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(@NotBlank(message = "비밀번호 확인은 비워둘 수 없습니다.") String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.") @NotBlank(message = "닉네임을 입력해주세요.") String getNickname() {
        return nickname;
    }

    public void setNickname(@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.") @NotBlank(message = "닉네임을 입력해주세요.") String nickname) {
        this.nickname = nickname;
    }

    public @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.") @NotBlank(message = "이메일을 입력해주세요.") String getEmail() {
        return email;
    }

    public void setEmail(@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.") @NotBlank(message = "이메일을 입력해주세요.") String email) {
        this.email = email;
    }

    public @NotBlank(message = "타이머에서 사용할 비밀번호를 입력해주세요.") @Pattern(regexp = "^\\d{6}$", message = "타이머 비밀번호는 6자리 숫자로만 입력해주세요.") String getTimerPassword() {
        return timerPassword;
    }

    public void setTimerPassword(@NotBlank(message = "타이머에서 사용할 비밀번호를 입력해주세요.") @Pattern(regexp = "^\\d{6}$", message = "타이머 비밀번호는 6자리 숫자로만 입력해주세요.") String timerPassword) {
        this.timerPassword = timerPassword;
    }
}
