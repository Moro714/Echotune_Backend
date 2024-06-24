package EchotuneClient.test.users;

import EchotuneClient.request.methods.POSTRequest;
import EchotuneClient.socket.SocketHandler;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestAddUsers {
	@Test
	public void testAddUsers() {
		int failed = 0;
		ArrayList<String> responseCodes = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> _user = new HashMap<String, String>();
			_user.put("username", this.generateRandomString(8));
			_user.put("password", generateRandomString("abcDEFghiJKLmnoPQRstuVWXyz0123456789!@#$"));

			SocketHandler.fetch(new POSTRequest("users", "addUser", _user), res -> {
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
