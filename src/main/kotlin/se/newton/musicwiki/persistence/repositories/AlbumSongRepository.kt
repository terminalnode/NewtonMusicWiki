package se.newton.musicwiki.persistence.repositories

import org.springframework.data.jpa.repository.JpaRepository
import se.newton.musicwiki.persistence.models.AlbumSong

interface AlbumSongRepository : JpaRepository<AlbumSong, Long>