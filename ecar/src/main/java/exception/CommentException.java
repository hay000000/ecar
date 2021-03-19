package exception;

public class CommentException extends Exception {
	private String url;
	public CommentException(String msg, String url) {
		super(msg);
		this.url=url;
	}
	public String getUrl() {
		return url;
	}
}
