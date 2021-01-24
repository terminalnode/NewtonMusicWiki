package se.newton.musicwiki.controller.rest.v1

import org.springframework.web.bind.annotation.*
import se.newton.musicwiki.persistence.models.Song
import se.newton.musicwiki.service.crud.SongService

@RestController
@RequestMapping("/api/rest/v1/song")
class SongController(
    val songService: SongService
) {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Song {
        return songService.findById(id)
    }

    @PostMapping
    fun createAlbum(@RequestBody song: Song): Song {
        return songService.create(song)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        songService.deleteById(id)
    }

    @GetMapping
    fun findAll(): List<Song> {
        return songService.findAll()
    }
}