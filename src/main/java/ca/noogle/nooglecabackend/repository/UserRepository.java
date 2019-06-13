package ca.noogle.nooglecabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.noogle.nooglecabackend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
