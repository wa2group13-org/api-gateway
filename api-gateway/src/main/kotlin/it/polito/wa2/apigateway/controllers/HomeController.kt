package it.polito.wa2.apigateway.controllers

import it.polito.wa2.apigateway.dto.UserDTO
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/user")
    fun user(
        @CookieValue(name = "XSRF-TOKEN", required = true)
        xsrf: String,
        authentication: Authentication?
    ): UserDTO {
        val principal: OidcUser? = authentication?.principal as? OidcUser
        val name = principal?.preferredUsername ?: ""
        return UserDTO(
            name = name,
            loginUrl = "/oauth2/authorization/gateway-client",
            logoutUrl = "/logout",
            principal = principal,
            xsrfToken = xsrf
        )
    }

}