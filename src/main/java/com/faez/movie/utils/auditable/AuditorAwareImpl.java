package com.faez.movie.utils.auditable;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {


    @Override
    public @NotNull Optional<String> getCurrentAuditor() {

        return Optional.ofNullable(getCurrUserUsername());
    }

    private String getCurrUserUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        return auth.getPrincipal().toString();
    }

}

