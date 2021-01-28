package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.newton.musicwiki.dto.mapper.DtoMapper
import se.newton.musicwiki.service.crud.SongService

@RestController
@RequestMapping("/api/rest/v1/song")
class SongController(
  val songService: SongService,
  val dtoMapper: DtoMapper
) {
  /*
  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): SongDto {
    val song = songService.findById(id)
    return dtoMapper.map(song)
  }

  @PostMapping
  fun createAlbum(@RequestBody song: Song): SongDto {
    val newSong = songService.create(song)
    return dtoMapper.map(newSong)
  }

  @DeleteMapping("/{id}")
  fun deleteById(@PathVariable id: Long) {
    songService.deleteById(id)
  }

  @GetMapping
  fun findAll(): SongListDto {
    val songs = songService.findAll()
    return SongListDto(dtoMapper.map(songs))
  }

  @PostMapping("/edit")
  fun addArtistsToSong(@RequestBody song: Song): SongDto {
    val updatedSong = songService.update(song)
    return dtoMapper.map(updatedSong)
  }
 */
}