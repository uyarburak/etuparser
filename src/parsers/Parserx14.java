package parsers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parserx14 {
//	private String studentId;
//
//	public Parserx14(String html) {
//	}
//
//	@Override
//	protected JSONArray parseIt() {
//		JSONArray arr = new JSONArray();
//		Document doc = Jsoup.parse(getHTML());
//		Elements elements = doc.getElementsByTag("tr");
//		JSONObject kunye1 = getKunye(elements.get(1));
//		JSONObject kunye2 = getKunye(elements.get(2), studentId);
//		arr.add(kunye1);
//		if(kunye2 != null){
//			arr.add(kunye2);
//		}
//		return arr;
//	}
//	
//	private JSONObject getKunye(Element element, String id){
//		if(!element.html().contains(id))
//			return null;
//		return getKunye(element);
//	}
//	private JSONObject getKunye(Element element){
//		Elements elements = element.getElementsByTag("td");
//		String[] attrs = new String[7];
//		for (int i = 0; i < 7; i++) {
//			attrs[i] = elements.get(i).text();
//		}
//		JSONObject obj = new JSONObject();
//		obj.put("id", attrs[0]);
//		obj.put("name", attrs[1]);
//		obj.put("department", attrs[2]);
//		obj.put("mail", attrs[3]);
//		obj.put("gpa", attrs[4]);
//		obj.put("credit", attrs[5]);
//		obj.put("class", attrs[6]);
//		studentId = attrs[0];
//		return obj;
//	}

}
