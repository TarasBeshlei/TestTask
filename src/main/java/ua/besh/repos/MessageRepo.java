package ua.besh.repos;

import org.springframework.data.repository.CrudRepository;
import ua.besh.domain.Message;

public interface MessageRepo extends CrudRepository<Message, Integer> {

}