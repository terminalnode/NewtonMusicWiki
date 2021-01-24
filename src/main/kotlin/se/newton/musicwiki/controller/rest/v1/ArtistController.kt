package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.*
import se.newton.musicwiki.dto.*
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song
import se.newton.musicwiki.service.crud.ArtistService


@RestController
@RequestMapping("/api/rest/v1/artist")
class ArtistController(
  val artistService: ArtistService
) {
  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): ArtistDto? {
    val artist = artistService.findById(id)
    return mapArtistToDto(artist)
  }

  @PostMapping
  fun create(@RequestBody artist: Artist): ArtistDto? {
    val savedArtist = artistService.create(artist)
    return mapArtistToDto(savedArtist)
  }

  @DeleteMapping("/{id}")
  fun deleteById(@PathVariable id: Long) {
    artistService.deleteById(id)
  }

  @GetMapping
  fun findAll(): ArtistListDto {
    val artists = artistService.findAll()
    return ArtistListDto(mapArtistsToDtos(artists))
  }

  @PostMapping("/{id}")
  fun addSongToArtist(@PathVariable id: Long, @RequestBody songs: List<Song>) {
    artistService.addSongsToArtist(id, songs)
  }

  @PostMapping("/{id}/songs")
  fun addSongsToArtist(
    @PathVariable id: Long,
    @RequestBody artist: Artist
  ): ArtistSongListDto {
    val songs = artistService.addSongsToArtist(id, artist.songs)
    return ArtistSongListDto(mapArtistSongsToDtos(songs))
  }
}