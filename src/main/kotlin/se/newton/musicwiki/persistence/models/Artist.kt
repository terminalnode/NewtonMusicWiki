package se.newton.musicwiki.persistence.models

import se.newton.musicwiki.persistence.IdBasedEntity
import se.newton.musicwiki.persistence.enums.ArtistType
import javax.persistence.*

@Entity
@Table(
    name = "artist",
    indexes = [
        Index(name = "IDX_ARTIST_NAME", columnList = "name", unique = true)
    ])
class Artist: IdBasedEntity() {
    @get:Id
    @get:Column(
        name = "id",
        nullable = false,
        columnDefinition = "BIGSERIAL")
    @get:GeneratedValue(strategy = GenerationType.SEQUENCE)
    override var id: Long? = null

    @get:Column(
        name = "name",
        nullable = false,
        columnDefinition = "VARCHAR(64)")
    var name: String? = null

    @get:Column(
        name = "artist_type",
        nullable = false,
        columnDefinition = "NUMERIC(1)"
    )
    var artistType: ArtistType? = null

    @get:ManyToMany(cascade = [ CascadeType.PERSIST, CascadeType.REFRESH ])
    @get:JoinTable(
        name = "artist_song",
        joinColumns = [ JoinColumn(name = "artist_id", nullable = false) ],
        inverseJoinColumns = [ JoinColumn(name = "song_id", nullable = false) ],
        foreignKey = ForeignKey(name = "fk_artist_song_artist"),
        inverseForeignKey = ForeignKey(name = "fk_artist_song_song")
    )
    var songs: List<Song>? = null

    @get:ManyToMany
    @get:JoinTable(
        name = "artist_album",
        joinColumns = [ JoinColumn(name = "artist_id", nullable = false) ],
        inverseJoinColumns = [ JoinColumn(name = "album_id", nullable = false) ],
        foreignKey = ForeignKey(name = "fk_artist_album_artist"),
        inverseForeignKey = ForeignKey(name = "fk_artist_album_album")
    )
    var albums: List<Album>? = null
}