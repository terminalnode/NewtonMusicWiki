package se.newton.musicwiki.dto.mapper

import org.springframework.stereotype.Component

@Component
class DtoMapper(val mappers: List<DtoMapping<*, *>>) {
  fun <Destination: Any, Origin: Any> map(origin: Origin): Destination {
    val mapper = mappers.filterIsInstance<DtoMapping<Origin, Destination>>()
      .firstOrNull() ?: throw Exception("No suitable mapper found")
    return mapper.map(origin)
  }

  fun <Destination: Any, Origin: Any> map(origin: Collection<Origin>): List<Destination> {
    val mapper = mappers.filterIsInstance<DtoMapping<Origin, Destination>>()
      .firstOrNull() ?: throw Exception("No suitable mapper found")
    return mapper.map(origin)
  }
}