package com.todo_app.api.repositories;

import com.todo_app.api.models.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITaskRepository extends JpaRepository<Task, Long> {

    @Query(
            """
        FROM
                Task t
        WHERE 
                t.donoId = :donoId
                and t.removido != :removido_ne
                and (
                        t.titulo ilike :search
                        or t.descricao ilike :search
                )
        """)
    public List<Task> findByDonoIdAndTituloOrDescricao(String donoId, boolean removido_ne, String search, Sort sort);

    @Query(
            """
        FROM
                Task t
        WHERE 
                t.donoId = :donoId
                and t.removido != :removido_ne
                and t.status in :status
                and (
                        t.titulo ilike :search
                        or t.descricao ilike :search
                )
        """)
    public List<Task> findByDonoIdAndTituloOrDescricaoAndStatus(String donoId, boolean removido_ne, String search, List<String> status, Sort sort);

    @Query(
            """
        FROM
                Task t
        WHERE 
                t.donoId = :donoId
                and t.removido != :removido_ne
                and t.status in :status
        """)
    public List<Task> findByDonoIdAndStatus(String donoId, boolean removido_ne, List<String> status, Sort sort);

    @Query(
            """
        FROM
                Task t
        WHERE 
                t.donoId = :donoId
                and t.removido != :removido_ne
                and t.prioridade in :prioridade
                and (
                        t.titulo ilike :search
                        or t.descricao ilike :search
                )
        """)
    public List<Task> findByDonoIdAndTituloOrDescricaoAndPrioridade(String donoId, boolean removido_ne, String search, List<String> prioridade, Sort sort);

    @Query(
            """
        FROM
                Task t
        WHERE 
                t.donoId = :donoId
                and t.removido != :removido_ne
                and t.status in :status
                and t.prioridade in :prioridade
        """)
    public List<Task> findByDonoIdAndStatusAndPrioridade(String donoId, boolean removido_ne, List<String> status, List<String> prioridade, Sort sort);

    @Query(
            """
        FROM
                Task t
        WHERE 
                t.donoId = :donoId
                and t.removido != :removido_ne
                and t.prioridade in :prioridade
        """)
    public List<Task> findByDonoIdAndPrioridade(String donoId, boolean removido_ne, List<String> prioridade, Sort sort);

    @Query(
            """
        FROM
                Task t
        WHERE 
                t.donoId = :donoId
                and t.removido != :removido_ne
                and t.prioridade in :prioridade
                and t.status in :status
                and (
                        t.titulo ilike :search
                        or t.descricao ilike :search
                )
        """)
    public List<Task> findByDonoIdAndTituloOrDescricaoAndStatusAndPrioridade(String donoId, boolean removido_ne, String search, List<String> status, List<String> prioridade, Sort sort);

    @Query("FROM Task t WHERE t.donoId = :donoId and t.removido != :removido_ne")
    public List<Task> findByDonoId(String donoId, boolean removido_ne, Sort sort);

    @Query("FROM Task t WHERE t.donoId = :id")
    public List<Task> findAllByDonoId(String id);
}
