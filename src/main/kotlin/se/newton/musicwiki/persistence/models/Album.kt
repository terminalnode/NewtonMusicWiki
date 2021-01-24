package se.newton.musicwiki.persistence.models

import se.newton.musicwiki.persistence.IdBasedEntity
import javax.persistence.*

@Entity
@Table(
    name = "album",
    indexes = [
        Index(name = "IDX_ALBUM_NAME", columnList = "name", unique = true)
    ])
class Album(inName: String? = null): IdBasedEntity() {
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
    var name: String? = inName

    @get:ManyToMany(mappedBy = "albums", fetch = FetchType.EAGER, cascade = [ CascadeType.ALL ])
    var artists: MutableList<Artist> = mutableListOf()

    @get:ManyToMany(fetch = FetchType.EAGER, cascade = [ CascadeType.PERSIST, CascadeType.MERGE ])
    @get:JoinTable(
        name = "album_song",
        joinColumns = [ JoinColumn(name = "album_id", nullable = false) ],
        inverseJoinColumns = [ JoinColumn(name = "song_id", nullable = false) ],
        foreignKey = ForeignKey(name = "fk_album_song_album"),
        inverseForeignKey = ForeignKey(name = "fk_album_song_song")
    )
    var songs: MutableList<Song> = mutableListOf()
}