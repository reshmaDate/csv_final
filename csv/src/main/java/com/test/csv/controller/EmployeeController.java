package com.test.csv.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.test.csv.pojo.Employee;
import com.test.csv.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.io.*;


@Controller
public class EmployeeController {

    private static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Resource
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<String> importEmployees(@RequestParam("file") MultipartFile file)
            throws InvalidFormatException, IOException {
        String message = "successfully imported Employee CSV";
        try {

            importCSV(file);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Error while uploading users,please upload valid data", e);
        }
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    public void importCSV(MultipartFile csvFile) {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), "UTF-8"));
            LOGGER.info("new br" + br.lines());
            String currentLine = null;
            int RowNum = -1;
            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split("\\|");
                LOGGER.info(str.toString());
                RowNum++;
                for (int i = 0; i < str.length; i++) {

                    LOGGER.info("cell value" + str[i]);
                    String[] val = str[i].split(",");
                    Employee emp = new Employee();
                    emp.setFirstName(val[0]);
                    emp.setLastName(val[1]);
                    emp.setEmailId(val[2]);
                    emp.setCompanyName(val[3]);
                    emp.setDepartment(val[4]);
                    emp.setDesignation(val[5]);
                    emp.setAddress(val[6]);
                    employeeService.saveEmployee(emp);

                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "Exception in try");
        }
    }

}

