package com.bd.dao;


import com.bd.entities.Department;
import com.bd.entities.Employee;
import com.bd.service.ParseXMLConf;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOempImpl implements DAOemp {
    private static DAOempImpl instance;
//    private Connection connection;
//    private PreparedStatement preparedStatement;
    private ResultSet resultset;
    private final ParseXMLConf config = new ParseXMLConf();

    private static final String GET_ALL_INFO_BY_EMP = "select tot.*, GRADE\n" +
            "from (select EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, d.DEPTNO, DNAME, LOC from EMP e, DEPT d where e.deptno = d.deptno) tot\n" +
            "left join SALGRADE s on SAL between MINSAL and HISAL\n" +
            "order by EMPNO";
    private static final String INSERT_EMP = "insert into EMP values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMP = "update EMP set ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=?  where EMPNO=?";
    private static final String GET_ALL_EMPNO = "select EMPNO from EMP";
    private static final String GET_ALL_DEPTNO = "select DEPTNO from DEPT";
    private static final String DELETE_EMP_BY_ID = "delete from EMP where EMPNO = ?";
    private static final String GET_MAX_ID = "select MAX(EMPNO) MX from EMP";
    private static final String GET_EMP_BY_ID = "select EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, e.DEPTNO, DNAME, LOC, GRADE " +
            "from EMP e, DEPT d, SALGRADE s " +
            "where e.deptno = d.deptno and EMPNO = ? and e.sal between s.minsal and s.hisal";


    private DAOempImpl() {
    }

    public static DAOempImpl getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return new DAOempImpl();
        }
    }


    public Connection connect() {
        try {
//            ResourceBundle resource = ResourceBundle.getBundle("database");
//            String driver = resource.getString("db.driver");
//            String url = resource.getString("db.url");
//            String user = resource.getString("db.user");
//            String pass = resource.getString("db.password");
            String driver = config.getDriverClass();
            String url = config.getUrl();
            String source = config.getSource();
            String user = config.getUserName();
            String pass = config.getPassword();
            Class.forName(driver);
            return DriverManager.getConnection(url+source, user, pass);

        } catch (ClassNotFoundException | SQLException throwables) {
            System.out.println("some problem");
            throwables.printStackTrace();
        }
        return null;
    }

      @Override
    public void createEmp(String name,
                          String job,
                          int mgrID,
                          Date hireDate,
                          double salary,
                          double comm,
                          int deptID) {

        int ID = getMaxID() + 1;
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMP);) {

            preparedStatement.setInt(1, ID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, job);
            preparedStatement.setInt(4, mgrID);
            preparedStatement.setDate(5, new java.sql.Date(hireDate.getTime()));
            preparedStatement.setDouble(6, salary);
            preparedStatement.setDouble(7, comm);
            preparedStatement.setInt(8, deptID);
            if (preparedStatement.executeUpdate() > 0)
                System.out.println("employee was created");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateEmp(int ID,
                          String name,
                          String job,
                          int mgrID,
                          Date hireDate,
                          double salary,
                          double comm,
                          int deptID) {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMP);) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, job);
            preparedStatement.setInt(3, mgrID);
            preparedStatement.setDate(4, new java.sql.Date(hireDate.getTime()));
            preparedStatement.setDouble(5, salary);
            preparedStatement.setDouble(6, comm);
            preparedStatement.setInt(7, deptID);
            preparedStatement.setInt(8, ID);
            if (preparedStatement.executeUpdate() > 0)
                System.out.println("employee was updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeEmp(int id) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMP_BY_ID);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0)
                System.out.println("employee was removed");
            else
                System.out.println("You have no employee with such ID");
        }

    }


    @Override
    public Employee showEmployee(int emp_ID) throws SQLException {
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_EMP_BY_ID);) {

            Employee employee = null;
            preparedStatement.setInt(1, emp_ID);
            resultset = preparedStatement.executeQuery();
            resultset.next();
            employee = parseEmp(resultset);
            return employee;
        }
    }

    @Override
    public List<Employee> getAllEmp() {
        List<Employee> empList = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_INFO_BY_EMP);) {
            resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                empList.add(parseEmp(resultset));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empList;
    }


    public List<Integer> getAllEmpno() {

        List<Integer> empno = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EMPNO);) {
            resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                empno.add(resultset.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return empno;
    }


    public int getMaxID() {
        int max = 0;
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MAX_ID);) {
            resultset = preparedStatement.executeQuery();
            resultset.next();
            max = resultset.getInt("MX");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return max;
    }

    private Employee parseEmp(ResultSet resultset) {

        Employee employee = null;
        try {

            int ID = resultset.getInt("EMPNO");
            String name = resultset.getString("ENAME");
            String job = resultset.getString("JOB");
            int mgrID = resultset.getInt("MGR");
            Date hireDate = resultset.getDate("HIREDATE");
            float salary = resultset.getFloat("SAL");
            float comm = resultset.getFloat("COMM");
            int grade = resultset.getInt("GRADE");
            int deptID = resultset.getInt("DEPTNO");
            String deptName = resultset.getString("DNAME");
            String location = resultset.getString("LOC");

            Department department = new Department(deptID, deptName, location);
            employee = new Employee(ID, name, job, mgrID, hireDate, salary, comm, grade, deptID, department);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;

    }

    public List<Integer> getAllDept() {

        List<Integer> deptList = new ArrayList<>();
        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DEPTNO);) {
            resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                deptList.add(resultset.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return deptList;

    }
}
