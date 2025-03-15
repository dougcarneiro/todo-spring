package com.todo_app.api.services;

import com.todo_app.api.models.Task;
import com.todo_app.api.repositories.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private ITaskRepository taskRepository;

    public List<Task> findAllByDonoId(String donoId) {
        return taskRepository.findAllByDonoId(donoId);
    }

    public List<Task> findByTituloOrDescricao(String donoId, String search) {
        return taskRepository.findByDonoIdAndTituloOrDescricao(donoId, search);
    }

    public Task inserir(Task task) {
        return taskRepository.save(task);
    }

    public Task atualizar(Task task) {
        return taskRepository.save(task);
    }

    public Task remover(Task task) {
        task.setRemovido(true);
        return taskRepository.save(task);
    }

}
