package se.newton.musicwiki.persistence.models

import javax.persistence.*

@Entity
@Table(
    name = "album",
    indexes = [
        Index(name = "IDX_ALBUM_NAME", columnList = "name", unique = true)
    ])
open class Album {
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
    open var name: String? = null;

    @get:ManyToMany(cascade = [ CascadeType.PERSIST, CascadeType.REFRESH ])
    @get:JoinTable(
        name = "album_artist",
        joinColumns = [ JoinColumn(name = "album_id") ],
        inverseJoinColumns = [ JoinColumn(name = "artist_id") ],
        foreignKey = ForeignKey(name = "fk_album_artist_album"),
        inverseForeignKey = ForeignKey(name = "fk_album_artist_artist")
    )
    open var artists: MutableList<Artist>? = mutableListOf()

    @get:ManyToMany(cascade = [ CascadeType.PERSIST, CascadeType.REFRESH ])
    @get:JoinTable(
        name = "album_song",
        joinColumns = [ JoinColumn(name = "album_id") ],
        inverseJoinColumns = [ JoinColumn(name = "song_id") ],
        foreignKey = ForeignKey(name = "fk_album_song_album"),
        inverseForeignKey = ForeignKey(name = "fk_album_song_song")
    )
    open var songs: MutableList<Song>? = mutableListOf()
}