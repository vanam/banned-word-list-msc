package com.martinvana.bannedwordlist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

/**
 * HTTP configuration.
 */
@Configuration
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Handlers for RFC 7807.
     */
    private final SecurityProblemSupport problemSupport;

    /**
     * @param problemSupport Handlers for RFC 7807.
     */
    public SecurityConfiguration(SecurityProblemSupport problemSupport) {
        this.problemSupport = problemSupport;
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        // Turn off CSRF support.
        http.csrf().disable();

        http.exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport);
    }
}
