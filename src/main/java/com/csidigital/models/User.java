package com.csidigital.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@NotBlank
	@Size(max = 20)
	private String username;
	private String image;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	@NotBlank
	@Size(max = 120)
	private String password;
	private Boolean confirme =false;
	private String passwordResetToken;
	private String confirmationToken;
	private String fullname;
	private long phone;
	private String adresse;
	private String website;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"),
			foreignKey = @ForeignKey(name = "none"),
			inverseForeignKey = @ForeignKey(name = "none"))
	private Set<Role> roles = new HashSet<>();
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Customer> customers = new ArrayList<>();


	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@JsonManagedReference
	@OneToMany(mappedBy = "userp", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Partner>  Partner= new ArrayList<>();

}
