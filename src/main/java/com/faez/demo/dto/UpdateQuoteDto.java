package com.faez.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuoteDto {
    private String role;
    private String actor;
    private String quote;
    private String show;
}
