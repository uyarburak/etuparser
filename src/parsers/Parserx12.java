package parsers;

public class Parserx12 extends AbstractParser {

	public Parserx12(String html) {
		super(html);
	}

	@Override
	protected int setPrimaryIndex() {
		return 0;
	}

	@Override
	protected String[] setColumns() {
		return new String[]{"code", "title", "size"};
	}

}
