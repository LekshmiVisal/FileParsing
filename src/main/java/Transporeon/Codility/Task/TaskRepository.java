package Transporeon.Codility.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


	@Repository
	interface TaskRepository extends JpaRepository<Task, Long> {
	}

