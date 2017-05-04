package parsers;

public class DuplicatedKeyException extends Exception {
	public DuplicatedKeyException() {
		super("Duplicated key");
	}
}
