package br.com.senai.model;

public class Employee {

	private Long id;
	private String name;
	private String cpf;
	private String role;
	
	public Employee() {
		
	}
	
	public Employee(Long id, String name, String cpf, String role) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

	
