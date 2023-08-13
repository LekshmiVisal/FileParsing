/*package Transporeon.Codility.Task;

public class TaskController {

}
*/
package Transporeon.Codility.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@RestController
@RequestMapping("/tasks")
class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        if (!taskRepository.existsById(id)) {
        	System.out.println("Inside taskRepository existsById- not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"MESSAGE\": \"Cannot find task with given ID\", \"status\": 404}");
        }

        if (updatedTask.getDescription() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"MESSAGE\": \"Task description is required\", \"status\": 400}");
        }

        Task existingTask = taskRepository.getById(id);
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());

        taskRepository.save(existingTask);

        return ResponseEntity.ok(existingTask);
    }
}