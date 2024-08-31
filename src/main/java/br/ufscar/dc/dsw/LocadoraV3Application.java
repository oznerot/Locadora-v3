package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.IClientDAO;
import br.ufscar.dc.dsw.dao.ICompanyDAO;
import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.dao.IRentalDAO;
import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Company;
import br.ufscar.dc.dsw.domain.Rental;

@SpringBootApplication
public class LocadoraV3Application {

	public static void main(String[] args) {
		SpringApplication.run(LocadoraV3Application.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IUserDAO userDao, IClientDAO clientDao, ICompanyDAO companyDao, IRentalDAO rentalDao, BCryptPasswordEncoder encoder)
	{
		return (args) -> {
			User u1 = new User();
			u1.setEmail("admin@hotmail.com");
			u1.setName("Admin");
			u1.setPassword(encoder.encode("admin"));
			u1.setRole("ROLE_ADMIN");
			userDao.save(u1);	

			Client c1 = new Client();
			c1.setEmail("maria@hotmail.com");
			c1.setName("Maria");
			c1.setPassword(encoder.encode("cliente1"));
			c1.setRole("ROLE_CLIENT");
			c1.setCpf("468.325.873-40");
			c1.setPhone("(16)99423-5549");
			c1.setGender("F");
			c1.setBirthday("01/01/2001");
			clientDao.save(c1);

			Client c2 = new Client();
			c2.setEmail("joao@gmail.com");
			c2.setName("João");
			c2.setPassword(encoder.encode("cliente2"));
			c2.setRole("ROLE_CLIENT");
			c2.setCpf("582.301.476-80");
			c2.setPhone("(11)95555-8888");
			c2.setGender("M");
			c2.setBirthday("15/08/1995");
			clientDao.save(c2);

			Client c3 = new Client();
			c3.setEmail("carlos@yahoo.com");
			c3.setName("Carlos");
			c3.setPassword(encoder.encode("cliente3"));
			c3.setRole("ROLE_CLIENT");
			c3.setCpf("987.654.321-00");
			c3.setPhone("(21)3333-9999");
			c3.setGender("M");
			c3.setBirthday("30/04/1988");
			clientDao.save(c3);

			Client c4 = new Client();
			c4.setEmail("ana@gmail.com");
			c4.setName("Ana");
			c4.setPassword(encoder.encode("cliente4"));
			c4.setRole("ROLE_CLIENT");
			c4.setCpf("123.456.789-10");
			c4.setPhone("(31)7777-2222");
			c4.setGender("F");
			c4.setBirthday("12/11/1990");
			clientDao.save(c4);

			Client c5 = new Client();
			c5.setEmail("pedro@hotmail.com");
			c5.setName("Pedro");
			c5.setPassword(encoder.encode("cliente5"));
			c5.setRole("ROLE_CLIENT");
			c5.setCpf("654.321.987-12");
			c5.setPhone("(47)8888-5555");
			c5.setGender("M");
			c5.setBirthday("25/06/1985");
			clientDao.save(c5);
			
			Company l1 = new Company();
			l1.setEmail("locadora1@hotmail.com");
			l1.setName("Locadora 1");
			l1.setPassword(encoder.encode("locadora1"));
			l1.setRole("ROLE_COMPANY");
			l1.setCnpj("12.345.678/0001-90");
			l1.setCity("São Carlos");
			companyDao.save(l1);

			Company l2 = new Company();
			l2.setEmail("locadora2@gmail.com");
			l2.setName("Locadora 2");
			l2.setPassword(encoder.encode("locadora2"));
			l2.setRole("ROLE_COMPANY");
			l2.setCnpj("98.765.432/0001-21");
			l2.setCity("Ribeirão Preto");
			companyDao.save(l2);

			Company l3 = new Company();
			l3.setEmail("locadora3@outlook.com");
			l3.setName("Locadora 3");
			l3.setPassword(encoder.encode("locadora3"));
			l3.setRole("ROLE_COMPANY");
			l3.setCnpj("75.432.109/0001-67");
			l3.setCity("Campinas");
			companyDao.save(l3);

			Company l4 = new Company();
			l4.setEmail("locadora4@yahoo.com");
			l4.setName("Locadora 4");
			l4.setPassword(encoder.encode("locadora4"));
			l4.setRole("ROLE_COMPANY");
			l4.setCnpj("23.876.543/0001-43");
			l4.setCity("São Paulo");
			companyDao.save(l4);

			Rental lo1 = new Rental();
			lo1.setDate("30/07/2023 15:00");
			lo1.setValue(BigDecimal.valueOf(54.9));
			lo1.setClient(c1);
			lo1.setCompany(l1);
			rentalDao.save(lo1);	
			
			Rental lo2 = new Rental();
			lo2.setDate("22/01/2022 12:00");
			lo2.setValue(BigDecimal.valueOf(66.6));
			lo2.setClient(c1);
			lo2.setCompany(l2);
			rentalDao.save(lo2);

			Rental lo3 = new Rental();
			lo3.setDate("12/11/2021 17:00" );
			lo3.setValue(BigDecimal.valueOf(15.8));
			lo3.setClient(c1);
			lo3.setCompany(l3);
			rentalDao.save(lo3);
		};
	}
}
