package se.newton.musicwiki.dto.mapper

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import se.newton.musicwiki.dto.album.*
import se.newton.musicwiki.dto.album.mapAlbumArtistToDto
import se.newton.musicwiki.dto.artist.*
import se.newton.musicwiki.dto.song.*
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

    @Bean
    fun albumMapper(): DtoMapping<Album, AlbumDto> {
        return DtoMapping { mapAlbumToDto(it) }
    }

    @Bean
    fun albumArtistMapper(): DtoMapping<Artist, AlbumArtistDto> {
        return DtoMapping { mapAlbumArtistToDto(it) }
    }

    /*@Bean
    fun albumSongMapper(): DtoMapping<Song, AlbumSongDto> {
        return DtoMapping { mapAlbumSongToDto(it) }
    }*/

    @Bean
    fun songMapper(): DtoMapping<Song, SongDto> {
        return DtoMapping { mapSongToDto(it) }
    }

    @Bean
    fun songArtistMapper(): DtoMapping<Artist, SongArtistDto> {
        return DtoMapping { mapSongArtistToDto(it) }
    }

    @Bean
    fun songAlbumMapper(): DtoMapping<Album, SongAlbumDto> {
        return DtoMapping { mapSongAlbumToDto(it) }
    }

}

