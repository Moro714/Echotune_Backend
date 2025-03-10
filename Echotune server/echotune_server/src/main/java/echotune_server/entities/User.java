package echotune_server.entities;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password_hash")
	private String password_hash;
	
	public User() {}
	public User(String username, String password) {
		this.username = username;
		// Will do hashing later, tpp B)
		this.password_hash = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPasswordHash() {
		return this.password_hash;
	}
}
