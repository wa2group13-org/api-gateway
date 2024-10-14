package it.polito.wa2.apigateway.dto

import org.springframework.security.oauth2.core.oidc.user.OidcUser

data class UserDTO(
    val name: String,
    val loginUrl: String,
    val logoutUrl: String,
    val principal: OidcUser?,
    val xsrfToken: String,
)
