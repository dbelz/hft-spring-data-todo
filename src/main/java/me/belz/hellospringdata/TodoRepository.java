package me.belz.hellospringdata;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    
    List<Todo> findAll();
    Todo findById(long id);
    List<Todo> findByDoneFalse();
    List<Todo> findByTagAndDoneFalse(String tag);
    List<Todo> findByPriorityAndDoneFalse(int priority);

}
