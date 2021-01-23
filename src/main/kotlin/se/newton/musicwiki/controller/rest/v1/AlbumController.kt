package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping

@RestController
@RequestMapping("/api/rest/v1/album")
class AlbumController {
    @GetMapping("/ping")
    fun ping(): String {
        return "pong!"
    }
}