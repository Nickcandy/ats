package com.hpe.itsma.bo.ats;

import com.hpe.itsma.bo.common.annoation.EnableBOExceptionHandlerAdvice;
import com.hpe.itsma.bo.common.annoation.EnableBOSwagger;
import com.hpe.itsma.bo.common.annoation.EnableBoHealthCheck;
import com.hpe.itsma.bo.common.annoation.EnableLoggerAdvice;
import com.hpe.itsma.bo.common.page.PageableAndSort;
import com.hpe.itsma.bo.common.repository.EntityRepositoryImpl;
import com.hpe.itsma.bo.common.utils.ConvertUtilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableBOExceptionHandlerAdvice
@EnableBOSwagger
@EnableLoggerAdvice
@EnableBoHealthCheck
@EnableJpaRepositories(repositoryBaseClass = EntityRepositoryImpl.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware",dateTimeProviderRef = "dateTimeProvider")
public class Application extends SpringBootServletInitializer {

    @Bean
    PageableAndSort pageableAndSort(){
        return new PageableAndSort();
    }

    @Bean
    ConvertUtilities convertUtilities(){
        return new ConvertUtilities();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
