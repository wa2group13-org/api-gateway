package it.polito.wa2.apigateway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class HomeController {

    @GetMapping("")
    fun home(principal: Principal): Any {
        return principal
    }

}