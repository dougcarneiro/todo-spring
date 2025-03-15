package repositories;

import model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("FROM Task t WHERE t.titulo ilike :x or t.descricao ilike :x")
    List<Task> findByTituloOrDescricao(String x);


}
