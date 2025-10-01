package org.example.todoapp.controllers;

import jakarta.validation.Valid;
import org.example.todoapp.dtos.TodoRequestDto;
import org.example.todoapp.model.Todo;
import org.example.todoapp.service.TodoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String index() {
        return "Hello World!";
    }

    @PostMapping("/add")
    public Todo addTodo(@RequestBody @Valid TodoRequestDto todo) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return todoService.addTodo(todo, auth.getName());
    }

    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable UUID id) {
        return todoService.getTodo(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable UUID id) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return todoService.deleteTodo(id, auth.getName());
    }

    @PutMapping("/{id}")
    public String updateTodo(@PathVariable UUID id, @RequestBody @Valid TodoRequestDto todo) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return todoService.updateTodo(id, todo, auth.getName());
    }

    @GetMapping("/getAll")
    public List<Todo> getAllTodos() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return todoService.getAllTodos(auth.getName());
    }

    @GetMapping("/me")
    public String me() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
