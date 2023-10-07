package kr.co.devcs.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class MemberForm {
    private UUID memberId = null;
    @NotBlank(message = "이메일은 필수입니다.")
    @Email
    private String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 6, max = 20)
    private String password;
    @NotBlank(message = "전화번호는 필수입니다.")
    private String phone;
    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;
//    @NotBlank(message = "생년월일은 필수입니다.")
    @NotNull(message = "생년월일은 필수입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthDate;
}
