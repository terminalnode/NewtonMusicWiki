package se.newton.musicwiki.persistence.models

import se.newton.musicwiki.persistence.IdBasedEntity
import javax.persistence.*

@Entity
@Table(
    name = "song",
    indexes = [
        Index(name = "IDX_SONG_NAME", columnList = "name")
    ])
class Song(inName: String? = null): IdBasedEntity() {
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
        updatable = true,
        columnDefinition = "VARCHAR(64)")
    var name: String? = inName

    @get:OneToMany(
        mappedBy = "song",
        fetch = FetchType.EAGER,
        cascade = [ CascadeType.ALL ],)
    var albums: MutableList<AlbumSong> = mutableListOf()

    @get:ManyToMany(mappedBy = "songs", fetch = FetchType.EAGER)
    var artists: MutableList<Artist> = mutableListOf()
}