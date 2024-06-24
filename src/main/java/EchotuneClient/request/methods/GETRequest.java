package EchotuneClient.request.methods;

import EchotuneClient.request.Request;
import EchotuneClient.request.RequestMethod;

public class GETRequest extends Request {
	public Object[] args;

	public GETRequest(String target, String invoke, Object[] args) {
		super(target);
		this.method = RequestMethod.GET;
		this.invoke = invoke;
		this.args = args;
	}
}