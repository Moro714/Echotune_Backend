package EchotuneClient.entities;

public class User {
	public int id;
	public String username;
	public String password;
	public User(double id, String username, String password) {
		this.id = (int) id; 
		this.username = username;
		this.password = password;
	}
}
