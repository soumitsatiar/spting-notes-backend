package org.example.todoapp.service;

import org.example.todoapp.dtos.TodoRequestDto;
import org.example.todoapp.model.Todo;
import org.example.todoapp.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public Todo addTodo(TodoRequestDto todo, String username) {
        Todo newTodo = new Todo();
        newTodo.setTitle(todo.getTitle());
        newTodo.setDescription(todo.getDescription());
        newTodo.setCompleted(false);
        newTodo.setCreatedBy(username);
        return todoRepository.save(newTodo);
    }

    @Transactional(readOnly = true)
    public Todo getTodo(UUID id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Todo> getAllTodos(String username) {
        return todoRepository.findTodosByCreatedBy(username);
    }

    @Transactional
    public String deleteTodo(UUID id, String username) {
        Todo todo =  todoRepository.findById(id).orElse(null);
        if (todo == null) return "Invalid Id";
        if (!username.equals(todo.getCreatedBy())) return "Invalid User";
        todoRepository.deleteById(id);
        return id + " Deleted Successfully";
    }

    @Transactional
    public String updateTodo(UUID id, TodoRequestDto todoRequestDto, String username) {
        Todo todo =  todoRepository.findById(id).orElse(null);
        if (todo == null) return "Invalid Id";
        if (!username.equals(todo.getCreatedBy())) return "Invalid User";
        todo.setTitle(todoRequestDto.getTitle());
        todo.setDescription(todoRequestDto.getDescription());
        todoRepository.save(todo);
        return "Updated Successfully";
    }
}
