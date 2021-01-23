@file:Suppress("FunctionName")
package se.newton.musicwiki.persistence.repositories

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import se.newton.musicwiki.persistence.models.Song
import javax.persistence.EntityManager
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJpaTest
class `Song Repository Integration`(
    @Autowired val songRepository: SongRepository,
    @Autowired val entityManager: EntityManager,
) {
    val songRepositoryHelper: RepoTestHelper<Song> = RepoTestHelper(songRepository)

    @Test
    fun `Can save when all required fields (name) are not null`() {
        // Arrange
        val song = Song()
        song.name = "Pep Rally"

        // Act
        songRepository.saveAndFlush(song)
        entityManager.clear()

        // Assert
        val saved = songRepositoryHelper.retrieveAndAssert(song)

        // Assert - Not null checks
        assertNotNull(saved.id)
        assertNotNull(saved.name)
        assertNotNull(saved.albums)
        assertNotNull(saved.artists)

        // Assert - Value checks
        assertEquals(saved.id, song.id)
        assertEquals(saved.name, song.name)
        //assertThat(saved.albums).isEmpty() // TODO Investigate why Song -> Albums isn't working
        //assertThat(saved.artists).isEmpty() // TODO Investigate why Song -> Artists isn't working
    }

    @Test
    fun `Can not save when name is null`() {
        // Arrange
        val song = Song()
        song.name = null

        // Act and Assert
        songRepositoryHelper.assertMissingRequiredField(song, "name")
    }
}