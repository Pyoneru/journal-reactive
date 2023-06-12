package pl.piotrkniemczuk.journalreactive.model

data class EntryDTO(
        var id: String?,
        var title: String,
        var content: String,
        var createdAt: String?
)