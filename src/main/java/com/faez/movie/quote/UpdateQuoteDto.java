package com.faez.movie.quote;

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
