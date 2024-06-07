package it.polito.wa2.apigateway

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
    ): Map<String, Any?> {
        val principal: OidcUser? = authentication?.principal as? OidcUser
        val name = principal?.preferredUsername ?: ""
        return mapOf(
            "name" to name,
            "loginUrl" to "/oauth2/authorization/gateway-client",
            "logoutUrl" to "/logout",
            "principal" to principal,
            "xsrfToken" to xsrf
        )
    }

}