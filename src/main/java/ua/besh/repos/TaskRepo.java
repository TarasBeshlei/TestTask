package ua.besh.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.besh.domain.Task;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByMessageAuthor(String messageAuthor);
    void deleteById(Long id);
}

