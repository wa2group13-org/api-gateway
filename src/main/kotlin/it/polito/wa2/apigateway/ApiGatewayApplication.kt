package it.polito.wa2.apigateway

import it.polito.wa2.apigateway.properties.OpenapiConfigProperties
import it.polito.wa2.apigateway.properties.ProjectConfigProperties
import it.polito.wa2.apigateway.properties.SecurityConfigProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(
	ProjectConfigProperties::class,
	OpenapiConfigProperties::class,
	SecurityConfigProperties::class,
)
class ApiGatewayApplication

fun main(args: Array<String>) {
	runApplication<ApiGatewayApplication>(*args)
}
