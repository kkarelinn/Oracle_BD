package com.bd.dao;

import com.bd.entities.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface DAOemp {
    Connection connect();
    //CRUD
    void createEmp(String name,
                   String job,
                   int mgrID,
                   Date hireDate,
                   double salary,
                   double comm,
                   int deptID);
    void updateEmp(int ID,
                   String name,
                   String job,
                   int mgrID,
                   Date hireDate,
                   double salary,
                   double comm,
                   int deptID);
    void removeEmp(int id) throws SQLException;

    Employee showEmployee(int emp_ID) throws SQLException;
    List<Employee> getAllEmp();
}
