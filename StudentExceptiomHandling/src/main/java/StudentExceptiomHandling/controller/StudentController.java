package StudentExceptiomHandling.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import StudentExceptiomHandling.exception.ResourceNotFoundException;
import StudentExceptiomHandling.model.Student;
import StudentExceptiomHandling.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
    @GetMapping("/studentEH")
    public List<Student> getAllEmployees() {
        return studentRepository.findAll();
    }
    
//    @GetMapping("/studentAllEH/{id}")
//    public Map<String, Boolean> getStudent(@PathVariable(value = "id") Integer studentId)
//			throws ResourceNotFoundException {
//    
//    		
//    	}
//		studentRepository.getById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
//
//		//studentRepository.getById(studentId);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("student found", Boolean.TRUE);
//		return response;
//	}
    
    
    
    @GetMapping("/studentIdEH/{studentId}")
    public Student retrieveStudent(@PathVariable Integer studentId) throws ResourceNotFoundException {
    	Optional<Student> student = studentRepository.findById(studentId);

    	if (!student.isPresent())
    		throw new ResourceNotFoundException("Student not found with id ::" + studentId);

    	return student.get();
    }
    
    
    @PostMapping("/studentEH")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }
    
    @PutMapping("/studentEH/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer studentId,
          @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student= studentRepository.findById(studentId)
        .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

 
        student.setStudentName(studentDetails.getStudentName());
    
        final Student updateStudent= studentRepository.save(student);
        return ResponseEntity.ok(updateStudent);
    }
    
    @DeleteMapping("/studentEH/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Integer studentId)
			throws ResourceNotFoundException {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

		studentRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
