package com.example.todo

import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/todo")
class TodoController(val repository: TodoRepository) {
    @GetMapping("/")
    fun getTodos() = repository.findAll()

    @GetMapping("/{id}")
    fun getTodo(@PathVariable id: Long) =
        repository.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This todo does not exist")

    @PostMapping
    fun createTodo(@RequestBody todo: Todo): Todo {
        repository.save(todo)
        return todo
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateTodo(@RequestBody todo: Todo, @PathVariable id: Long){
        var target: Todo = repository.findById(id).get()
        target.title = todo.title
        target.description = todo.description
        target.finished = todo.finished

        repository.save(target)
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long){
        repository.deleteById(id)
    }
}
