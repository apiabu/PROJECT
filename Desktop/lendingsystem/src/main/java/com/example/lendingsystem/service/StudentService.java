package com.example.lendingsystem.service;

import com.example.lendingsystem.model.Student;
import com.example.lendingsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> list(){
        return repo.findAll();
    }

    public Student create(Student student){
        return repo.save(student);
    }

    public Student update(Long id, Student updated){
        return repo.findById(id).map(stu -> {
            stu.setFullname(updated.getFullname());
            stu.setStudentId(updated.getStudentId());
            return repo.save(stu);
        }).orElse(null);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
