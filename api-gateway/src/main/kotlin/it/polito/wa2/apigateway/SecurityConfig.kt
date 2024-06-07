package it.polito.wa2.apigateway

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val crr: ClientRegistrationRepository,
) {
    // Handle RP-initiated logout
    fun oidcLogoutSuccessHandler() = OidcClientInitiatedLogoutSuccessHandler(crr)
        .also { it.setPostLogoutRedirectUri("http://localhost:8080") }

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests {
                it.requestMatchers("/","/user","/login","/ui/**").permitAll()
            }
            .oauth2Login {  }
            .logout { it.logoutSuccessHandler(oidcLogoutSuccessHandler()) }
//            .csrf {
//                it.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .build()
    }
}