package com.hpe.itsma.bo.ats.config;

import com.hpe.itsma.bo.common.annoation.EnableBOSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by peijia on 7/12/2017 0012.
 */
@Configuration
@EnableBOSecurity
@Profile({"dev", "prod", "kube"})
public class SecurityConfig {
}
