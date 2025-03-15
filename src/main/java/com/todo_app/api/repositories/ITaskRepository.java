package com.todo_app.api.repositories;

import com.todo_app.api.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITaskRepository extends JpaRepository<Task, Long> {

    @Query("FROM Task t WHERE t.donoId = :donoId and t.titulo ilike :search or t.descricao ilike :search")
    public List<Task> findByDonoIdAndTituloOrDescricao(String donoId, String search);

    @Query("FROM Task t WHERE t.donoId = :id")
    public List<Task> findAllByDonoId(String id);
}
