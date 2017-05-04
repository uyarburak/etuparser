package parsers;
import java.util.Map;

public class Parserx26 extends AbstractParser {

	public Parserx26(String html) {
		super(html);
	}

	@Override
	protected int setPrimaryIndex() {
		return 0;
	}

	@Override
	protected String[] setColumns() {
		return new String[]{"id", "name", "surname", "department",
				"mail", "advisor", "advisor_mail"};
	}
	@Override
	protected Map<String, Integer[]> getArrayTypes() {
		Map<String, Integer[]> types = super.getArrayTypes();
		types.put("departments", new Integer[]{3, 5, 6});
		return types;
	}
}
