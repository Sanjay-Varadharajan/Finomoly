package com.example.Finomoly.dto;

import com.example.Finomoly.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {


    private String userName;

    private String userMail;

    private String userPassword;

    private Role userRole;

}
