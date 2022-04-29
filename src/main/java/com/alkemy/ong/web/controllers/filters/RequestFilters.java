package com.alkemy.ong.web.controllers.filters;

import com.alkemy.ong.domain.users.UserService;
import com.amazonaws.util.IOUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.auth.InvalidCredentialsException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class RequestFilters extends OncePerRequestFilter {

        @Autowired
        private UserService userService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        private AuthenticationManager authenticationManager;
        public RequestFilters (AuthenticationManager authenticationManager){
            this.authenticationManager = authenticationManager;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userService.loadUserByUsername(username);
                if(!userDetails.getUsername().equals(username)){
                    throw new UsernameNotFoundException("Username invalid");
                }
                Boolean boo = passwordEncoder.matches(password, userDetails.getPassword());
                if(!boo){
                    throw new BadCredentialsException("Password invalid");
                }

                    UsernamePasswordAuthenticationToken authReq =
                            new UsernamePasswordAuthenticationToken(username,
                                    password);

                    //authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    Authentication auth = authenticationManager.authenticate(authReq);

                    SecurityContextHolder.getContext().setAuthentication(authReq);

                }
            chain.doFilter(request, response);
        }
}
