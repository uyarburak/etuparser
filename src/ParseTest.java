import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import parsers.*;

public class ParseTest {
	public static void main(String[] args){
		int[] numbers = {10, 11, 12, 26, 27, 28, 29, 30, 31, 32};
		for (int i : numbers) {
			test(i);
		}
	}

	public static String getHTML(int number) throws IOException{
		StringBuilder page = new StringBuilder();

		URL url = new URL(String.format("http://kayit.etu.edu.tr/rapor/x%02d.php", number));
		URLConnection conn = url.openConnection();

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "iso-8859-9"));
		String line;
		while ((line = rd.readLine()) != null) {
			page.append(line+"\n");
		}
		rd.close();
		return page.toString();
	}

	public static void test(int number){
		ClassLoader loader = ParseTest.class.getClassLoader();
		try {
			Class[] cArgs = {String.class};
			Class<AbstractParser> pr = (Class<AbstractParser>)loader.loadClass("parsers.Parserx"+number);
			AbstractParser parser = pr.getDeclaredConstructor(cArgs).newInstance(getHTML(number));
			FileWriter fw = new FileWriter(new File("/home/rootf/Desktop/jsons/"+parser.getName()+".json"));
			fw.write(parser.parse().toJSONString());
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
