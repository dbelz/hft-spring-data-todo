package me.belz.hellospringdata;

import java.util.ArrayList;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloSpringDataApplication {

	private Logger logger = LoggerFactory.getLogger(HelloSpringDataApplication.class);

	@Autowired
	private TodoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringDataApplication.class, args);
	}

	// ------------------------------------------------------------------------
	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public String listAllTodos(@RequestParam(required=false) Integer priority, @RequestParam(required=false) String tag) {

		logger.info("priority: " + priority);
		logger.info("tag: " + tag);

		if (priority != null && tag != null) {
			logger.error("ERROR: Both params tag and prio are given. Exiting...");
			return "Sorry, in this demo either the priority filter or the tag filter is allowed!";
		}

		StringJoiner todoJoiner = new StringJoiner("\n");
		if (priority != null) {
			todoJoiner.add("Things you still have to do with priority [" + priority + "]:");
			for (Todo todo : repository.findByPriorityAndDoneFalse(priority)) {
				todoJoiner.add(todo.toString());
			}
			return todoJoiner.toString();
		}

		if (tag != null) {
			todoJoiner.add("Things you still have to do tagged [" + tag + "]:");
			for (Todo todo : repository.findByTagAndDoneFalse(tag)) {
				todoJoiner.add(todo.toString());
			}
			return todoJoiner.toString();
		}

		todoJoiner.add("Things you still have to do:");
		for (Todo todo : repository.findByDoneFalse()) {
			todoJoiner.add(todo.toString());
		}
		return todoJoiner.toString();

	}

	// ------------------------------------------------------------------------
	@RequestMapping(value="/todo/add", method=RequestMethod.PUT)
	public String addTodo(@RequestParam String task,
			@RequestParam(required = false) String tag,
			@RequestParam(required = false) Integer priority) {

		Todo todo;
		if (tag != null && priority != null) {
			todo = new Todo(task, tag, priority);
		} else if (tag != null) {
			todo = new Todo(task, tag);
		} else if (priority != null) {
			todo = new Todo(task, priority);
		} else {
			todo = new Todo(task);
		}

		// TODO Add logging here
		repository.save(todo);
		return "New todo added: " + todo.toString();
	}



	// ------------------------------------------------------------------------
	@RequestMapping(value="/todo/remove/{id}", method=RequestMethod.DELETE)
	public void removeTodo(@PathVariable String todo) {



		// TODO Add implementation
	}

}
