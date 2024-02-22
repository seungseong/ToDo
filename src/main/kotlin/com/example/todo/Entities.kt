package com.example.todo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name="todo")
class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    var id: Long? = null
    var title: String? = null
    var description: String? = null
    var finished: Boolean?= null
    var createdAt: LocalDateTime?= LocalDateTime.now()
}