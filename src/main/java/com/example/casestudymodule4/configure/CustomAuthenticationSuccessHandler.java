package com.example.casestudymodule4.configure;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "/home"; // Default redirect URL

        // Check if there's a redirect URL stored in the session
        String sessionRedirectUrl = (String) request.getSession().getAttribute("redirectAfterLogin");
        if (sessionRedirectUrl != null) {
            logger.info("Redirecting to: " + sessionRedirectUrl);
            redirectUrl = sessionRedirectUrl;
            // Remove the redirect URL from the session after use
            request.getSession().removeAttribute("redirectAfterLogin");
        } else {
            logger.info("No redirect URL found in session, redirecting to default: " + redirectUrl);
        }

        response.sendRedirect(redirectUrl);
    }
}
