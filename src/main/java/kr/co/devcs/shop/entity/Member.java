package kr.co.devcs.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "members")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email", "social_flag"}))
public class Member extends BaseTime {
    @Id
    @Column(name = "member_id", columnDefinition = "BINARY(16)")
    private UUID memberId;
    @Column(name = "email", length = 40, nullable = false)
    private String email;
    @Column(name = "phone", length = 20, nullable = false)
    private String phone;
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "social_flag", nullable = false)
    private Boolean socialFlag;
    @Column(name = "active", nullable = false)
    private Boolean active;
}
