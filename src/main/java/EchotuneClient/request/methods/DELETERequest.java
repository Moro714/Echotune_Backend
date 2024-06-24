package EchotuneClient.request.methods;

import EchotuneClient.request.RequestMethod;

public class DELETERequest extends GETRequest {
	public DELETERequest(String target, String invoke, Object[] args) {
		super(target, invoke, args);
		this.method = RequestMethod.DELETE;
	}
}