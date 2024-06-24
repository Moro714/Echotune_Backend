package EchotuneClient.test.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import EchotuneClient.scene.SceneLoader;
import EchotuneClient.scene.Scenes;
import javafx.stage.Stage;

public class TestSceneLoading {
	@Test
	public void testAddUsers() {
		int failed = 0;
		SceneLoader loader = SceneLoader.getInstance();

		for (int i = 0; i < 10; i++) {
			try {
				SceneLoader.launch(SceneLoader.class);
				Stage stage = new Stage();
				loader.setScene(Scenes.LOGIN, stage);
				loader.setScene(Scenes.REGISTER, stage);
				loader.setScene(Scenes.MAIN, stage);
				loader.setScene(Scenes.ADD_SONG, stage);
				stage.close();
			} catch (Exception e) {
				failed++;
			}
		}

		assertEquals(failed, 0);
	}
}
