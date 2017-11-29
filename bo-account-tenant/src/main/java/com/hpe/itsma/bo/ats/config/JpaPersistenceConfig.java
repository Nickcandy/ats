package com.hpe.itsma.bo.ats.config;

import com.hpe.itsma.bo.common.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.TimeZone;

@Configuration
@Profile({"dev", "prod", "kube"})
public class JpaPersistenceConfig {
    Logger logger = LoggerFactory.getLogger(JpaPersistenceConfig.class);

    @Bean
    AuditorAware<String> auditorAware(){
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getDetails();
            String userId = userDetails.getUser().getId();
            try {
                Long.parseLong(userId);
                return userId;
            } catch (NumberFormatException nfe) {
                logger.info("User id for auditing is : " + userId);
                return null;
            }
        };
    }

    @Bean
    DateTimeProvider dateTimeProvider(){
        return () -> Calendar.getInstance(TimeZone.getTimeZone(ZoneOffset.UTC));
    }
}
