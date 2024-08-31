package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import br.ufscar.dc.dsw.validation.UniqueEmail;

@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity<Long>
{
	@NotBlank
	@UniqueEmail(message = "Email já cadastrado")
    @Column(nullable = false, length = 45, unique = true)
    private String email;
    
	@NotBlank
    @Column(nullable = false, length = 64)
    private String name;

	@NotBlank
    @Column(nullable = false, length = 64)
    private String password;
    
	@NotBlank
    @Column(nullable = false, length = 45)
    private String role;
	
	public String getEmail() {
		return email;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}