package EchotuneClient.request;
import com.google.gson.annotations.SerializedName;

public enum RequestMethod {
	@SerializedName(value = "GET") GET, 
	@SerializedName(value = "POST") POST, 
	@SerializedName(value = "PATCH") PATCH, 
	@SerializedName(value = "DELETE") DELETE
}
