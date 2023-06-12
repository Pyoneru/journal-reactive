package pl.piotrkniemczuk.journalreactive.handler

data class PaginationHandler(
        val page: Long = 0L,
        val size: Long = 10L
)