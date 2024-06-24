package EchotuneClient.scene;

import javafx.scene.Scene;

public enum Scenes {
	LOGIN(SceneDictionary.getSceneDictionary().get("login")), 
	REGISTER(SceneDictionary.getSceneDictionary().get("register")),
	MAIN(SceneDictionary.getSceneDictionary().get("main")),
	ADD_SONG(SceneDictionary.getSceneDictionary().get("add_song"));

	public final Scene scene;
	Scenes(Scene scene) {
		this.scene = scene;
	}
	
	public Scene getScene() {
		return this.scene;
	}
}
