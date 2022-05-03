package com.senior.reporTown.service;

import com.senior.reporTown.model.ApplicationUser;
import com.senior.reporTown.model.InvalidToken;
import com.senior.reporTown.repository.TokenBlacklistRepository;
import com.senior.reporTown.security.jwt.AuthTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class LogoutHandlerService implements LogoutHandler {
    private final Logger logger = LoggerFactory.getLogger(LogoutHandlerService.class);
    private final TokenBlacklistRepository tokenBlacklistRepository;

    public LogoutHandlerService(TokenBlacklistRepository tokenBlacklistRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logout(AuthTokenFilter.parseJwt(request));
    }

    public void logout(String jwt) {
        boolean tokenExists = tokenBlacklistRepository
                .findByJwt(jwt)
                .isPresent();

        if (tokenExists) {
            logger.info("User already logged out");
            return;
        }

        InvalidToken token = new InvalidToken();
        token.setJwt(jwt);
        tokenBlacklistRepository.save(token);
    }
}
