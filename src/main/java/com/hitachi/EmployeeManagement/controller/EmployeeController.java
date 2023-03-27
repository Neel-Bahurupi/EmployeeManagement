package com.hitachi.EmployeeManagement.controller;

import com.hitachi.EmployeeManagement.model.Employee;
import com.hitachi.EmployeeManagement.repository.EmployeeRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/get-employees")
    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    @PostMapping("/add-employee")
    public Employee addEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    @PutMapping("/update-employee")
    public Employee updateEmployee(@RequestBody Employee employee){

        Employee emp = repository.findById(employee.getId()).get();

        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setDepartment(employee.getDepartment());
        emp.setEmail(employee.getEmail());

        repository.save(emp);
        return emp;
    }

    @DeleteMapping("/delete-employee")
    public void deleteEmployee(@RequestParam Long id){
      repository.deleteById(id);
    }


}
