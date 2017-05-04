package parsers;
import java.util.Map;

public class Parserx10 extends AbstractParser {

	public Parserx10(String html) {
		super(html);
	}

	@Override
	protected int setPrimaryIndex() {
		return 0;
	}

	@Override
	protected String[] setColumns() {
		return new String[]{"id", "name", "status", "faculty",
				"major", "class", "program", "scholarship", "mail"};
	}
	@Override
	protected Map<String, Integer[]> getArrayTypes() {
		Map<String, Integer[]> types = super.getArrayTypes();
		types.put("departments", new Integer[]{3, 4, 5, 6});
		return types;
	}

}
