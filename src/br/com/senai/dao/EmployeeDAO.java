package br.com.senai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.senai.model.Employee;
import br.com.senai.view.EmployeeMainView;

public class EmployeeDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/SENAI";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
	private static final EmployeeMainView employeeMainView = new EmployeeMainView();

	
	public static Connection getConnection() {
	    Connection conn = null;
	    try {
	        conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(employeeMainView,"Error connecting to database: " + ex.getMessage());
	    }
	    return conn;
	}  
	
	
	public void insertEmployess(String name, String cpf, String role) throws SQLException {
	    String sql = "INSERT INTO " + "employees" + " (name, cpf, role) VALUES (?, ?, ?)";
	    Connection conn = getConnection();
	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setString(1, name);
	        statement.setString(2, cpf);
	        statement.setString(3, role);
	        
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            JOptionPane.showMessageDialog(employeeMainView,"Insertion completed successfully!");
	        }
	    }
	}
	
	
		
	public List<Employee> readEmployees(String table) throws SQLException {
		Connection conn = getConnection();
		List<Employee> employees = new ArrayList<Employee>();
		
	    String sql = "SELECT * FROM " + table;
	
	    try (Statement statement = conn.createStatement();
	         ResultSet resultSet = statement.executeQuery(sql)) {
	      
	        while (resultSet.next()) {
	            Long id = resultSet.getLong("id");
	            String name = resultSet.getString("name");
	            String cpf = resultSet.getString("cpf");
	            String role = resultSet.getString("role");
	            
	            Employee employee = new Employee(id, name, cpf, role);
	            employees.add(employee);   
	            
	        }
	        return employees;
	        
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(employeeMainView,"Error reading employees: " + ex.getMessage());
	    }
	    
	    return employees;
	    
	}
}
