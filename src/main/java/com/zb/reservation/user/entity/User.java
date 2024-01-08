package com.zb.reservation.user.entity;

import com.zb.reservation.user.type.UserRole;
import com.zb.reservation.user.dto.SignInResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User {
    @Id
    @Column(unique = true)
    private String userId;

    private String password;

    private String userName;

    @Column(unique = true)
    private String phone;

    private String email;

    private String zipCode;

    private String address;

    private String userStatus;

    private LocalDateTime regDate;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;


    public SignInResponseDto from() {
        return SignInResponseDto.builder()
                .userId(this.userId)
                .roles(this.getRoles())
                .build();
    }
}
