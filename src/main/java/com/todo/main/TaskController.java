package com.todo.main;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.todo.main.task.Task;
import com.todo.main.task.TaskRepository;

import javax.swing.text.html.Option;

@CrossOrigin("*")
@RestController
public class TaskController {
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/t")
	public List <Task> getTask() {
		return taskRepository.findAll();	
	}

	@PostMapping("/t")
	public Task addTask(@RequestBody Task t) {
		return taskRepository.save(t);
	}

	@DeleteMapping("/t/{id}")
	public ResponseEntity deletTask(@PathVariable Long id) {
		Optional<Task> t = taskRepository.findById(id);
		Task task = t.orElseThrow(()-> new RuntimeException("No Record found"));
		taskRepository.delete(task);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
