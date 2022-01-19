package com.bd.service;

import com.bd.dao.DAOempImpl;
import com.bd.entities.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ServEM {


    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int getNFromLine(int max) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                String str = br.readLine();
//                System.out.println(str);
                if (str.matches("[1-max]")){
                    return Integer.parseInt(str);
                }
                System.out.print("Wrong choice. Enter again: ");
            } catch (IOException e) {
                System.out.print("Wrong choice. Enter again: ");
            }
        }
    }

    public static void deleteEmployee() {
        while (true) {
            try {
                System.out.print("Enter employee ID: ");
                String str = br.readLine();
                int id = Integer.parseInt(str);
                DAOempImpl.getInstance().removeEmp(id);
                return;
            } catch (IOException e) {
                System.out.print("Wrong choice. Enter again: ");
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
                System.out.print("Wrong choice. Enter again: ");
            }
        }
    }

    public static void showAll() {
        List<Employee> list = DAOempImpl.getInstance().getAllEmp();
        printHatEmployee();
        for (Employee emp : list) {
            printEmployee(emp);
        }
        System.out.println();
        list = null;
    }

    public static void showEmployee() {
        int maxID = DAOempImpl.getInstance().getMaxID();


        Employee emp = null;
        while (true){
            System.out.print("Enter correct employee ID.");
            int ID = getIDFromLine(maxID);

            try {
                emp = DAOempImpl.getInstance().showEmployee(ID);
            } catch (SQLException throwables) {
                throwables.getMessage();
                continue;
            }
            if (emp==null)continue;
            break;
        }
        print1Employee(emp);
    }

    private static int getIDFromLine(int maxID) {
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    String str = br.readLine();
                    System.out.println(str);
                    int testID = Integer.parseInt(str);
                    if (testID < 1 || testID > maxID){
                        System.out.print("Out of range.");
                        continue;
                    }
                    return testID;
                    } catch (IOException e) {
                    e.getMessage();
                    System.out.print("Wrong choice.");
                }


            }
        }


    private static void print1Employee(Employee emp) {
        System.out.println();
        printHatEmployee();
        printEmployee(emp);
        System.out.println();
    }

    private static void printHatEmployee() {
        System.out.printf("%6s%10s%10s%6s%15s%8s%8s%8s%8s%12s%10s%n%n", "ID", "NAME", "JOB", "MGR", "hireDate", "SALARY", "COMM", "GRADE", "deptID", "deptNAME", "Location");
    }
    private static void printEmployee(Employee emp){
        System.out.printf("%6s%10s%10s%6s%15s%8s%8s%8s%8s%12s%10s%n",
                emp.getID(),
                emp.getName(),
                emp.getJob(),
                emp.getMgrID(),
                emp.getHireDate(),
                emp.getSalary(),
                emp.getComm(),
                emp.getGrade(),
                emp.getDeptID(),
                emp.getDepartment().getDeptName(),
                emp.getDepartment().getLocation());

    }

    public static void createEmployeeFromLine(){
        System.out.println("Creating EMPLOYEE from line...");
//        Employee employee = new Employee();
        String name = putString("NAME");
        String job = putString("JOB");
        int mgr =  putMgrId();
        Date hDate = putDate();
        double salary = putFloat("SALARY");
        double comm = putFloat("COMMISION");
        int depID =  putDepID();
        DAOempImpl.getInstance().createEmp(name, job, mgr, hDate, salary, comm, depID);

    }



    //Creating methods

    private static int putDepID() {
        System.out.println("departments ID:");
        List<Integer> list = DAOempImpl.getInstance().getAllDept();
        System.out.println(list);
        while (true) {
            try {
                System.out.print("Enter Department ID to employee: ");
                String str = br.readLine();
                int dep = Integer.parseInt(str);
                if (list.contains(dep)) return dep;

            } catch (IOException e) {
                e.getMessage();
                System.out.print("Wrong. Enter again: ");
            }
        }
    }

    private static float putFloat(String type) {
        while (true) {
            try {
                System.out.print("Enter "+type+": ");
                String str = br.readLine();
                if (str.isEmpty())continue;
                float d =Float.parseFloat(str);
                if (d>=0) return d;
                System.out.print("Wrong. Enter again: ");
            } catch (IOException e) {
                System.out.print("Wrong. Enter again: ");
            }
        }
    }

    private static Date putDate() {
        System.out.print("Enter hireDate in format \"yyyy-MM-dd\": ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
   while (true) {
                try {
                    LocalDate date = LocalDate.parse(br.readLine(), formatter);
                    return java.util.Date.from(date.atStartOfDay()
                            .atZone(ZoneId.systemDefault())
                            .toInstant());
                } catch (Exception e) {
                    System.out.print("Wrong date time. Enter again: ");
                }
            }

        }

    private static int putMgrId() {
        System.out.println("managers ID:");
        List<Integer> list = DAOempImpl.getInstance().getAllEmpno();
        System.out.println(list);
        while (true) {
            try {
                System.out.print("Enter Manager ID to employee: ");
                String str = br.readLine();
                int mgr = Integer.parseInt(str);
               if (list.contains(mgr) || mgr==0) return mgr;

                } catch (IOException e) {
                e.getMessage();
                System.out.print("Wrong. Enter again: ");
            }
        }
    }

    private static String putString(String type) {
        while (true) {
            try {
                System.out.print("Enter "+type+": ");
                String str = br.readLine();
                if (!str.isEmpty()){
                    return str;
                }
                System.out.print("Enter again: ");
            } catch (IOException e) {
                System.out.print("Enter again: ");
            }
        }
    }


}
