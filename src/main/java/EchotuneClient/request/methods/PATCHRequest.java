package EchotuneClient.request.methods;

import java.util.Map;

import EchotuneClient.request.Request;
import EchotuneClient.request.RequestMethod;

public class PATCHRequest extends Request {
	public Object[] args;
	public Map<?, ?> data;

	public PATCHRequest(String target, Object[] args, Map<?, ?> data) {
		super(target);
		this.method = RequestMethod.PATCH;
		this.args = args;
		this.data = data;
	}
}
