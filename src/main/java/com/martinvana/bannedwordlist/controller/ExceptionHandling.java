package com.martinvana.bannedwordlist.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;

/**
 * Exception handling according to RFC 7807.
 * @see <a href="https://github.com/zalando/problem-spring-web>problem-spring-web</a>
 */
@ControllerAdvice
class ExceptionHandling implements ProblemHandling {

}
