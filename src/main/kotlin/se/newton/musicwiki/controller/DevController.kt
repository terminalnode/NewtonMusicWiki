package se.newton.musicwiki.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dev")
class DevController {
    @GetMapping("ping")
    fun ping(): String {
        return "pong!"
    }
}