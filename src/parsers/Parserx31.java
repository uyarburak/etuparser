package parsers;
import java.util.Map;

public class Parserx31 extends AbstractParser {

	public Parserx31(String html) {
		super(html);
	}

	@Override
	protected int setPrimaryIndex() {
		return 1;
	}

	@Override
	protected String[] setColumns() {
		return new String[]{"department", "id", "name", "surname",
				"scholarship", "course_code", "section", "mail"};
	}
	@Override
	protected Map<String, Integer[]> getArrayTypes() {
		Map<String, Integer[]> types = super.getArrayTypes();
		types.put("departments", new Integer[]{0});
		types.put("sections", new Integer[]{5, 6});
		return types;
	}
}
