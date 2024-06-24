package EchotuneClient;

public class LocalStorage {
	public static LocalStorage instance;
	private LocalStorage() {}
	
	int userID;
	String username = "";
	
	public static LocalStorage getInstance() {
		if(LocalStorage.instance == null) {
			LocalStorage.instance = new LocalStorage();
		}
		return LocalStorage.instance;
	}
	
	public void setUserID(int id){
		this.userID = id;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
}
