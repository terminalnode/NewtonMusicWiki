package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.*
import se.newton.musicwiki.dto.artist.ArtistDto
import se.newton.musicwiki.dto.artist.ArtistListDto
import se.newton.musicwiki.dto.artist.ArtistSongDto
import se.newton.musicwiki.dto.artist.ArtistSongListDto
import se.newton.musicwiki.dto.mapper.DtoMapper
import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song
import se.newton.musicwiki.service.crud.ArtistService

@RestController
@RequestMapping("/api/rest/v1/artist")
class ArtistController(
  val artistService: ArtistService,
  val dtoMapper: DtoMapper
) {
  @CrossOrigin
  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): ArtistDto {
    val artist = artistService.findById(id)
    return dtoMapper.map(artist, ArtistDto::class)
  }

  @CrossOrigin
  @PostMapping
  fun create(@RequestBody artist: Artist): ArtistDto {
    val savedArtist = artistService.create(artist)
    return dtoMapper.map(savedArtist, ArtistDto::class)
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  fun deleteById(@PathVariable id: Long) {
    artistService.deleteById(id)
  }

  @CrossOrigin
  @GetMapping
  fun findAll(): ArtistListDto {
    val artists = artistService.findAll()
    return ArtistListDto(dtoMapper.map(artists, ArtistDto::class))
  }

  @CrossOrigin
  @GetMapping("/type/{type}")
  fun findByType(@PathVariable type: ArtistType): ArtistListDto {
    val artists = artistService.findByType(type)
    return ArtistListDto(dtoMapper.map(artists, ArtistDto::class))
  }

  @CrossOrigin
  @PostMapping("/{id}")
  fun addSongToArtist(@PathVariable id: Long, @RequestBody songs: List<Song>) {
    artistService.addSongsToArtist(id, songs)
  }

  @CrossOrigin
  @PostMapping("/{id}/songs")
  fun addSongsToArtist(
    @PathVariable id: Long,
    @RequestBody artist: Artist
  ): ArtistSongListDto {
    val songs = artistService.addSongsToArtist(id, artist.songs)
    return ArtistSongListDto(dtoMapper.map(songs, ArtistSongDto::class))
  }

  @CrossOrigin
  @GetMapping("/{artistId}/songs/remove/{songId}")
  fun removeSongFromArtist(
    @PathVariable artistId: Long,
    @PathVariable songId: Long
  ) {
    artistService.removeSongFromArtist(artistId, songId)
  }
}