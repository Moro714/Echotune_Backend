package EchotuneClient.scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class SceneDictionary {
	public static SceneDictionary instance;
	private Map<String, Scene> dict;

	private SceneDictionary() {

	}

	public static Map<String, Scene> getSceneDictionary() {
		if (SceneDictionary.instance == null) {
			SceneDictionary.instance = new SceneDictionary();
		}
		
		if (SceneDictionary.instance.dict == null) {
			SceneDictionary.instance.dict = new HashMap<String, Scene>();
			try {
				SceneDictionary.instance.dict.put("login",
						new Scene(FXMLLoader.load(SceneLoader.class.getClassLoader().getResource("fxml/login.fxml"))));
				System.out.println("Scene loaded: Login");
				SceneDictionary.instance.dict.put("register", new Scene(
						FXMLLoader.load(SceneLoader.class.getClassLoader().getResource("fxml/register.fxml"))));
				System.out.println("Scene loaded: Register");
				SceneDictionary.instance.dict.put("main",
						new Scene(FXMLLoader.load(SceneLoader.class.getClassLoader().getResource("fxml/main.fxml"))));
				System.out.println("Scene loaded: Main");
				SceneDictionary.instance.dict.put("add_song", new Scene(
						FXMLLoader.load(SceneLoader.class.getClassLoader().getResource("fxml/add_song.fxml"))));
				System.out.println("Scene loaded: Add Song");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return SceneDictionary.instance.dict;
	}
}
