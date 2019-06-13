package ca.noogle.nooglecabackend.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {""}, allowGetters = true) //annotation is a Jackson annotation. Spring Boot uses Jackson for Serializing and Deserializing Java objects to and from JSON.
public class User implements Serializable  {

	@Id //is used to define the primary Key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // is used to define the primary key genaration strategy. we used it to be an Auto Increment field.
	private Long userID;
	
	@NotBlank // is used to validate that the annotated field is not null or empty
	private String userEmail;
	
	@NotBlank
	private String userPassword;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}
