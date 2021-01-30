package se.newton.musicwiki.persistence

abstract class IdBasedEntity {
  abstract var id: Long?

  override fun equals(other: Any?): Boolean {
    println("Equals!")
    val o: Any = other ?: return false
    println("other is not null!")
    if (this::class != o::class) return false
    println("other is same class!")
    if (this.id == null) return false
    println("this id is not null!")

    val idBasedEntity = o as IdBasedEntity
    println("this: ${this.id}")
    println("other: ${o.id}")
    println("Are they equal? :thonk: ${id == idBasedEntity.id}")
    return id == idBasedEntity.id
  }

  override fun hashCode(): Int {
    // Null safe kotlin hash code method, won't cause null pointer exception
    return id.hashCode();
  }
}