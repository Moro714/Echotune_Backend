package EchotuneClient.controller;

import java.net.URL;
import java.util.ResourceBundle;

import EchotuneClient.components.ListSongComponent;
import EchotuneClient.entities.Song;
import EchotuneClient.scene.SceneLoader;
import EchotuneClient.scene.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;

public class MainSceneController implements Initializable {

	@FXML
	private Button addSongButton;

	@FXML
	private Button yourSongsButton;

	@FXML
	private Button addReviewButton;

	@FXML
	private VBox songList;
	
    @FXML
    private TextField searchInputField;
    
   

//	@FXML
//	void initialize() {
//		
//	}
    
    @FXML
    void onSearchButtonPressed(ActionEvent event) {
    	GridPane pane = new GridPane();
		ListSongComponent.addHeaders(pane);
		Song[] songs = Song.actuallyGetAllSongs();
		for(int i = 0; i < songs.length; i++) {
			if(songs[i].title.contains(searchInputField.getText())) {
				ListSongComponent.addComponent(pane, songs[i], i);
			}
		}
		songList.getChildren().setAll(pane);
    }

	@FXML
	void onAddSongButtonPressed(ActionEvent event) {
		SceneLoader.getInstance().setScene(Scenes.ADD_SONG, event);
	}

	@FXML
	void onRefreshButtonPressed(ActionEvent event) {
		GridPane pane = new GridPane();
		ListSongComponent.addHeaders(pane);
		Song[] songs = Song.actuallyGetAllSongs();
		for(int i = 0; i < songs.length; i++) {
			ListSongComponent.addComponent(pane, songs[i], i);
		}
		songList.getChildren().setAll(pane);
	}

	public void initialize(URL location, ResourceBundle resources) {
		GridPane pane = new GridPane();
		ListSongComponent.addHeaders(pane);
		Song[] songs = Song.actuallyGetAllSongs();
		for(int i = 0; i < songs.length; i++) {
			ListSongComponent.addComponent(pane, songs[i], i);
			
		}
		songList.getChildren().setAll(pane);
	}

}
