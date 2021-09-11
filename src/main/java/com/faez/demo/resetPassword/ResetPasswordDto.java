package com.faez.demo.resetPassword;

import lombok.Getter;

@Getter
public class ResetPasswordDto {
    private String token;
    private String oldPassword;
    private String newPassword;
    private String repeatPassword;


    @Override
    public String toString() {
        return "ResetPasswordDto{" +
                "token='" + token + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
