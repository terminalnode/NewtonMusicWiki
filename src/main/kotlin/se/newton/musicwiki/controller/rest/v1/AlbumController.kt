package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.*
import se.newton.musicwiki.dto.album.AlbumDto
import se.newton.musicwiki.dto.album.AlbumListDto
import se.newton.musicwiki.dto.mapper.DtoMapper
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.service.crud.AlbumService

@RestController
@RequestMapping("/api/rest/v1/album")
class AlbumController(
  val albumService: AlbumService,
  val dtoMapper: DtoMapper
) {
  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): AlbumDto {
    val album = albumService.findById(id)
    return dtoMapper.map(album);
  }

  @PostMapping
  fun createAlbum(@RequestBody album: Album): AlbumDto {
    val newAlbum = albumService.create(album)
    return dtoMapper.map(newAlbum)
  }

  @DeleteMapping("/{id}")
  fun deleteById(@PathVariable id: Long) {
    albumService.deleteById(id)
  }

  @GetMapping
  fun findAll(): AlbumListDto {
    val albums = albumService.findAll()
    return AlbumListDto(dtoMapper.map(albums))
  }
}