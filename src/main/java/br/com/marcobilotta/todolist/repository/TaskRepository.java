package br.com.marcobilotta.todolist.repository;

import br.com.marcobilotta.todolist.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
}
