package se.newton.musicwiki.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.newton.musicwiki.persistence.repositories.AlbumRepository
import se.newton.musicwiki.persistence.repositories.ArtistRepository

@RestController
@RequestMapping("/api/dev")
class DevController(
  val artistRepository: ArtistRepository,
  val albumRepository: AlbumRepository
) {
  @CrossOrigin
  @GetMapping("ping")
  fun ping(): String {
    return "pong!"
  }

  @CrossOrigin
  @GetMapping("test")
  fun test() {
    /*
    val artist = Artist();
    artist.name = "Goodie Goodie Boy 2";
    artist.artistType = ArtistType.BAND;
    artistRepository.save(artist);
     */
    val artist = artistRepository.findAll()[0]
    val album = albumRepository.findAll()[0]
    /*
    album.artists?.add(artist)
    albumRepository.save(album)
     */
    println(album.artists)
    println(artist.albums)
  }
}