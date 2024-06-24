package EchotuneClient.controller;

import java.util.HashMap;
import java.util.Map;

import EchotuneClient.LocalStorage;
import EchotuneClient.entities.Song;
import EchotuneClient.request.methods.POSTRequest;
import EchotuneClient.scene.SceneLoader;
import EchotuneClient.scene.Scenes;
import EchotuneClient.socket.SocketHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AddSongSceneController {

    @FXML
    private TextField songNameInputField;

    @FXML
    private TextField artistNameInputField;

    @FXML
    private TextField genreInputField;

    @FXML
    private TextField urlInputField;
    
    @FXML
    private Text errorText;

    @FXML
    void onAddSongButtonClicked(ActionEvent event) {
    	if(songNameInputField == null) {
    		errorText.setText("Song name must not be empty.");
    		return;
    	}
    	if(artistNameInputField == null) {
    		errorText.setText("Artist name must not be empty.");
    		return;
    	}
    	if(genreInputField == null) {
    		errorText.setText("You must provide a song genre.");
    		return;
    	}
    	if(urlInputField == null) {
    		errorText.setText("You must provide a URL.");
    		return;
    	}
    	
    	Map<String, String> song = new HashMap<String, String>();
    	
    	song.put("title", this.songNameInputField.getText());
    	song.put("artist", this.artistNameInputField.getText());
    	song.put("genre", this.genreInputField.getText());
    	song.put("url", this.urlInputField.getText());
    	
    	song.put("added_by", LocalStorage.getInstance().getUsername());
    	
    	SocketHandler.fetch(new POSTRequest("songs", "addSong", song), response -> {
    		return response.data;
    	});
    	
    	Song[] songs = Song.actuallyGetAllSongs();
    	((Stage) ((Node) event.getSource()).getScene().getWindow()).setUserData(songs);
    	
    	SceneLoader.getInstance().setScene(Scenes.MAIN, event);
    }

    @FXML
    void onBackButtonPressed(ActionEvent event) {
    	Song[] songs = Song.actuallyGetAllSongs();
    	((Stage) ((Node) event.getSource()).getScene().getWindow()).setUserData(songs);
    	SceneLoader.getInstance().setScene(Scenes.MAIN, event);
    }

}
