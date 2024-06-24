package EchotuneClient.request;

public class Request {
	protected RequestMethod method;
	protected String target;
	protected String invoke;

	public Request(String target) {
		this.target = target;
	}
}
