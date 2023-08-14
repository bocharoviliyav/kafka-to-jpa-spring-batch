package blog.bocharoviliyav.batch.processor

import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component
import blog.bocharoviliyav.batch.model.Message

@Component
class TransformationItemProcessorConfiguration : ItemProcessor<String, Message> {

    override fun process(item: String): Message {
        println("[processor]$item")

        return Message(message = item)
    }
}