@file:Suppress("FunctionName")
package se.newton.musicwiki.persistence.repositories

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import se.newton.musicwiki.persistence.models.Song
import javax.persistence.EntityManager
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class `Song Repository Integration`(
    @Autowired val songRepository: SongRepository,
    @Autowired val entityManager: EntityManager,
) {
    val songRepositoryHelper: RepoTestHelper<Song> = RepoTestHelper(songRepository)

    @Test
    fun `Can save when all required fields (name) are not null`() {
        // Arrange
        val song = Song("Pep Rally")

        // Act
        songRepository.saveAndFlush(song)
        entityManager.clear()

        // Assert
        val saved = songRepositoryHelper.retrieveAndAssert(song)

        // Assert - Not null checks
        assertNotNull(saved.id)
        assertNotNull(saved.name)

        // Assert - Value checks
        assertEquals(saved.id, song.id)
        assertEquals(saved.name, song.name)
        assertThat(saved.albums).isEmpty()
        assertThat(saved.artists).isEmpty()
    }

    @Test
    fun `Can not save when name is null`() {
        // Arrange
        val song = Song(null)

        // Act and Assert
        songRepositoryHelper.assertMissingRequiredField(song, "name")
    }
}