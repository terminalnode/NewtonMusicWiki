package se.newton.musicwiki.dto.mapper

import kotlin.reflect.KClass

data class DtoMapping<OriginClass: Any, DestinationClass: Any>(
  val mapFunction: (origin: OriginClass) -> DestinationClass,
  val originClass: KClass<OriginClass>,
  val destinationClass: KClass<DestinationClass>
) {
  fun map(origin: OriginClass): DestinationClass {
    return mapFunction(origin)
  }

  fun map(origins: Collection<OriginClass>): List<DestinationClass> {
    return origins.map { map(it) }
  }
}