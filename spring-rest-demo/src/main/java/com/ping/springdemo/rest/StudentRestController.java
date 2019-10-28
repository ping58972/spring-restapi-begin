package com.ping.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ping.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	private List<Student> theStudents;
	
	//define @PostConstruct to load the student data ... only once!
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<Student>();
		theStudents.add(new Student("Poornima", "Patel"));
		theStudents.add(new Student("Ping", "Pong"));
		theStudents.add(new Student("Mary", "Danddank"));
	}
	
	//define endpoint for "/students" - return list of students
	@GetMapping("/students")
	public List<Student> getStudents(){
		
		return theStudents;
	}
	
	//define endpoint for "/students/{studentId}" -return student at index
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		//just index into the list... keep it simple for now
		
		//check the studentId again list size
		if(studentId>= theStudents.size() || (studentId<0)) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		
		
		return theStudents.get(studentId);
	}
	/*// Move to @ControllerAdvice
	 * // Add an exception handler using @ExceptionHandler
	 * 
	 * @ExceptionHandler public ResponseEntity<StudentErrorResponse>
	 * handleException(StudentNotFoundException exc){
	 * 
	 * //create a StudentErrorRespone StudentErrorResponse errorResponse = new
	 * StudentErrorResponse();
	 * errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
	 * errorResponse.setMessage(exc.getMessage());
	 * errorResponse.setTimeStamp(System.currentTimeMillis()); // return
	 * ResponseEntity
	 * 
	 * return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND) ; }
	 * 
	 * //Add another exception handler ... to catch any exception (catch all)
	 * 
	 * @ExceptionHandler public ResponseEntity<StudentErrorResponse>
	 * handleException(Exception exc){ //create a StudentErrorRespone
	 * StudentErrorResponse errorResponse = new StudentErrorResponse();
	 * errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
	 * errorResponse.setMessage(exc.getMessage());
	 * errorResponse.setTimeStamp(System.currentTimeMillis()); // return
	 * ResponseEntity
	 * 
	 * return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); }
	 */
}
