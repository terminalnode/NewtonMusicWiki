package se.newton.musicwiki.persistence.models

import se.newton.musicwiki.persistence.IdBasedEntity
import javax.persistence.*

@Entity
@Table(name = "album_song")
class AlbumSong(
  inTrack: Int? = null,
  inAlbum: Album? = null,
  inSong: Song? = null
): IdBasedEntity() {
  @get:Id
  @get:Column(
    name = "id",
    nullable = false,
    columnDefinition = "BIGSERIAL")
  @get:GeneratedValue(strategy = GenerationType.SEQUENCE)
  override var id: Long? = null

  @get:Column(
    name = "track",
    nullable = false,
    columnDefinition = "SMALLINT")
  var track: Int? = inTrack

  @get:ManyToOne(cascade = [ CascadeType.ALL ])
  @get:JoinColumn(
    name = "album_id",
    referencedColumnName = "id",
    nullable = false,
    foreignKey = ForeignKey(name = "fk_album_song_album"),
    columnDefinition = "BIGINT")
  var album: Album? = inAlbum

  @get:ManyToOne(cascade = [ CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE ])
  @get:JoinColumn(
    name = "song_id",
    referencedColumnName = "id",
    nullable = false,
    foreignKey = ForeignKey(name = "fk_album_song_song"),
    columnDefinition = "BIGINT")
  var song: Song? = inSong
}