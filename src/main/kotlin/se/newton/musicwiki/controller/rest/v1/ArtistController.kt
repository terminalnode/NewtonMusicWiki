package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.*
import se.newton.musicwiki.dto.artist.ArtistDto
import se.newton.musicwiki.dto.artist.ArtistListDto
import se.newton.musicwiki.dto.artist.ArtistSongDto
import se.newton.musicwiki.dto.artist.ArtistSongListDto
import se.newton.musicwiki.dto.mapper.DtoMapper
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song
import se.newton.musicwiki.service.crud.ArtistService

@RestController
@RequestMapping("/api/rest/v1/artist")
class ArtistController(
  val artistService: ArtistService,
  val dtoMapper: DtoMapper
) {
  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): ArtistDto {
    val artist = artistService.findById(id)
    return dtoMapper.map(artist, ArtistDto::class)
  }

  @PostMapping
  fun create(@RequestBody artist: Artist): ArtistDto {
    val savedArtist = artistService.create(artist)
    return dtoMapper.map(savedArtist, ArtistDto::class)
  }

  @DeleteMapping("/{id}")
  fun deleteById(@PathVariable id: Long) {
    artistService.deleteById(id)
  }

  @GetMapping
  fun findAll(): ArtistListDto {
    val artists = artistService.findAll()
    return ArtistListDto(dtoMapper.map(artists, ArtistDto::class))
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
    return ArtistSongListDto(dtoMapper.map(songs, ArtistSongDto::class))
  }
}