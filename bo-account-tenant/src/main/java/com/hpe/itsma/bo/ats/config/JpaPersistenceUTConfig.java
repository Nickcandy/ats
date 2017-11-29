package com.hpe.itsma.bo.ats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.TimeZone;

@Configuration
@Profile({"test"})
public class JpaPersistenceUTConfig {
    @Bean
    AuditorAware<String> auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return ((User) authentication.getPrincipal()).getUsername();
        };
    }

    @Bean
    DateTimeProvider dateTimeProvider() {
        return () -> Calendar.getInstance(TimeZone.getTimeZone(ZoneOffset.UTC));
    }
}
