package com.masai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class week4Day1Question1jdbc {

	public static void main(String[] args) {
		
		System.out.println("Program starting......\n");

		String url = "jdbc:mysql://localhost:3306/db1"; // database selected

		try (Connection conn = DriverManager.getConnection(url, "root", "root");) { // trying with source

			// query to show salary of particular employee

			System.out.println("Enter employee Id");

			Scanner sc = new Scanner(System.in);
			int eid = sc.nextInt(); // taking employee id from user to show there salary

			PreparedStatement ps = conn.prepareStatement("select salary from emp where eid = ?");

			ps.setInt(1, eid); // giving input to query

			ResultSet rs = ps.executeQuery();

			rs.next();

			int salary = rs.getInt("salary"); // only one line is printed by query

			System.out.println("Salary of this Employee is :" + salary); // showing salary as output
			System.out.println("\n");

			// printing details of all the employee

			System.out.println("These are the details of all the employee's");
			System.out.println("=====================================================");

			PreparedStatement ps2 = conn.prepareStatement("select * from emp");

			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) { // n number of output is provided by query
				System.out.println("Employee Id is :" + rs2.getInt("eid"));
				System.out.println("Employee name is :" + rs2.getString("name"));
				System.out.println("Employee address is :" + rs2.getString("address"));
				System.out.println("Employee salary is :" + rs2.getInt("Salary"));

				System.out.println("-------------------------------------------------");
			}

			// code to give bonus to all employee

			System.out.println("It's time to give them some bonus...");

			PreparedStatement ps3 = conn.prepareStatement("update emp set salary = salary +500");

			int lines1 = ps3.executeUpdate(); // query executed
			System.out.println(lines1 + " updated...");

			// inserting employee details without address, input is given by the user

			System.out.println("Enter employee details without address to insert into table \n");
			// taking input from used....
			System.out.println("Enter employee Id");
			int empId = sc.nextInt();

			System.out.println("Enter employee name as per aadhar number");
			String name = sc.next();

			System.out.println("Enter employee salary");
			int empSalary = sc.nextInt();

			PreparedStatement ps4 = conn.prepareStatement("insert into emp(eid,name,salary) values(? ,? ,?)");
			ps4.setInt(1, empId);
			ps4.setString(2, name);
			ps4.setInt(3, empSalary);

			int lines2 = ps4.executeUpdate();

			System.out.println(lines2 + " updated...");
			
			System.out.println("row is inserted sucessfully.... \n");

			// application to search Employees based on Id.
			
			System.out.println("Enter employee id to search employee detail");
			int eid2 = sc.nextInt(); // taking employee id from user to show there detail
			
			PreparedStatement ps5 = conn.prepareStatement("select * from emp where eid = ?");
			ps5.setInt(1, eid2);
			
			ResultSet rs5 = ps5.executeQuery();
			
			// eid is primary key so it will give atmost 1 output;
			System.out.println("=======================================================");
			if(rs5.next())
				{
					System.out.println("Employee Id is :" + rs5.getInt("eid"));
					System.out.println("Employee name is :" + rs5.getString("name"));
					System.out.println("Employee address is :" + rs5.getString("address"));
					System.out.println("Employee salary is :" + rs5.getInt("Salary"));

					System.out.println("-------------------------------------------------");
				}
			else System.out.println("Employee does not exist...");
			
			
			//application to list out all the Employee Details whose salary is less than 80000;
			System.out.println("\n List of all the employee whose salary is less then 80000");
			
			PreparedStatement ps6 = conn.prepareStatement("select * from emp where salary < 80000");
			ResultSet rs6 = ps6.executeQuery();
			
			System.out.println("=======================================================");
			
			while(rs6.next()){
				System.out.println("Employee Id is :" + rs6.getInt("eid"));
				System.out.println("Employee name is :" + rs6.getString("name"));
				System.out.println("Employee address is :" + rs6.getString("address"));
				System.out.println("Employee salary is :" + rs6.getInt("Salary"));

				System.out.println("-------------------------------------------------");
			}
			
			
			System.out.println("Programm ended ..... Have a nice day");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}