package parsers;

public class Parserx28 extends AbstractParser {

	public Parserx28(String html) {
		super(html);
	}

	@Override
	protected int setPrimaryIndex() {
		return 0;
	}

	@Override
	protected String[] setColumns() {
		return new String[]{"id", "name", "surname", "department",
				"mail"};
	}

}
