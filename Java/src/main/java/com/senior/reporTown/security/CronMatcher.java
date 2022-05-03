package com.senior.reporTown.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class CronMatcher implements RequestMatcher {

    public boolean matches(HttpServletRequest request){
        if(request.getHeader("X-Appengine-Cron") != null &&
                request.getHeader("X-Appengine-Cron").equals("true")){
            return true;
        }
        return false;
    }
}
