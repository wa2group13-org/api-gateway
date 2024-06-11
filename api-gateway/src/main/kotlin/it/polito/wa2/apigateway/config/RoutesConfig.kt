package it.polito.wa2.apigateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.http.HttpStatus
import java.net.URI
import java.net.URL

@Configuration
class RoutesConfig {

    @Bean
    fun myRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("document_store") { p ->
                p.path("/document_store/**")
                    .filters { f ->
                        f.stripPrefix(1)
                    }
                    .uri(
                        "lb://document_store"
                    )
            }
            .route("crm") { p ->
                p.path("/crm/**")
                    .filters { f ->
                        f.stripPrefix(1)
                    }
                    .uri(
                        "lb://crm"
                    )
            }
            .route("communication_manager") { p ->
                p.path("/communication_manager/**")
                    .filters { f ->
                        f.stripPrefix(1)
                    }
                    .uri(
                        "lb://communication_manager"
                    )
            }
            .route("ui") { p ->
                p.path("/ui/**")
                    .filters { f ->
                        f.stripPrefix(1)
                    }
                    .uri(
                        "http://localhost:5173"
                    )
            }
            .route("home") { p ->
                p.path("/")
                    .filters { f ->
                        f.redirect(HttpStatus.MOVED_PERMANENTLY, URI.create("http://localhost:8080/ui").toURL())
                    }
                    .uri(
                        "http://localhost:5173"
                    )
            }
            .build()
    }

}