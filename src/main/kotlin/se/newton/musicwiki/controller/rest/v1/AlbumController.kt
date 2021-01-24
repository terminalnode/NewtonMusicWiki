package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.*
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.service.crud.AlbumService

@RestController
@RequestMapping("/api/rest/v1/album")
class AlbumController(
    val albumService: AlbumService
) {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Album {
        return albumService.findById(id)
    }

    @PostMapping
    fun createAlbum(@RequestBody album: Album): Album {
        return albumService.create(album)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        albumService.deleteById(id)
    }

    @GetMapping
    fun findAll(): List<Album> {
        return albumService.findAll()
    }
}