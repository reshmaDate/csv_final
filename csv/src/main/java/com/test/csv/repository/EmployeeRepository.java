package com.test.csv.repository;

import com.test.csv.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee ,Long> {
}
