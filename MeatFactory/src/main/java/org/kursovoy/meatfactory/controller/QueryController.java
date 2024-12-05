package org.kursovoy.meatfactory.controller;

import org.kursovoy.meatfactory.model.Employee;
import org.kursovoy.meatfactory.service.QueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queries")
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/employeesByPosition")
    public List<Employee> getEmployeesByPosition(@RequestParam String position) {
        return queryService.findEmployeesByPosition(position);
    }

    @GetMapping("/employeesBySalaryRange")
    public List<Employee> getEmployeesBySalaryRange(@RequestParam double minSalary, @RequestParam double maxSalary) {
        return queryService.findEmployeesBySalaryRange(minSalary, maxSalary);
    }

    @GetMapping("/employeesByAge")
    public List<Employee> getEmployeesOlderThan(@RequestParam int age) {
        return queryService.findEmployeesOlderThan(age);
    }
}
