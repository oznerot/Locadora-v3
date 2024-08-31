package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import br.ufscar.dc.dsw.validation.UniqueRental;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@UniqueRental
@Table(name = "Rental")
public class Rental extends AbstractEntity<Long> {

	@NotNull
	@Column(nullable = false, length = 19)
	private String date;
    
	@NotNull
	@Column(columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal value;

	@NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

    public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}   
}