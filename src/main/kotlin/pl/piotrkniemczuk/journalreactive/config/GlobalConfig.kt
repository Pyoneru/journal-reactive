package pl.piotrkniemczuk.journalreactive.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.text.SimpleDateFormat

@Configuration
class GlobalConfig {

    @Bean
    fun dateFormat(): SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

}