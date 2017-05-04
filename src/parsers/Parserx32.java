package parsers;
import java.util.Map;

public class Parserx32 extends AbstractParser {

	public Parserx32(String html) {
		super(html);
	}

	@Override
	protected int setPrimaryIndex() {
		return 0;
	}

	@Override
	protected String[] setColumns() {
		return new String[]{"course_code", "course_title", "section",
				"instructor", "mail"};
	}
	@Override
	protected Map<String, Integer[]> getArrayTypes() {
		Map<String, Integer[]> types = super.getArrayTypes();
		types.put("sections", new Integer[]{2, 3, 4});
		return types;
	}

}
