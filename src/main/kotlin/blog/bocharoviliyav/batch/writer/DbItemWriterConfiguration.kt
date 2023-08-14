package blog.bocharoviliyav.batch.writer

import jakarta.persistence.EntityManagerFactory
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import blog.bocharoviliyav.batch.model.Message


@Configuration
class DbItemWriterConfiguration(private val emf: EntityManagerFactory){

    @Bean
    fun itemWriter(): JpaItemWriter<Message> {
        val writer: JpaItemWriter<Message> = JpaItemWriter<Message>()
        writer.setEntityManagerFactory(emf)
        return writer
    }
}