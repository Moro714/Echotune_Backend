package EchotuneClient.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.google.gson.Gson;

import EchotuneClient.request.Request;
import EchotuneClient.request.Response;
import EchotuneClient.request.methods.GETRequest;
import EchotuneClient.request.methods.POSTRequest;

public class SocketHandler {
	public static void testRequests() {
		Map<String, String> _user = new HashMap<String, String>();
		_user.put("username", "rares");
		_user.put("password_hash", "parola123");

		try {
			System.out.println(SocketHandler.sendRequest(new POSTRequest("users", "addUser", _user)));
			System.out.println(
					SocketHandler.sendRequest(new GETRequest("users", "getUserById", new Object[] { (int) 1 })));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static <T> T fetch(Request req, Function<Response, T> func) {
		try {
			T ret = func.apply(SocketHandler.sendRequest(req));
			return ret;
		} catch (IOException e) {
			T ret = func.apply(new Response("503", "Internal server error."));
			return ret;
		}
	}
	
	public static <T> T fetch(Request req, Function<Response, T> func, Type castTo) {
		try {
			T ret = func.apply(SocketHandler.sendRequest(req, castTo));
			return ret;
		} catch (IOException e) {
			T ret = func.apply(new Response("503", "Internal server error."));
			return ret;
		}
	}

	private static Response sendRequest(Request req, Type castTo) throws IOException {
		Gson jsonParser = new Gson();
		Socket socket = new Socket("127.0.0.1", 8080);
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		out.writeUTF(jsonParser.toJson(req));
		out.flush();
		out.close();
		socket.close();
		Response res = jsonParser.fromJson(SocketHandler.awaitResponse(), Response.class);
		return new Response(res.responseCode, res.data, castTo);
	}
	
	private static Response sendRequest(Request req) throws IOException {
		Gson jsonParser = new Gson();
		Socket socket = new Socket("127.0.0.1", 8080);
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		out.writeUTF(jsonParser.toJson(req));
		out.flush();
		out.close();
		socket.close();
		return jsonParser.fromJson(SocketHandler.awaitResponse(), Response.class);
	}

	private static String awaitResponse() throws IOException {
		Socket socket = new Socket("127.0.0.1", 8080);
		DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		String data = in.readUTF();
		in.close();
		socket.close();
		return data;
	}

	
}