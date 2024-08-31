package br.ufscar.dc.dsw.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCpf;

import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

 
@Entity
@Table(name = "Client")
public class Client extends User {
    
    @NotBlank
	@UniqueCpf(message = "CPF ja cadastrado")
	@Size(min = 11, max = 15, message = "Número de caracteres invalido")
    @Column(nullable = false, length = 45 )
    private String cpf;
    
    @Column(nullable = false, length = 64)
    private String phone;
    
    @Column(nullable = false, length = 45)
    private String gender;
    
    @Column(nullable = false, length = 15)
    private String birthday;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Rental> rentals;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}