package EchotuneClient.entities;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import EchotuneClient.request.methods.GETRequest;
import EchotuneClient.socket.SocketHandler;

public class Song {
	public int id;
	public String title;
	public String artist;
	public String genre;
	public String url;
	public String addedBy;

	public Song(int id, String title, String artist, String genre, String url, String addedBy) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.url = url;
		this.addedBy = addedBy;
	}

	public static Song[] actuallyGetAllSongs() {
		// What does this even do? Well, that's between me, God and Java's Type Erasure
		// """"feature""""
		Type ttk = new TypeToken<ArrayList<Song>>() {
		}.getType();

		try {
			@SuppressWarnings({ "unchecked", "null" })
			ArrayList<Song> songs = SocketHandler.fetch(new GETRequest("songs", "getAllSongs", new Object[] {}),
					response -> {
						if (response != null) {
							System.out.println(response.data);
							return (ArrayList<Song>) response.data;
						}
						System.out.println(response.data);
						return new ArrayList<Song>();
					}, ttk);

			Song[] ret = new Song[songs.size()];

			for (int i = 0; i < songs.size(); i++) {
				System.out.println(songs.getClass().getName());
				try {
					Object getSong = songs.get(i);
					@SuppressWarnings("unchecked")
					LinkedTreeMap<Object, Object> t = (LinkedTreeMap<Object, Object>) getSong;
					for (int j = 0; j < t.size(); j++) {
						System.out.println(t.get(j));
					}
					Song song = new Song(((Double) t.get("id")).intValue(), (String) t.get("title"),
							(String) t.get("artist"), (String) t.get("genre"), (String) t.get("url"),
							String.valueOf(t.get("addedBy")));
					ret[i] = song;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return new Song[] {};
		}
	}
}
