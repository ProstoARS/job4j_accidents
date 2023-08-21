package ru.job4j.accidents.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource ds;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(ds);
        users.setUsersByUsernameQuery("""
                SELECT username, password, enabled
                FROM users WHERE username = ?
                """);
        users.setAuthoritiesByUsernameQuery("""
                SELECT u.username, a.authority
                FROM authorities AS a, users AS u
                WHERE u.username = ? and u.authority_id = a.id
                """);
        return users;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .requestMatchers("/login", "/reg").permitAll()
                        .requestMatchers("/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated());
        http.formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login?error=true"));
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true));
        return http.build();
    }

}