package iterator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import reader.StalkerPOST;

public abstract class AbstractIterator implements Iterator<String> {
	public enum Type {ST_TIMETABLE, COURSE_ST_LIST, COURSE_TIMETABLE,
		ROOM_TIMETABLE, FREE_ROOMS, INS_TIMETABLE, DEP_CLASS_TIMETABLE};
	public static final String POST_URL = "http://kayit.etu.edu.tr/Ders/Ders_prg.php";
	public static final String GET_URL = "http://kayit.etu.edu.tr/Ders/_Ders_prg_start.php";
	protected static String HTML;
	static{
		try {
			HTML = StalkerPOST.getHtml(GET_URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String[] values;
	private String elementName;
	private String[] button;
	private int index;
	
	public AbstractIterator() {
	}
	
	public AbstractIterator(String elementName) {
		this.index = 0;
		this.elementName = elementName;
		values = setValues();
		button = getButton();
		System.out.println(Arrays.toString(values));
	}
	
	@Override
	public boolean hasNext(){
		return index < values.length;
	}

	@Override
	public String next(){
		String value = values[index++];
		String data = "";
		try {
			data = URLEncoder.encode(elementName, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
			data += "&" + URLEncoder.encode(button[0], "UTF-8") + "=" + URLEncoder.encode(button[1], "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return StalkerPOST.httpPost(POST_URL, data);
	}

	protected abstract String[] getButton();
	protected String[] setValues(){
		Document doc = Jsoup.parse(HTML);
		Element element = doc.getElementsByAttributeValue("name", this.elementName).first();
		Elements options = element.getElementsByTag("option");
		int i = 0;
		String[] values = new String[options.size()];
		for (Element e : options) {
			values[i++] = e.attr("value");
		}
		return values;
	}

	public String getElementName() {
		return elementName;
	}
	
}
