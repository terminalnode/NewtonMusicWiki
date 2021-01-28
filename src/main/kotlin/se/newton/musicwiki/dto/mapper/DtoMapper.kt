package se.newton.musicwiki.dto.mapper

import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class DtoMapper(val mappers: List<DtoMapping<*, *>>) {
  fun <Destination: Any, Origin: Any> map(
    origin: Origin,
    destinationClass: KClass<Destination>
  ): Destination {
    return mappers
      .filterIsInstance<DtoMapping<Origin, Destination>>()
      .filter { it.destinationClass == destinationClass }
      .first { it.originClass == origin::class }
      .map(origin)
  }

  fun <Destination: Any, Origin: Any> map(
    origin: Collection<Origin>,
    destinationClass: KClass<Destination>
  ): List<Destination> {
    if (origin.isEmpty()) return mutableListOf()
    return mappers
      .filterIsInstance<DtoMapping<Origin, Destination>>()
      .filter { it.destinationClass == destinationClass }
      .first { it.originClass == origin.first()::class }
      .map(origin)
  }
}