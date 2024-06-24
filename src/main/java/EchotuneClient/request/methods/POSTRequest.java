package EchotuneClient.request.methods;

import java.util.Map;

import EchotuneClient.request.Request;
import EchotuneClient.request.RequestMethod;

public class POSTRequest extends Request {
	public Map<?, ?> data;
	
	public POSTRequest(String target, String invoke, Map<?, ?> data) {
		super(target);
		this.method = RequestMethod.POST;
		this.invoke = invoke;
		this.data = data;
	}
}
