@file:Suppress("FunctionName")
package se.newton.musicwiki.persistence.repositories

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import se.newton.musicwiki.persistence.enums.ArtistType
import se.newton.musicwiki.persistence.models.Album
import se.newton.musicwiki.persistence.models.AlbumSong
import se.newton.musicwiki.persistence.models.Artist
import se.newton.musicwiki.persistence.models.Song
import javax.persistence.EntityManager
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Suppress("LeakingThis")
class `Album Repository Integration`(
  @Autowired val albumRepository: AlbumRepository,
  @Autowired val albumSongRepository: AlbumSongRepository,
  @Autowired val artistRepository: ArtistRepository,
  @Autowired val songRepository: SongRepository,
  @Autowired val entityManager: EntityManager,
) {
  val albumRepositoryHelper: RepoTestHelper<Album> = RepoTestHelper(albumRepository)
  val albumSongRepositoryHelper: RepoTestHelper<AlbumSong> = RepoTestHelper(albumSongRepository)
  val artistRepositoryHelper: RepoTestHelper<Artist> = RepoTestHelper(artistRepository)
  val songRepositoryHelper: RepoTestHelper<Song> = RepoTestHelper(songRepository)

  @Test
  @Suppress("SpellCheckingInspection")
  fun `Can save when all required fields (name) are not null`() {
    // Arrange
    val album = Album("Alice in Thunderdome")

    // Act
    albumRepository.saveAndFlush(album)
    entityManager.clear()

    // Assert
    albumRepositoryHelper.assertCount(1)
    val saved = albumRepositoryHelper.retrieveAndAssert(album)

    // Assert - Not null checks
    assertNotNull(saved.name)
    assertNotNull(saved.id)

    // Assert - Value checks
    assertEquals(saved.id, album.id)
    assertEquals(saved.name, album.name)
    assertThat(saved.artists).isEmpty()
    assertThat(saved.songs).isEmpty()
  }

  @Test
  fun `Can not save when name is null`() {
    // Arrange
    val album = Album(null)

    // Act and Assert
    albumRepositoryHelper.assertMissingRequiredField(album, "name")
  }

  @Test
  @Suppress("SpellCheckingInspection")
  fun `Can cascade save an artist`() {
    // Arrange
    val artist = Artist("Rob Sonic", ArtistType.PERSON)
    val album = Album("Alice in Thunderdome")
    album.artists.add(artist)

    // Act
    albumRepository.saveAndFlush(album)

    // Assert
    albumRepositoryHelper.assertCount(1)
    artistRepositoryHelper.assertCount(1)
    val savedAlbum = albumRepositoryHelper.retrieveAndAssert(album)
    assertThat(savedAlbum.artists).contains(artist)
  }

  @Test
  @Suppress("SpellCheckingInspection")
  fun `Removing an album does not remove the artist`() {
    // Arrange
    val artist = Artist("Rob Sonic", ArtistType.PERSON)
    val album = Album("Alice in Thunderdome")
    album.artists.add(artist)

    // Act and Assert 1 - Save and assert saved
    albumRepository.saveAndFlush(album)
    albumRepositoryHelper.assertCount(1)
    artistRepositoryHelper.assertCount(1)
    val savedAlbum = albumRepositoryHelper.retrieveAndAssert(album)
    assertThat(savedAlbum.artists).contains(artist)
    entityManager.clear()

    // Act and assert 2 - Delete and assert album deleted and artist not deleted
    val albumId = savedAlbum.id ?: throw AssertionError("Saved album doesn't have an id")
    albumRepository.deleteById(albumId)
    albumRepositoryHelper.assertEmpty()
    artistRepositoryHelper.assertCount(1)
  }

  @Test
  @Suppress("SpellCheckingInspection")
  fun `Can cascade save a song`() {
    // Arrange
    val song = Song("Pep Rally")
    val album = Album("Alice in Thunderdome")
    val albumSong = AlbumSong(2, album, song)
    album.songs.add(albumSong)

    // Act
    albumRepository.saveAndFlush(album)

    // Assert
    albumRepositoryHelper.assertCount(1)
    songRepositoryHelper.assertCount(1)
    val savedAlbum = albumRepositoryHelper.retrieveAndAssert(album)
    assertThat(savedAlbum.songs).contains(albumSong)
  }

  @Test
  @Suppress("SpellCheckingInspection")
  fun `Removing an album removes albumSong from database`() {
    // Arrange
    val song = Song("Pep Rally")
    val album = Album("Alice in Thunderdome")
    val albumSong = AlbumSong(2, album, song)
    album.songs.add(albumSong)

    // Act and Assert 1 - Save and assert saved
    albumRepository.saveAndFlush(album)
    albumRepositoryHelper.assertCount(1)
    songRepositoryHelper.assertCount(1)
    val savedAlbum = albumRepositoryHelper.retrieveAndAssert(album)
    assertThat(savedAlbum.songs).contains(albumSong)

    // Act and assert 2 - Delete and assert album and album song deleted
    albumRepositoryHelper.deleteEntityById(savedAlbum)
    entityManager.flush()
    entityManager.clear()

    albumRepositoryHelper.assertEmpty()
    albumSongRepositoryHelper.assertEmpty()
  }

  @Test
  @Suppress("SpellCheckingInspection")
  fun `Removing an album does not remove song from database`() {
    // Arrange
    val song = Song("Pep Rally")
    val album = Album("Alice in Thunderdome")
    val albumSong = AlbumSong(2, album, song)
    album.songs.add(albumSong)

    // Act and Assert 1 - Save and assert saved
    albumRepository.saveAndFlush(album)
    albumRepositoryHelper.assertCount(1)
    songRepositoryHelper.assertCount(1)
    val savedAlbum = albumRepositoryHelper.retrieveAndAssert(album)
    assertThat(savedAlbum.songs).contains(albumSong)

    // Act and assert 2 - Delete and assert album deleted but song not deleted
    albumRepositoryHelper.deleteEntityById(savedAlbum)
    albumRepository.flush()
    entityManager.clear()

    albumRepositoryHelper.assertEmpty()
    songRepositoryHelper.assertCount(1)
  }
}