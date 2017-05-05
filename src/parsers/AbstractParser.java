package parsers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public abstract class AbstractParser {
	private String tableName;
	private Map<String, JSONObject> map;
	private String html;
	private int primaryIndex;
	private String[] columns;
	private Set<Integer> indices;
	public AbstractParser(String html) {
		this.html = html;
		primaryIndex = setPrimaryIndex();
		columns = setColumns();
		map = new HashMap<String, JSONObject>();
		preParse();
	}
	public JSONArray parse() throws DuplicatedKeyException{
		parseIt();
		JSONArray arr = new JSONArray();
		for (JSONObject object : map.values()) {
			arr.add(object);
		}
		System.out.println(tableName);
		System.out.println(this.getClass().getSimpleName() + ": " + map.size());
		return arr;
	}
	private void preParse(){
		html = Jsoup.clean(html, Whitelist.relaxed());
		indices = new HashSet<Integer>();
		for (int i = 0; i < columns.length; i++) {
			indices.add(i);
		}
		for (Integer[] array : getArrayTypes().values()) {
			for (Integer integer : array) {
				indices.remove(integer);
			}
		}
		getTableName();
	}
	
	private void getTableName(){
		Document doc = Jsoup.parse(getHTML());
		tableName = doc.getElementsByTag("strong").get(1).text();
	}
	
	public String getName(){
		return tableName;
	}

	private void parseIt() throws DuplicatedKeyException {
		Document doc = Jsoup.parse(getHTML());
		Elements elements = doc.getElementsByTag("tr");
		Iterator<Element> iter = elements.iterator();
		iter.next();
		while(iter.hasNext()){
			Element element = iter.next();
			if(!iter.hasNext()){
				break;
			}
			Elements atts = element.getElementsByTag("td");
			Map<String, Integer[]> types = getArrayTypes();

			JSONObject object;
			if(map.containsKey(atts.get(primaryIndex).text())){
				if(types.isEmpty())
					throw new DuplicatedKeyException();
				object = map.get(atts.get(primaryIndex).text());
			}else{
				object = new JSONObject();
				for (Map.Entry<String, Integer[]> entry : types.entrySet()) {
					String key = entry.getKey();
					Integer[] value = entry.getValue();
					JSONArray arr = new JSONArray();
					object.put(key, arr);
				}
				for (Integer i : indices) {
					object.put(columns[i], atts.get(i).text());
				}
				if(atts.get(primaryIndex).text().isEmpty()){
					System.out.println("WTF");
					System.out.println(element.html());
					System.out.println(iter.next().html());
				}
				map.put(atts.get(primaryIndex).text(), object);
			}
			for (Map.Entry<String, Integer[]> entry : types.entrySet()) {
				String key = entry.getKey();
				Integer[] value = entry.getValue();
				JSONArray arr = (JSONArray) object.get(key);
				JSONObject inner = new JSONObject();
				for (Integer i : value) {
					inner.put(columns[i], atts.get(i).text());
				}
				arr.add(inner);
			}
			
		}
	}
	protected abstract int setPrimaryIndex();
	protected abstract String[] setColumns();
	
	protected Map<String, Integer[]> getArrayTypes(){
		return new HashMap<>();
	}

	protected String getHTML(){
		return html;
	}
}