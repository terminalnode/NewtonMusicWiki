package se.newton.musicwiki.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.repositories.ArtistRepository

@RestController
@RequestMapping("/api/dev")
class DevController(
    val artistRepository: ArtistRepository
) {
    @GetMapping("ping")
    fun ping(): String {
        return "pong!"
    }

    @GetMapping("test")
    fun test() {
        val artist = Artist();
        artist.name = "Goodie Goodie Boy 2";
        artist.artistType = ArtistType.BAND;
        artistRepository.save(artist);
    }
}