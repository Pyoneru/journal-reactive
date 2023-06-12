package pl.piotrkniemczuk.journalreactive.service.simple

import org.springframework.stereotype.Service
import pl.piotrkniemczuk.journalreactive.handler.PaginationHandler
import pl.piotrkniemczuk.journalreactive.model.DeleteOperationResult
import pl.piotrkniemczuk.journalreactive.model.EntryDTO
import pl.piotrkniemczuk.journalreactive.repository.EntryRepository
import pl.piotrkniemczuk.journalreactive.service.EntryService
import pl.piotrkniemczuk.journalreactive.service.mapper.EntryMapper
import pl.piotrkniemczuk.journalreactive.service.throwable.EntryNotFoundException
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.*

@Service
class SimpleEntryService(
        val entryRepository: EntryRepository,
        val entryMapper: EntryMapper
) : EntryService {

    override fun save(input: EntryDTO): Mono<EntryDTO> {
        return Mono.just(input)
                .flatMap {
                    it.id = UUID.randomUUID().toString()
                    it.createdAt = null

                    val entity = entryMapper.toEntity(it)

                    entryRepository.save(entity)
                            .map(entryMapper::toDTO)
                }
    }

    override fun update(input: EntryDTO, id: UUID): Mono<EntryDTO> {
        return entryRepository.findById(id)
                .switchIfEmpty {
                    Mono.error(EntryNotFoundException())
                }
                .flatMap { entity ->
                    entity.title = input.title
                    entity.content = input.content

                    entryRepository.save(entity).map(entryMapper::toDTO)
                }
    }

    override fun findAll(pagination: PaginationHandler): Flux<EntryDTO> {
        return entryRepository.findAll()
                .skip(pagination.size * pagination.page)
                .take(pagination.size)
                .map(entryMapper::toDTO)
    }

    override fun findById(id: UUID): Mono<EntryDTO> {
        return entryRepository.findById(id)
                .switchIfEmpty {
                    Mono.error(EntryNotFoundException())
                }
                .map(entryMapper::toDTO)
    }

    override fun deleteById(id: UUID): Mono<DeleteOperationResult> {
        return entryRepository.existsById(id)
                .flatMap { exists ->
                    if(exists){
                        entryRepository.deleteById(id)
                                .then(Mono.just(DeleteOperationResult(true)))
                    }else{
                        Mono.just(DeleteOperationResult(false))
                    }
                }
    }
}