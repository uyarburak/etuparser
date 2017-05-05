package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import parsers.Parserx10;
import reader.StalkerPOST;

public class StudentIterator implements Iterator<String> {
	Iterator<String> iter;
	public StudentIterator() {
		Parserx10 parser;
		try {
			parser = new Parserx10(StalkerPOST.getHtml("http://kayit.etu.edu.tr/rapor/x10.php"));

			JSONArray arr = parser.parse();
			List<String> list = new ArrayList<String>(arr.size());
			for (Object object : arr) {
				list.add((String) ((JSONObject)object).get("id"));
			}
			iter = list.iterator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}

	@Override
	public String next() {
		return iter.next();
	}

}
