package blog.bocharoviliyav.batch.model

import jakarta.persistence.*

@Entity
data class Message(
    @Column(name = "message", nullable = false)
    var message: String? = null
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

}