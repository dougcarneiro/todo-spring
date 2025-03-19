package com.todo_app.api.controllers;

import com.todo_app.api.models.Task;
import com.todo_app.api.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TodoService todoService;

    @GetMapping()
    public ResponseEntity<List<Task>> findAllByDonoId(
            @RequestParam(required = true) String donoId,
            @RequestParam(required = true) boolean removido_ne,
            @RequestParam(required = false) String q,
            @RequestParam(required = false) List<String> status,
            @RequestParam(required = false) List<String> prioridade,
            @RequestParam(required = false) String _sort,
            @RequestParam(required = false) String _order
    ) {
        String sortField = _sort != null ? _sort : "dataCriacao";
        String sortOrder = _order != null ? _order : "desc";
        String search = q != null ? "%" + q + "%" : "";

        List<Task> tasks = todoService.findAllByDono(donoId, removido_ne, search, status, prioridade, sortField, sortOrder);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> inserir(@RequestBody Task task) {
        Task newTask = todoService.inserir(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> atualizar(@RequestBody Task task) {
        Task updatedTask = todoService.atualizar(task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable long id) {
        todoService.remover(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
