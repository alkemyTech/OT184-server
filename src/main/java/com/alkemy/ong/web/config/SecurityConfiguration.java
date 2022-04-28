package com.alkemy.ong.web.config;

import com.alkemy.ong.domain.users.UserService;
import com.amazonaws.util.IOUtils;
import lombok.SneakyThrows;
import org.apache.http.auth.InvalidCredentialsException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private RequestFilter requestFilter;

    public SecurityConfiguration(UserService userService,@Lazy RequestFilter requestFilter){
        this.userService = userService;
        this.requestFilter = requestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class );
    }

    @Component
    public class RequestFilter extends OncePerRequestFilter {

        private AuthenticationManager authenticationManager;
        private PasswordEncoder passwordEncoder;
        @Autowired
        private UserService userService;

        public RequestFilter(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
            this.authenticationManager = authenticationManager;
            this.passwordEncoder = passwordEncoder;
        }

        @SneakyThrows
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
            String jsonString = IOUtils.toString(request.getInputStream());
            JSONObject json = new JSONObject(jsonString);

            String email = json.get("email").toString();
            String password = json.get("password").toString();

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(email);
                if (userDetails == null) {
                    throw new UsernameNotFoundException("User Not Found with email: " + userDetails.getUsername());
                }
                Boolean booleanResult = passwordEncoder.matches(password, userDetails.getPassword());
                if (!booleanResult) {
                    throw new InvalidCredentialsException("Invalid Password");
                }

                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken( email, password);

                authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Authentication auth = authenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(authReq);
            }
            chain.doFilter(request, response);
        }

    }
}



