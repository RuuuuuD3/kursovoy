package org.kursovoy.meatfactory.service;

import org.kursovoy.meatfactory.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Service
public class QueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Employee> findEmployeesByPosition(String position) {
        String jpql = "SELECT e FROM Employee e WHERE e.role = :position";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("position", position);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Employee> findEmployeesBySalaryRange(double minSalary, double maxSalary) {
        String jpql = "SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("minSalary", minSalary);
        query.setParameter("maxSalary", maxSalary);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Employee> findEmployeesOlderThan(int age) {
        String jpql = "SELECT e FROM Employee e WHERE e.age > :age";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("age", age);
        return query.getResultList();
    }
}
