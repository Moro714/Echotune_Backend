package EchotuneClient.test.songs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import EchotuneClient.request.methods.DELETERequest;
import EchotuneClient.socket.SocketHandler;

public class TestRemoveSongs {
	@Test
	public void testAddUsers() {
		int failed = 0;
		ArrayList<String> responseCodes = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			SocketHandler.fetch(new DELETERequest("songs", "deleteSong", new Object[] { i }), res -> {
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
}
