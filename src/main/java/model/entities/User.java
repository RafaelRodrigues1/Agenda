package model.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Entities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 80)
	private String name;
	
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;
	
	@Column(name = "login", nullable = false, length = 50, unique = true)
	private String login;
	
	@Column(name = "password", nullable = false, length = 16)
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Contact> contactList;
	
	public User() {}

	public User(String name, LocalDate birthDate, String login, String password) {
		this.name = name;
		this.birthDate = birthDate;
		this.login = login;
		this.password = password;
	}

	public User(Integer id, String name, LocalDate birthDate, String login, String password) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.login = login;
		this.password = password;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Contact> getContactsList() {
		return contactList;
	}

	public void setContactsList(List<Contact> contactList) {
		this.contactList = contactList;
	}	
}
