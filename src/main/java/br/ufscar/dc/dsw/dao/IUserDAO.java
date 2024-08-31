package br.ufscar.dc.dsw.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.User;

public interface IUserDAO extends CrudRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.email = :email")
	User findByEmail(@Param ("email") String email);
}