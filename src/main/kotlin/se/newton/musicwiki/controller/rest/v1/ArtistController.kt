package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.*
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song
import se.newton.musicwiki.service.crud.ArtistService


@RestController
@RequestMapping("/api/rest/v1/artist")
class ArtistController(
  val artistService: ArtistService
) {
  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): Artist {
    return artistService.findById(id)
  }

  @PostMapping
  fun createAlbum(@RequestBody artist: Artist): Artist {
    return artistService.create(artist)
  }

  @DeleteMapping("/{id}")
  fun deleteById(@PathVariable id: Long) {
    artistService.deleteById(id)
  }

  @GetMapping
  fun findAll(): List<Artist> {
    return artistService.findAll()
  }

  @PostMapping("/{id}")
  fun addSongToArtist(@PathVariable id: Long, @RequestBody songs: List<Song>) {
    artistService.addSongsToArtist(id, songs)
  }


}