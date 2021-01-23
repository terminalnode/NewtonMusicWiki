@file:Suppress("FunctionName")
package se.newton.musicwiki.persistence.repositories

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import se.newton.musicwiki.persistence.models.Album
import javax.persistence.EntityManager
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJpaTest
class `Album Repository Integration`(
    @Autowired val albumRepository: AlbumRepository,
    @Autowired val entityManager: EntityManager,
) {
    val albumRepositoryHelper: RepoTestHelper<Album> = RepoTestHelper(albumRepository)

    @Test
    @Suppress("SpellCheckingInspection")
    fun `Can save when all required fields (name) are not null`() {
        // Arrange
        val album = Album()
        album.name = "Alice in Thunderdome"

        // Act
        albumRepository.saveAndFlush(album)
        entityManager.clear()

        // Assert
        val saved = albumRepositoryHelper.retrieveAndAssert(album)

        // Assert - Not null checks
        assertNotNull(saved.name)
        assertNotNull(saved.id)
        assertNotNull(saved.artists)
        assertNotNull(saved.songs)

        // Assert - Value checks
        assertEquals(saved.id, album.id)
        assertEquals(saved.name, album.name)
        //assertThat(saved.artists).isEmpty() // TODO Investigate why Album -> Artists doesn't work
        //assertThat(saved.songs).isEmpty() // TODO Investigate why Album -> Songs doesn't work
    }

    @Test
    fun `Can not save when name is null`() {
        // Arrange
        val album = Album()
        album.name = null

        // Act and Assert
        albumRepositoryHelper.assertMissingRequiredField(album, "name")
    }
}