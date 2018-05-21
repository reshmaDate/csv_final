package com.test.csv.service;

import com.test.csv.pojo.Employee;
import com.test.csv.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeService {

    @Resource
    private EmployeeRepository employeeRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public Employee saveEmployee(Employee employee){
        Employee savedEmployee = new Employee();
        try {
            if(null != employee){
                savedEmployee = employeeRepository.save(employee);
            }
            return savedEmployee;
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error while persisting employee",e);
            throw e;
        }
    }

    public Employee getEmployeeById(Long employeeId ){
        Employee employee = new Employee();
        try{
            if(null != employeeId){
                employee = employeeRepository.getOne(employeeId);
            }
            return employee;

        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error while getting employee",e);
            throw e;
        }
    }

}
