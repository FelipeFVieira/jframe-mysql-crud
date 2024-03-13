package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.senai.dao.EmployeeDAO;

public class EmployeeUpdateView extends JDialog {
	
	private JLabel lblId = new JLabel("Id:");
	private JTextField txtId = new JTextField();
	private JButton btnSearch = new JButton("Search");
	
	private JLabel lblEmp = new JLabel();
	
	private JLabel lblName = new JLabel();
	private JTextField txtName = new JTextField();
	
	private JLabel lnlCpf = new JLabel();
	private JTextField txtCpf = new JTextField();
			
	private JLabel lnlRole = new JLabel();
	private JTextField txtRole = new JTextField();
	
	private JButton btnUpdate = new JButton();
	private JButton btnExit = new JButton();
	
	EmployeeDAO dao = new EmployeeDAO();
	
	public EmployeeUpdateView() {
		boot();
		actions();
	}
	
	public void boot() {
		setLayout(null);
		lblId.setBounds(210, 30, 30, 20);
		txtId.setBounds(230, 30, 130, 20);
		btnSearch.setBounds(360, 30, 150, 20);
		lblEmp.setBounds(160, 80, 600, 20);
		
		getContentPane().add(lblId);
		getContentPane().add(txtId);
		getContentPane().add(btnSearch);
		getContentPane().add(lblEmp);
		
		
		setModal(true);
		setSize(800,400);
	}
	
	public void actions() {
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				if (txtId.getText() != null) {
					try {
					String employee = dao.searchEmployee(txtId.getText());
					lblEmp.setText(employee);
					if (employee != "employee not found!") {
					int searchId = Integer.parseInt(txtId.getText());
					} 
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(searchId > 0) {
				if (txtName.getText() != null || txtCpf.getText() != null || txtRole.getText() != null) {
					int id = Integer.parseInt(txtId.getText());
					Map<String, Object> columns = new HashMap<>();
					columns.put("name", txtName.getText());
					columns.put("cpf", txtCpf.getText());
					columns.put("cpf", txtRole.getText());
					dao.updateEmployee(id, columns);
				}
				}
			}
		});
	}


}
