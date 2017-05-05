package iterator;

public class CourseTimetableIterator extends AbstractIterator {
	public CourseTimetableIterator() {
		super("dd_ders");
	}

	@Override
	protected String[] getButton() {
		return new String[]{"btn_ders", "Seçili Dersin Programını Göster"};
	}
}
