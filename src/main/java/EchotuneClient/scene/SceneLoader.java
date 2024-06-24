package EchotuneClient.scene;

import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class SceneLoader extends Application {
	private static SceneLoader instance;
	private Map<String, Scene> dict;

	// Don't use this. it's public only because its required by jfx
	public SceneLoader() {
		super();
	}

	public static SceneLoader getInstance() {
		if (SceneLoader.instance == null) {
			SceneLoader.instance = new SceneLoader();
		}
		SceneLoader.instance.dict = SceneDictionary.getSceneDictionary();
		return SceneLoader.instance;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.dict = SceneDictionary.getSceneDictionary();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		// primaryStage becomes null after this.start(), so we can't persist it with a class instance variable
	}
	
	public void setScene(String sceneName, ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(this.dict.get(sceneName));
	}

	public void setScene(Scenes scene, ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene.getScene());
	}
	
	public void setScene(Scenes scene, Node node) {
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(scene.getScene());
	}
	
	public void setScene(Scenes scene, Stage stage) {
		stage.setScene(scene.getScene());
	}
}
