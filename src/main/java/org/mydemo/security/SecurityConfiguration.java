package org.mydemo.security;

import org.mydemo.security.custom_filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                // Disabilita utilizzo del Token CSRF (verrà soppiantato dal fatto che la sessione sarà STATELESS)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/users/loginUsername", "/users/loginEmail", "/users/register").permitAll()
                        // queste richieste saranno aperte a tutti
                        .anyRequest().authenticated())
                        // Tutte le altre richieste http dovranno essere fatte da utenti già autenticati
                        // (Username-Password-Authentication filter)
                .httpBasic(Customizer.withDefaults())
                // Abilita le impostazioni di default di Spring Security per le richieste da client con Basic Auth
                .formLogin(Customizer.withDefaults())
                // Abilita le impostazioni di default di Spring Security per le richieste auth da form login
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Imposta la sessione come "STATELESS". Ogni utente si dovrà autenticare a ogni richiesta.
                // Il server non manterrà lo stato dell'utente impostando a ogni ricaricamento un session id
                // diverso.
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                // Per confermare un accesso già avvenuto, aggiunge un filtro prima del filtro
                // Username-Password-Authentication che valida un Jwt Token già presente,
                // saltando il filtro Username-Password-Authentication
                .build();
    }

    @Bean
    // Metodo che implementiamo per ricavare dal db username e password di utenti da autenticare.
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        // ho fatto il decode della password salvata sul db.
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
        return new ProviderManager(authenticationProvider);
    }
}
