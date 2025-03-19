package com.todo_app.api.services;

import com.todo_app.api.models.Task;
import com.todo_app.api.repositories.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private ITaskRepository taskRepository;

    public List<Task> findAllByDono(String donoId, boolean removido_ne, String search, List<String> status, List<String> prioridade, String sortField, String sortOrder) {
        Sort sort = (sortField != null && sortOrder != null)
                ? Sort.by(Sort.Order.by(sortField).with(Sort.Direction.fromString(sortOrder)))
                : Sort.unsorted();

        // Se todas as opções forem fornecidas
        if (search != null && !search.isEmpty() && status != null && !status.isEmpty() && prioridade != null && !prioridade.isEmpty()) {
            return taskRepository.findByDonoIdAndTituloOrDescricaoAndStatusAndPrioridade(donoId, removido_ne, search, status, prioridade, sort);
        }

        // Se apenas search e status forem fornecidos
        if (search != null && !search.isEmpty() && status != null && !status.isEmpty()) {
            return taskRepository.findByDonoIdAndTituloOrDescricaoAndStatus(donoId, removido_ne, search, status, sort);
        }
        // Se apenas search e prioridade forem fornecidos
        if (search != null && !search.isEmpty() && prioridade != null && !prioridade.isEmpty()) {
            return taskRepository.findByDonoIdAndTituloOrDescricaoAndPrioridade(donoId, removido_ne, search, prioridade, sort);
        }
        // Se apenas status e prioridade forem fornecidos
        if (status != null && !status.isEmpty() && prioridade != null && !prioridade.isEmpty()) {
            return taskRepository.findByDonoIdAndStatusAndPrioridade(donoId, removido_ne, status, prioridade, sort);
        }
        // Se apenas search for fornecido
        if (search != null && !search.isEmpty()) {
            return taskRepository.findByDonoIdAndTituloOrDescricao(donoId, removido_ne, search, sort);
        }
        // Se apenas status for fornecido
        if (status != null && !status.isEmpty()) {
            return taskRepository.findByDonoIdAndStatus(donoId, removido_ne, status, sort);
        }
        // Se apenas prioridade for fornecida
        if (prioridade != null && !prioridade.isEmpty()) {
            return taskRepository.findByDonoIdAndPrioridade(donoId, removido_ne, prioridade, sort);
        }

        // Caso nenhum filtro adicional seja fornecido, retorna todas as tarefas do dono
        return taskRepository.findByDonoId(donoId, removido_ne, sort);
    }

    public Task inserir(Task task) {
        return taskRepository.save(task);
    }

    public Task atualizar(Task task) {
        return taskRepository.save(task);
    }

    public void remover(Long taskId) {
        this.taskRepository.deleteById(taskId);
    }

    public Task buscarPorId(Long taskId) {
        return this.taskRepository.findById(taskId).orElse(null);
    }
}
