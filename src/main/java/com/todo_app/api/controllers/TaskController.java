package com.todo_app.api.controllers;

import com.todo_app.api.models.Task;
import com.todo_app.api.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TodoService todoService;

    @GetMapping()
    public List<Task> findAllByDonoId(
            @RequestParam(required = true) String donoId,
            @RequestParam(required = true) boolean removido_ne,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<String> status,
            @RequestParam(required = false) List<String> prioridade,
            @RequestParam(required = false) String _sort,
            @RequestParam(required = false) String _order
    ) {
        String sortField = _sort != null ? _sort : "dataCriacao";
        String sortOrder = _order != null ? _order : "desc";

        return todoService.findAllByDono(donoId, removido_ne, search, status, prioridade, sortField, sortOrder);
    }

    @PostMapping
    public Task inserir(@RequestBody Task task) {
        return todoService.inserir(task);
    }

    @PutMapping("/{id}")
    public Task atualizar(@RequestBody Task task) {
        return todoService.atualizar(task);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id) {
        todoService.remover(id);
    }

}
