package EchotuneClient.controller;

import java.util.HashMap;
import java.util.Map;

import EchotuneClient.request.methods.POSTRequest;
import EchotuneClient.scene.SceneLoader;
import EchotuneClient.scene.Scenes;
import EchotuneClient.socket.SocketHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterSceneController {

    @FXML
    private TextField usernameInputField;

    @FXML
    private TextField passwordInputField;

    @FXML
    private TextField repeatPasswordInputField;

    @FXML
    private Text errorText;

    @FXML
    void onLoginAnchorClicked(ActionEvent event) {
    	SceneLoader.getInstance().setScene(Scenes.LOGIN, event);
    }

    @FXML
    void onRegisterButtonClicked(ActionEvent event) {
    	final String ONE_DIGIT = "(?=.*[0-9])";  //a number must occur at least once
        final String LOWER_CASE = "(?=.*[a-z])";  //a lower case letter must occur at least once
        final String UPPER_CASE = "(?=.*[A-Z])";  //an upper case letter must occur at least once
        final String NO_SPACE = "(?=\\S+$)";  //no whitespace allowed in the entire string
        final String MIN_LENGTH = ".{8,}"; //at least 8 chars
        
        final String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE + NO_SPACE + MIN_LENGTH;
        
    	if(this.usernameInputField.getText().length() < 5) {
    		errorText.setText("Error: Username must have at least 5 characters.");
    		return;
    	}
    	if(this.passwordInputField.getText().length() < 5) {
    		errorText.setText("Error: Password must have at least 5 characters.");
    		return;
    	}
    	if(!this.passwordInputField.getText().matches(PATTERN)) {
    		errorText.setText("Error: Password must contain at least 1 number, 1 uppercase letter,\n and no whitespace.");
    		return;
    	}
    	if(!this.passwordInputField.getText().equals(this.repeatPasswordInputField.getText())) {
    		errorText.setText("Error: Passwords do not match.");
    		return;
    	}
    	
    	Map<String, String> user = new HashMap<String, String>();
    	user.put("username", this.usernameInputField.getText());
    	user.put("password", this.passwordInputField.getText());
    	SocketHandler.fetch(new POSTRequest("users", "addUser", user), response -> {
    		errorText.setText((String) response.data);
    		return null;
    	});
//    	SceneLoader.getInstance().setScene(Scenes.LOGIN, event);
    }

}
