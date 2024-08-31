package br.ufscar.dc.dsw.domain;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import br.ufscar.dc.dsw.validation.UniqueCnpj;
import jakarta.persistence.CascadeType;


@SuppressWarnings("serial")
@Entity
@Table(name = "Company")
public class Company extends User {

	@NotBlank
    @UniqueCnpj (message = "CNPJ ja cadastrado")
	@Size(min = 13, max = 18, message = "Número de caracteres invalido")
	@Column(nullable = false, unique = true, length = 60)
	private String cnpj;

    @Column(nullable = true, length = 50)
    private String city;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Rental> rentals;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

	public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}