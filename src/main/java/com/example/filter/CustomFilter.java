package com.example.filter;

import com.example.exceptions.ContactNotFoundException;
import com.example.model.Error;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
//@WebFilter(urlPatterns = "/contacts/")
public class CustomFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating Custom filter ##########");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        //LOGGER.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());

        //call next filter in the filter chain
        filterChain.doFilter(request, response);

        System.out.println("Status: "+ response.getStatus());
        System.out.println("URI: " + response.getContentType());
        LOGGER.info("Logging Response :{}", response.getContentType());

        if(response.getStatus()==404 && response.getContentType()==null) {

            List<String> errors = new ArrayList<>();
            errors.add("Request Not Found");

            Error error= new Error(new Date(), HttpStatus.NOT_FOUND,errors);
            response.getWriter().write(convertObjectToJson(error));
        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    @Override
    public void destroy() {
        // TODO: 7/4/18
    }

}
