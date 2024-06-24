package EchotuneClient.request;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

public class Response {
	public String responseCode;
	public Object data;
	
	public Response(String responseCode, Object data) {
		this.responseCode = responseCode;
		this.data = data;
	}
	
	public Response(String responseCode, Object data, Type castTo) {
		this.responseCode = responseCode;
		if(data instanceof LinkedTreeMap) {
			Gson jsonParser = new Gson();
			JsonObject json = jsonParser.toJsonTree(data).getAsJsonObject();
			data = jsonParser.fromJson(json, castTo);
		}
		this.data = data;
	}
}
