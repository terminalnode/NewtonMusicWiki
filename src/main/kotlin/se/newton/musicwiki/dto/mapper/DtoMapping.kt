package se.newton.musicwiki.dto.mapper

data class DtoMapping<OriginClass, DestinationClass>(
    val mapFunction: (origin: OriginClass) -> DestinationClass
) {
    fun map(origin: OriginClass): DestinationClass {
        return mapFunction(origin)
    }

    fun map(origins: List<OriginClass>): List<DestinationClass> {
        return origins.mapNotNull { map(it) }
    }
}