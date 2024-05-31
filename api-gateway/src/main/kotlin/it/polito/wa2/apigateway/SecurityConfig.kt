package it.polito.wa2.apigateway

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests {
                it.requestMatchers("/").authenticated()
                it.requestMatchers("/login").permitAll()
                it.anyRequest().denyAll()
            }
//            .formLogin {  } it should be a bean for using this
            .oauth2Login {  }
            .logout {  }
            .build()
    }
}