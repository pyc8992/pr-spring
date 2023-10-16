package domain.dao.test

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "foo")
class Foo (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long = Long.MIN_VALUE,
  var bar1: String,
  var bar2: String,
  var bar3: String,
  @Column(name = "created_at")
  var createdAt: LocalDateTime,
  @Column(name = "updated_at")
  var modifiedAt: LocalDateTime
)
