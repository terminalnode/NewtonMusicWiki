package se.newton.musicwiki.service.crud.implementation

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import se.newton.musicwiki.persistence.models.Song
import se.newton.musicwiki.persistence.repositories.SongRepository
import se.newton.musicwiki.service.crud.SongService
import javax.persistence.EntityNotFoundException

@Service
class SongServiceImpl(
    val songRepository: SongRepository
) : SongService {
    override fun create(song: Song): Song {
        song.id = 0;

        // Only save primary fields
        song.artists = mutableListOf()
        song.albums = mutableListOf()

        return songRepository.save(song)
    }

    override fun update(song: Song): Song {
        existsById(song.id)
        return songRepository.save(song)
    }

    override fun findById(id: Long): Song {
        return songRepository.findByIdOrNull(id)
            ?: throw EntityNotFoundException("No album by that id exists")
    }

    override fun existsById(id: Long?): Boolean {
        id ?: throw EntityNotFoundException("No id specified")
        return songRepository.existsById(id)
    }

    override fun findAll(): List<Song> {
        return songRepository.findAll()
    }

    override fun deleteById(id: Long) {
        existsById(id)
        songRepository.deleteById(id)
    }
}