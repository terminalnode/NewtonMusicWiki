package se.newton.musicwiki.persistence.models

import javax.persistence.*

@Entity
@Table(name = "album")
open class Album {
    @Id
    @Column(
        name = "id",
        columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open var id: Long? = null
}