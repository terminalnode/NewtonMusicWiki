package se.newton.musicwiki.persistence.models

import se.newton.musicwiki.persistence.enums.ArtistType
import javax.persistence.*

@Entity
@Table(
    name = "artist",
    indexes = [
        Index(name = "IDX_ARTIST_NAME", columnList = "name", unique = true)
    ])
open class Artist {
    @get:Id
    @get:Column(
        name = "id",
        nullable = false,
        columnDefinition = "BIGSERIAL")
    @get:GeneratedValue(strategy = GenerationType.SEQUENCE)
    open var id: Long? = null

    @get:Column(
        name = "name",
        nullable = false,
        columnDefinition = "VARCHAR(64)")
    open var name: String? = null

    @get:Column(
        name = "artist_type",
        nullable = false,
        columnDefinition = "NUMERIC(1)"
    )
    open var artistType: ArtistType? = null

    @get:ManyToMany(cascade = [ CascadeType.PERSIST, CascadeType.REFRESH ])
    @get:JoinTable(
        name = "artist_song",
        joinColumns = [ JoinColumn(name = "artist_id") ],
        inverseJoinColumns = [ JoinColumn(name = "song_id") ],
        foreignKey = ForeignKey(name = "fk_artist_song_artist"),
        inverseForeignKey = ForeignKey(name = "fk_artist_song_song")
    )
    open var songs: List<Song>? = null

    @get:ManyToMany(mappedBy = "artists")
    open var albums: List<Album>? = null
}