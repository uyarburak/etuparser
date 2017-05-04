package parsers;

public class Parserx11 extends AbstractParser {

	public Parserx11(String html) {
		super(html);
	}

	@Override
	protected int setPrimaryIndex() {
		return 0;
	}

	@Override
	protected String[] setColumns() {
		return new String[]{"code", "title", "credits", "inner_capacity",
				"outer_capacity", "total_capacity", "user", "date"};
	}

}
