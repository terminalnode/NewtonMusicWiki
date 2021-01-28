package se.newton.musicwiki.dto.mapper

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import se.newton.musicwiki.dto.album.*
import se.newton.musicwiki.dto.artist.*
import se.newton.musicwiki.dto.song.*
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.AlbumSong
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song

@Configuration
class DtoMapperConfig {
  @Bean
  fun artistMapper(): DtoMapping<Artist, ArtistDto> {
    return DtoMapping(
      { mapArtistToDto(it) },
      Artist::class,
      ArtistDto::class,
    )
  }

  @Bean
  fun artistAlbumMapper(): DtoMapping<Album, ArtistAlbumDto> {
    return DtoMapping(
      { mapArtistAlbumToDto(it) },
      Album::class,
      ArtistAlbumDto::class
    )
  }

  @Bean
  fun artistSongMapper(): DtoMapping<Song, ArtistSongDto> {
    return DtoMapping(
      { mapArtistSongToDto(it) },
      Song::class,
      ArtistSongDto::class
    )
  }

  @Bean
  fun albumMapper(): DtoMapping<Album, AlbumDto> {
    return DtoMapping(
      { mapAlbumToDto(it) },
      Album::class,
      AlbumDto::class
    )
  }

  @Bean
  fun albumArtistMapper(): DtoMapping<Artist, AlbumArtistDto> {
    return DtoMapping(
      { mapAlbumArtistToDto(it) },
      Artist::class,
      AlbumArtistDto::class
    )
  }

  @Bean
  fun albumSongMapper(): DtoMapping<AlbumSong, AlbumSongDto> {
    return DtoMapping(
      { mapAlbumSongToDto(it) },
      AlbumSong::class,
      AlbumSongDto::class
    )
  }

  @Bean
  fun songMapper(): DtoMapping<Song, SongDto> {
    return DtoMapping(
      { mapSongToDto(it) },
      Song::class,
      SongDto::class
    )
  }

  @Bean
  fun songArtistMapper(): DtoMapping<Artist, SongArtistDto> {
    return DtoMapping(
      { mapSongArtistToDto(it) },
      Artist::class,
      SongArtistDto::class
    )
  }

  @Bean
  fun songAlbumMapper(): DtoMapping<AlbumSong, SongAlbumDto> {
    return DtoMapping(
      { mapSongAlbumToDto(it) },
      AlbumSong::class,
      SongAlbumDto::class
    )
  }
}