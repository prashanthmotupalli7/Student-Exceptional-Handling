package StudentExceptiomHandling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import StudentExceptiomHandling.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
