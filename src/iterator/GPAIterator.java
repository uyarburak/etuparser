package iterator;

import java.util.Iterator;

import reader.StalkerPOST;

public class GPAIterator implements Iterator<String> {
	private Iterator<String> iter;
	public GPAIterator() {
		iter = new StudentIterator();
	}

	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}

	@Override
	public String next() {
		String number = iter.next();
		return StalkerPOST.getStudentNote(number);
	}
	
}
