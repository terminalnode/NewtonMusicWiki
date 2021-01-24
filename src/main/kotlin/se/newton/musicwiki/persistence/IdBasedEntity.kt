package se.newton.musicwiki.persistence

abstract class IdBasedEntity {
    abstract var id: Long?

    override fun equals(other: Any?): Boolean {
        val o: Any = other ?: return false
        if (this::class != o::class) return false
        if (this.id == null) return false

        val idBasedEntity = o as IdBasedEntity
        return id == idBasedEntity.id
    }

    override fun hashCode(): Int {
        // Null safe kotlin hash code method, won't cause null pointer exception
        return id.hashCode();
    }
}