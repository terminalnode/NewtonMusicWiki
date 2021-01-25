package se.newton.musicwiki.dto.mapper

import org.springframework.stereotype.Component
import se.newton.musicwiki.dto.album.AlbumArtistDto
import se.newton.musicwiki.dto.album.AlbumDto
import se.newton.musicwiki.dto.album.AlbumSongDto
import se.newton.musicwiki.dto.artist.ArtistAlbumDto
import se.newton.musicwiki.dto.artist.ArtistDto
import se.newton.musicwiki.dto.artist.ArtistSongDto
import se.newton.musicwiki.dto.song.SongAlbumDto
import se.newton.musicwiki.dto.song.SongArtistDto
import se.newton.musicwiki.dto.song.SongDto
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song

@Component
class DtoMapper(
    val toArtistDto: DtoMapping<Artist, ArtistDto>,
    val toArtistAlbumDto: DtoMapping<Album, ArtistAlbumDto>,
    val toArtistSongDto: DtoMapping<Song, ArtistSongDto>,
    val toAlbumDto: DtoMapping<Album, AlbumDto>,
    val toAlbumArtistDto: DtoMapping<Artist, AlbumArtistDto>,
    val toAlbumSongDto: DtoMapping<Song, AlbumSongDto>,
    val toSongDto: DtoMapping<Song, SongDto>,
    val toSongArtistDto: DtoMapping<Artist, SongArtistDto>,
    val toSongAlbumDto: DtoMapping<Album, SongAlbumDto>,
) {
}