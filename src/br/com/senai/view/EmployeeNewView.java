package br.com.senai.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.senai.model.Employee;


public class EmployeeNewView extends JDialog {	
	
	JLabel lblName = new JLabel("Name:");
	JTextField txtName = new JTextField(null, 20);
	
	JLabel lblCpf = new JLabel("CPF:");
	JTextField txtCpf = new JTextField(null, 20);
	
	JLabel lblRole = new JLabel("Role:");
	JTextField txtRole = new JTextField(null, 20);
	
	JButton btnAdd  = new JButton("Add Employee"); 
	JButton btnExit = new JButton("Exit"); 
	
	private Employee employee = new Employee();
	
	public EmployeeNewView() {
		boot();
		actions();
	}
	
	public void boot() {
		setLayout(new FlowLayout()); 
		
		getContentPane().add(lblName);
		getContentPane().add(txtName);
		
		getContentPane().add(lblCpf);
		getContentPane().add(txtCpf);		
		
		getContentPane().add(lblRole);
		getContentPane().add(txtRole);		
	
		getContentPane().add(btnAdd); 
		getContentPane().add(btnExit);
		
		setModal(true);
		setSize(250, 600); 
	}
	
	public void actions() {
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtName.getText() != null && txtCpf.getText() != null && txtRole.getText() != null) {
			
					employee.setName(txtName.getText());
					employee.setCpf(txtCpf.getText());
					employee.setRole(txtRole.getText());
				
				}
			    hide();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				hide();
			}
		});
		
		
		
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
