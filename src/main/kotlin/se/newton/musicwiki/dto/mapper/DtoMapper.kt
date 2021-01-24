package se.newton.musicwiki.dto.mapper

import org.springframework.stereotype.Component
import se.newton.musicwiki.dto.artist.ArtistAlbumDto
import se.newton.musicwiki.dto.artist.ArtistDto
import se.newton.musicwiki.dto.artist.ArtistSongDto
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song

@Component
class DtoMapper(
    val toArtistDto: DtoMapping<Artist, ArtistDto>,
    val toArtistAlbumDto: DtoMapping<Album, ArtistAlbumDto>,
    val toArtistSongDto: DtoMapping<Song, ArtistSongDto>,
) {
}