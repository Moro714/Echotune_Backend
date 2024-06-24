package EchotuneClient;

import EchotuneClient.test.TestRunner;

import java.util.HashMap;
import java.util.Map;

import EchotuneClient.request.methods.GETRequest;
import EchotuneClient.request.methods.POSTRequest;
import EchotuneClient.scene.SceneLoader;
import EchotuneClient.socket.SocketHandler;
import EchotuneClient.test.TestRunner;

public class Client {
	public static void main(String args[]) {
		Map<String, String> _user = new HashMap<String, String>();
		_user.put("username", "rares");
		_user.put("password", "parola123");

		Object code = SocketHandler.fetch(new POSTRequest("users", "addUser", _user), response -> {
			return null;
		});

		Object data = SocketHandler.fetch(new GETRequest("users", "getUserById", new Object[] { (int) 1 }),
				response -> {
					return response.data;
				});

		System.out.println(code);
		System.out.println(data);
		
		try {
			SceneLoader.launch(SceneLoader.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		};
//		TestRunner.testAll();
	}
}
