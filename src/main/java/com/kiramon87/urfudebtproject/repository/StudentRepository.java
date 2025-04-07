package com.kiramon87.urfudebtproject.repository;

import com.kiramon87.urfudebtproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
