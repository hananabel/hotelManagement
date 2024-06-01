package com.example.hotelManagementSystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/v1/customer/addCustomer", "/v1/customer/getCustomer/**","/v1/customer/updateCustomer/**", "/v1/room/getAllRooms", "/v1/room/getRoom/**", "/v1/booking/addBooking","/v1/booking/getBooking/**","/v1/booking/updateBooking/**","/v1/booking/deleteBooking/**","/v1/payment/getPayment/**", "/v1/payment/addPayment").hasAnyAuthority("ADMIN", "STAFF", "USER")
                        .requestMatchers("/v1/customer/getAllCustomers","/v1/booking/getAllBookings","/v1/payment/getAllPayments","/v1/payment/updatePayment/**","/v1/payment/deletePayment/**").hasAnyAuthority("ADMIN", "STAFF")
                        .requestMatchers("/v1/room/addRoom", "/v1/room/updateRoom/**","/v1/room/deleteRoom/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/public/**");
    }
}
