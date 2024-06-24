package EchotuneClient.test.songs;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import EchotuneClient.LocalStorage;
import EchotuneClient.request.methods.POSTRequest;
import EchotuneClient.socket.SocketHandler;

public class TestAddSongs {
	@Test
	public void testAddSongs() {
		LocalStorage.getInstance().setUserID(0);
		LocalStorage.getInstance().setUsername("test_user");

		int failed = 0;
		ArrayList<String> responseCodes = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> _song = new HashMap<String, String>();
			_song.put("username", this.generateRandomString(8));
			_song.put("password", generateRandomString("abcDEFghiJKLmnoPQRstuVWXyz0123456789!@#$"));

			_song.put("title", this.generateRandomString(8));
			_song.put("artist", this.generateRandomString(12));
			_song.put("genre", this.generateRandomString(6));
			_song.put("url", this.generateRandomString(22));
			_song.put("added_by", LocalStorage.getInstance().getUsername());

			SocketHandler.fetch(new POSTRequest("songs", "addSong", _song), res -> {
				responseCodes.add(res.responseCode);
				return null;
			});
		}

		for (int i = 0; i < responseCodes.size(); i++) {
			if (responseCodes.get(i).equals("503")) {
				failed++;
			}
		}

		assertEquals(failed, 0);
	}

	private String generateRandomString(int length) {
		byte[] bytes = new byte[7]; // length is bounded by 7
		new Random().nextBytes(bytes);
		return new String(bytes, Charset.forName("UTF-8"));
	}

	private String generateRandomString(String sample) {
		StringBuilder str = new StringBuilder();
		Random rnd = new Random();
		while (str.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * sample.length());
			str.append(sample.charAt(index));
		}
		String saltStr = str.toString();
		return saltStr;
	}
}
