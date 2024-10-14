package it.polito.wa2.apigateway.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("security.logout-redirect")
class SecurityConfigProperties {
    /**
     * URL where the [org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler.setPostLogoutRedirectUri]
     * function will redirect the user.
     */
    lateinit var url: String
}