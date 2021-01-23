package se.newton.musicwiki.persistence.models

import javax.persistence.*

@Entity
@Table(
    name = "song",
    indexes = [
        Index(name = "IDX_SONG_NAME", columnList = "name", unique = true)
    ])
open class Song {
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

    @get:ManyToMany(mappedBy = "albums")
    open var albums: List<Album>? = null

    @get:ManyToMany(mappedBy = "artists")
    open var artists: List<Artist>? = null
}