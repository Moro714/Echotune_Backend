package EchotuneClient.controller;

import EchotuneClient.LocalStorage;
import EchotuneClient.entities.User;
import EchotuneClient.request.methods.GETRequest;
import EchotuneClient.scene.SceneLoader;
import EchotuneClient.scene.Scenes;
import EchotuneClient.socket.SocketHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginSceneController {

	@FXML
	private TextField usernameInputField;

	@FXML
	private TextField passwordInputField;

	@FXML
	private Text errorText;

	@FXML
	void onLoginButtonClicked(ActionEvent event) {
		try {
			User user = SocketHandler.fetch(
					new GETRequest("users", "getUserByName", new Object[] { this.usernameInputField.getText() }),
					response -> {
						return (User) response.data;
					}, User.class);
			if (user.username == null || user.password == null) {
				this.errorText.setText("User " + this.usernameInputField.getText() + " not found.");
				return;
			}
			if (!user.password.equals(passwordInputField.getText())) {
				this.errorText.setText("Wrong password.");
				return;
			}
			LocalStorage.getInstance().setUserID(user.id);
			LocalStorage.getInstance().setUsername(user.username);
//			
//			SceneLoader.getInstance().setScene("main", event);
			SceneLoader.getInstance().setScene(Scenes.MAIN, event);
//			System.out.println("Salut");
		} catch (ClassCastException e) {
			// response.data = "Internal server error."
			this.errorText.setText("Something went wrong. Please try again later.");
		}
	}

	@FXML
	void onRegisterAnchorClicked(ActionEvent event) {
		SceneLoader.getInstance().setScene(Scenes.REGISTER, event);
	}

}
