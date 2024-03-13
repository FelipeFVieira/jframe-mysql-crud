package br.com.senai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	    Connection conn = getConnection();
		String sql = "INSERT INTO employees " + "(name, cpf, role) VALUES (?, ?, ?)";
	    try (PreparedStatement statement = conn.prepareStatement(sql)) {
	        statement.setString(1, name);
	        statement.setString(2, cpf);
	        statement.setString(3, role);
	        
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            JOptionPane.showMessageDialog(employeeMainView,"Insertion completed successfully!");
	        }
	    } catch(Exception e){
	    	JOptionPane.showMessageDialog(employeeMainView,"Insertion error!");
	    } finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
		
	public List<Employee> readEmployees(String table){
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
	        
	    } catch (SQLException ex) {
	    	JOptionPane.showMessageDialog(employeeMainView,"Error reading employees: " + ex.getMessage());
	    } finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
	    return employees;
	    
	}
	
	public String searchEmployee(String id) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM employees WHERE id = ?";
		try(PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, id);
			
			ResultSet resultset = statement.executeQuery();
			
			if(resultset.next()) {
				String resultId = resultset.getString("id");
				String resultName = resultset.getString("name");
				String resultCpf = resultset.getString("cpf");
				String resultRole = resultset.getString("role");
				
				return "ID: " + resultId + " | Name: " + resultName +
						   " | CPF: " + resultCpf + " | Role: " + resultRole;
				
			} 
		    
		} catch (SQLException e) {
			 JOptionPane.showMessageDialog(employeeMainView, e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "Employee not found!";
	}
	
	public void updateEmployee(int id, Map<String, Object> columns) {
		Connection conn = getConnection();
		
	    try {
	        StringBuilder sqlUpdate = new StringBuilder("UPDATE minha_tabela SET ");
	        List<String> updateColumns = new ArrayList<>();

	        for (Map.Entry<String, Object> entry : columns.entrySet()) {
	            if (entry.getValue() != null) {
	            	updateColumns.add(entry.getKey() + " = ?");
	            }
	        }

	        sqlUpdate.append(String.join(", ", updateColumns));
	        sqlUpdate.append(" WHERE id = ?");

	        PreparedStatement updateStatement = conn.prepareStatement(sqlUpdate.toString());
	        int parametroIndex = 1;

	        for (Map.Entry<String, Object> entry : columns.entrySet()) {
	            if (entry.getValue() != null) {
	                updateStatement.setObject(parametroIndex++, entry.getValue());
	            }
	        }

	        updateStatement.setInt(parametroIndex, id);

	        updateStatement.executeUpdate();

	    } catch (SQLException e) {
	        System.out.println("Erro ao executar operação: " + e.getMessage());
	    }
	}
}
