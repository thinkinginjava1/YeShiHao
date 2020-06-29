package com.yeshihao.sqlserverspringboot1.respostroy;

import com.yeshihao.sqlserverspringboot1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRespostroy extends JpaRepository<Student,Integer> {

}
