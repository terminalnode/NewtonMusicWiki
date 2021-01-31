package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.*
import se.newton.musicwiki.dto.mapper.DtoMapper
import se.newton.musicwiki.dto.song.SongDto
import se.newton.musicwiki.dto.song.SongListDto
import se.newton.musicwiki.persistence.models.Song
import se.newton.musicwiki.service.crud.SongService

@RestController
@RequestMapping("/api/rest/v1/song")
class SongController(
  val songService: SongService,
  val dtoMapper: DtoMapper
) {
  @CrossOrigin
  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): SongDto {
    val song = songService.findById(id)
    return dtoMapper.map(song, SongDto::class)
  }

  @CrossOrigin
  @PostMapping
  fun createAlbum(@RequestBody song: Song): SongDto {
    val newSong = songService.create(song)
    return dtoMapper.map(newSong, SongDto::class)
  }

  @CrossOrigin
  @DeleteMapping("/{id}")
  fun deleteById(@PathVariable id: Long) {
    songService.deleteById(id)
  }

  @CrossOrigin
  @GetMapping
  fun findAll(): SongListDto {
    val songs = songService.findAll()
    return SongListDto(dtoMapper.map(songs, SongDto::class))
  }

  @CrossOrigin
  @PostMapping("/edit")
  fun addArtistsToSong(@RequestBody song: Song): SongDto {
    val updatedSong = songService.update(song)
    return dtoMapper.map(updatedSong, SongDto::class)
  }
}