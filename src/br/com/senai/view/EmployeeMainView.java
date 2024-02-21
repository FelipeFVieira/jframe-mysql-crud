package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import br.com.senai.dao.EmployeeDAO;
import br.com.senai.model.Employee;


public class EmployeeMainView extends JFrame{
	
	private JLabel lblList = new JLabel("Employee List");
	private JTextArea txtList = new JTextArea();
	private JButton btnUpdate = new JButton("Update Employees");
    private JButton btnNew = new JButton("New Employee");
    private List<Employee> employees = new ArrayList<Employee>();
    
	public EmployeeMainView(){
		boot();
		actions();
		EmployeeDAO dao = new EmployeeDAO();
		
		try {
			employees = dao.readEmployees("Employees");
			fillInEmployees();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		
	}
	
	public void boot() {
		setLayout(null);
		txtList.setEditable(false);
       
        txtList.setBounds(50, 70, 500, 360);
        
		lblList.setBounds(50, 50, 300, 20);
		
		btnUpdate.setBounds(50, 450, 200, 20);
		
		btnNew.setBounds(260, 450, 200, 20); 
		
		getContentPane().add(lblList);
		getContentPane().add(txtList);
		getContentPane().add(btnUpdate);
		getContentPane().add(btnNew);
		
		setSize(800,600);
		setVisible(true);
	
	}
	
	public void actions() {
		btnNew.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					EmployeeNewView employeeNewView = new EmployeeNewView();
					employeeNewView.show();
					
					Employee tmp = employeeNewView.getEmployee();
					
					
					if (tmp.getName() != null && tmp.getCpf() != null && tmp.getRole() != null) {
						employees.add(tmp);
						EmployeeDAO dao = new EmployeeDAO();
						try {
							dao.insertEmployess(tmp.getName(), tmp.getCpf(), tmp.getRole());
							fillInEmployees();
							JOptionPane.showMessageDialog(null,"Employee added");
						}catch(Exception e2){
							JOptionPane.showMessageDialog(null, e);
						}
					}
					
				}
			});
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String updateID = JOptionPane.showInputDialog(null, "Enter the ID of the employee you want to update");
				
				EmployeeUpdateView employeeUpdateView = new EmployeeUpdateView();
				employeeUpdateView.show();
				
			}
		});
		
		
	}
	
	private void fillInEmployees() {
		txtList.setText("");
		EmployeeDAO dao = new EmployeeDAO();
		try {
			employees = dao.readEmployees("employees");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		for(Employee employee : employees) {
			txtList.append("ID: " + employee.getId() + " | Name: " + employee.getName() 
			+ " | CPF: " + employee.getCpf() + " | Role: " + employee.getRole() + "\n" );
		}
	}
}
