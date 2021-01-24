package se.newton.musicwiki.dto.mapper

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import se.newton.musicwiki.dto.artist.*
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song

@Configuration
class DtoMapperConfig(
) {
    @Bean
    fun artistMapper(): DtoMapping<Artist, ArtistDto> {
        return DtoMapping { mapArtistToDto(it) }
    }

    @Bean
    fun artistAlbumMapper(): DtoMapping<Album, ArtistAlbumDto> {
        return DtoMapping { mapArtistAlbumToDto(it) }
    }

    @Bean
    fun artistSongMapper(): DtoMapping<Song, ArtistSongDto> {
        return DtoMapping { mapArtistSongToDto(it) }
    }
}