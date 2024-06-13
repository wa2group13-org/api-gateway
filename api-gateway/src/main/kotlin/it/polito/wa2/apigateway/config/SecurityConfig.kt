package it.polito.wa2.apigateway.config

import it.polito.wa2.apigateway.properties.SecurityConfigProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val crr: ClientRegistrationRepository,
    private val securityConfigProperties: SecurityConfigProperties,
) {
    /**
     * Handle RP-initiated logout
     */
    fun oidcLogoutSuccessHandler() = OidcClientInitiatedLogoutSuccessHandler(crr)
        .also { it.setPostLogoutRedirectUri(securityConfigProperties.url) }

    /**
     * Maps roles from the keycloak userInfo to Spring roles.
     */
    private fun userAuthoritiesMapper(): GrantedAuthoritiesMapper =
        GrantedAuthoritiesMapper { authorities: Collection<GrantedAuthority> ->
            val mappedAuthorities = mutableSetOf<GrantedAuthority>()

            authorities.forEach { authority ->
                if (authority is OidcUserAuthority) {
                    val userInfo = authority.userInfo

                    // Map the claims found in idToken and/or userInfo
                    // to one or more GrantedAuthority's and add it to mappedAuthorities
                    val roles = userInfo
                        .claims["realm_access"]
                        ?.let { it as? Map<*, *> }
                        ?.get("roles")
                        ?.let { it as? List<*> }
                        ?.map { SimpleGrantedAuthority("ROLE_$it") }
                        ?: listOf()

                    mappedAuthorities.addAll(roles)
                } else if (authority is OAuth2UserAuthority) {
                    val userAttributes = authority.attributes

                    // Map the attributes found in userAttributes
                    // to one or more GrantedAuthority's and add it to mappedAuthorities
                    val roles = userAttributes["realm_access"]
                        ?.let { it as? Map<*, *> }
                        ?.get("roles")
                        ?.let { it as List<*> }
                        ?.map { SimpleGrantedAuthority("ROLE_$it") }
                        ?: listOf()

                    mappedAuthorities.addAll(roles)
                }
            }

            mappedAuthorities
        }

    @Bean
    @Profile("!no-security")
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests {
                it.requestMatchers("/", "/user", "/login", "/ui/**").permitAll()
                it.requestMatchers(HttpMethod.GET).permitAll()

                it.anyRequest().authenticated()
            }
            .oauth2Login { loginConfigurer ->
                loginConfigurer.userInfoEndpoint {
                    it.userAuthoritiesMapper(userAuthoritiesMapper())
                }
            }
            .logout { it.logoutSuccessHandler(oidcLogoutSuccessHandler()) }
            .csrf {
                it.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                it.csrfTokenRequestHandler(SpaCsrfTokenRequestHandler())
            }
            .addFilterAfter(CsrfCookieFilter(), BasicAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    @Profile("no-security")
    fun noSecurityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .build()
    }
}