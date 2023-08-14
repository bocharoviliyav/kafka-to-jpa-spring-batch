package blog.bocharoviliyav.batch.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import blog.bocharoviliyav.batch.model.Message

@Repository
interface MessageRepository: JpaRepository<Message,Long> {
}