@file:Suppress("FunctionName")
package se.newton.musicwiki.persistence.repositories

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Artist
import javax.persistence.EntityManager
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJpaTest
class `Artist Repository Integration`(
    @Autowired val artistRepository: ArtistRepository,
    @Autowired val entityManager: EntityManager,
) {
    val artistRepositoryHelper: RepoTestHelper<Artist> = RepoTestHelper(artistRepository)

    @Test
    fun `Can save when all required fields (name, artistType) are not null`() {
        // Arrange
        val artist = Artist()
        artist.name = "Rob Sonic"
        artist.artistType = ArtistType.PERSON

        // Act
        artistRepository.saveAndFlush(artist)
        entityManager.clear()

        // Assert
        val saved = artistRepositoryHelper.retrieveAndAssert(artist)

        // Assert - Not null checks
        assertNotNull(saved.id)
        assertNotNull(saved.name)
        assertNotNull(saved.albums)
        assertNotNull(saved.songs)

        // Assert - Value checks
        assertEquals(saved.id, artist.id)
        assertEquals(saved.name, artist.name)
        assertEquals(saved.artistType, artist.artistType)
        //assertThat(saved.albums).isEmpty() // TODO Investigate why Artist -> Albums doesn't work
        //assertThat(saved.songs).isEmpty() // TODO Investigate why Artists -> Songs doesn't work
    }

    @Test
    fun `Can not save when name is null`() {
        // Arrange
        val artist = Artist()
        artist.name = null
        artist.artistType = ArtistType.PERSON

        // Act and Assert
        artistRepositoryHelper.assertMissingRequiredField(artist, "name")
    }

    @Test
    fun `Can not save when artistType is null`() {
        // Arrange
        val artist = Artist()
        artist.name = "Rob Sonic"
        artist.artistType = null

        // Act and Assert
        artistRepositoryHelper.assertMissingRequiredField(artist, "artistType")
    }
}