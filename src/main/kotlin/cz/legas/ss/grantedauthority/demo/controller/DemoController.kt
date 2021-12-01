package cz.legas.ss.grantedauthority.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


@RestController
class DemoController {
    @GetMapping("/demo")
    fun demo(): Mono<String> = "Hi!".toMono()
}
