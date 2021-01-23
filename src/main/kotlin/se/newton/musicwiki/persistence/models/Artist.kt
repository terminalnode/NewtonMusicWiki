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
}