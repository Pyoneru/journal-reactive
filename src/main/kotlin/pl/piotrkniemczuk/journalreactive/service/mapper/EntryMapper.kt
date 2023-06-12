package pl.piotrkniemczuk.journalreactive.service.mapper

import org.springframework.stereotype.Component
import pl.piotrkniemczuk.journalreactive.model.Entry
import pl.piotrkniemczuk.journalreactive.model.EntryDTO
import pl.piotrkniemczuk.journalreactive.utils.Utils
import java.util.*

@Component
class EntryMapper(
        private val dateMapper: DateMapper
) {

    fun toEntity(dto: EntryDTO): Entry = Entry(
            id = UUID.fromString(dto.id),
            title = dto.title,
            content = dto.content,
            createdAt = if(dto.createdAt != null) dateMapper.toDate(dto.createdAt!!) else Utils.now()
    )

    fun toDTO(entity: Entry): EntryDTO = EntryDTO(
            id = entity.id.toString(),
            title = entity.title,
            content = entity.content,
            createdAt = dateMapper.toString(entity.createdAt)
    )
}