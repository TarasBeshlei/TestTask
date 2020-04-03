package ua.besh.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.besh.domain.SharedTask;

import java.util.List;

public interface SharedTaskRepo extends JpaRepository<SharedTask, Long> {
    List<SharedTask> findByCooperatorEmail(String email);

}
