package se.newton.musicwiki.persistence.repositories

import org.assertj.core.api.Assertions.assertThat
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import se.newton.musicwiki.persistence.IdBasedEntity
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class RepoTestHelper<EntityType>(
    private val repository: JpaRepository<EntityType, Long>
) where EntityType : IdBasedEntity {
    /**
     * Assert that an exception is thrown when trying to save an entity with non-null field set to null.
     * @param entity The entity we're supposed to fail to save
     * @param fieldName The not-null field name that's null
     */
    fun assertMissingRequiredField(entity: EntityType, fieldName: String) {
        val e = assertFailsWith<DataIntegrityViolationException> {
            repository.save(entity)
        }

        assertThat(e.message)
            .contains("not-null property references a null or transient value : ${entity.javaClass.name}.${fieldName}")
    }

    /**
     * Retrieve an entity from the database, throw an AssertionError if the entity does not exist in the database.
     * Before retrieval a not null check will be performed on the entity id
     * @param entity The entity to fetch from the database.
     */
    fun retrieveAndAssert(entity: EntityType): EntityType {
        assertNotNull(entity.id)

        return repository.findByIdOrNull(entity.id)
            ?: throw AssertionError("Could not find entity with id ${entity.id}")
    }
}