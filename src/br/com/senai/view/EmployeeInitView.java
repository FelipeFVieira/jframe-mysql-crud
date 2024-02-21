package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EmployeeInitView extends JFrame{

	private JLabel lblStart = new JLabel("Welcome to Java CRUD!");
	private JButton btnStart = new JButton("START");
	
	public EmployeeInitView() {
		boot();
		actions();
	}
	
	public void boot() {
		setLayout(null);
		lblStart.setBounds(295,250,300,20);
		btnStart.setBounds(270,280,200,20);
		
		getContentPane().add(lblStart);
		getContentPane().add(btnStart);
		
		setSize(800,600);
		setVisible(true);
		
		
	}
	
	public void actions() {
		btnStart.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
					EmployeeMainView EmployeeMainView = new EmployeeMainView();
					EmployeeMainView.show();
					
					hide();
					
				}
			});
	}
}
