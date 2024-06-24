package EchotuneClient.components;

import EchotuneClient.LocalStorage;
import EchotuneClient.entities.Song;
import EchotuneClient.request.methods.DELETERequest;
import EchotuneClient.scene.SceneLoader;
import EchotuneClient.socket.SocketHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import com.sun.javafx.application.HostServicesDelegate;

public class ListSongComponent {
	public static void addHeaders(GridPane pane) {
		Text songTitle = new Text("Song Title");
		songTitle.setFill(Paint.valueOf("WHITE"));
		songTitle.setFont(Font.font(14));
		songTitle.setUnderline(true);
		
		pane.addColumn(0, songTitle);
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setHgrow(Priority.ALWAYS);

		Text artistName = new Text("Artist");
		artistName.setFill(Paint.valueOf("WHITE"));
		artistName.setFont(Font.font(14));
		artistName.setUnderline(true);
		
		pane.addColumn(1, artistName);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.ALWAYS);
		
		Text genre = new Text("Genre");
		genre.setFill(Paint.valueOf("WHITE"));
		genre.setFont(Font.font(14));
		genre.setUnderline(true);
		
		pane.addColumn(2, genre);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		
		Text url = new Text("Link");
		url.setFill(Paint.valueOf("WHITE"));
		url.setFont(Font.font(14));
		url.setUnderline(true);
		
		pane.addColumn(3, url);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHgrow(Priority.ALWAYS);
		
		Text addedBy = new Text("AddedBy");
		addedBy.setFill(Paint.valueOf("WHITE"));
		addedBy.setFont(Font.font(14));
		addedBy.setUnderline(true);
		
		pane.addColumn(4, addedBy);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setHgrow(Priority.ALWAYS);
		
		Text action = new Text("Action");
		action.setFill(Paint.valueOf("WHITE"));
		action.setFont(Font.font(14));
		action.setUnderline(true);
		
		pane.addColumn(5, action);
		ColumnConstraints col5 = new ColumnConstraints();
		col5.setHgrow(Priority.ALWAYS);
		
		pane.getColumnConstraints().addAll(col0, col1, col2, col3, col4, col5);
	}
	public static void addComponent(GridPane pane, Song song, int i) {
		Text songTitle = new Text(song.title);
		songTitle.setFill(Paint.valueOf("WHITE"));
		songTitle.setFont(Font.font(16));

		Text artistName = new Text(song.artist);
		artistName.setFill(Paint.valueOf("WHITE"));
		artistName.setFont(Font.font(16));

		Text genre = new Text(song.genre);
		genre.setFill(Paint.valueOf("WHITE"));
		genre.setFont(Font.font(14));

		Button url = new Button("🔗 Open link");
		url.setTextFill(Paint.valueOf("WHITE"));
		url.setCursor(Cursor.HAND);
		url.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		url.setUnderline(true);
		url.setPadding(new Insets(-0.01, 0, 0, 0));
		
		url.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				HostServicesDelegate delegate = HostServicesDelegate.getInstance(SceneLoader.getInstance());
				delegate.showDocument(song.url);
			}
		});
		
		Text addedBy = new Text(song.addedBy);
		addedBy.setFill(Paint.valueOf("WHITE"));
		addedBy.setFont(Font.font(14));
		
		
		Button deleteButton = null;

		if (LocalStorage.getInstance().getUsername().equals(song.addedBy)) {
			deleteButton = new Button("x Delete");
			deleteButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Handle delete
//					SceneLoader.getInstance().setScene(Scenes.ADD_REVIEW, event);
					SocketHandler.fetch(new DELETERequest("songs", "deleteSong", new Object[] {song.id}), response -> {
						return response.data;
					});
				}
			});
		}
		
		if(deleteButton != null) {
			pane.addRow(i + 1, new Node[] { songTitle, artistName, genre, url, addedBy, deleteButton });
		} else {
			pane.addRow(i + 1, new Node[] { songTitle, artistName, genre, url, addedBy });
		}
	};
}
