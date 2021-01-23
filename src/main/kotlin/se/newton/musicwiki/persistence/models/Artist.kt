package se.newton.musicwiki.persistence.models

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Table(name = "artist")
open class Artist {
    @Id
    @Column(
        name = "id",
        columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    open var id: Long? = null
}