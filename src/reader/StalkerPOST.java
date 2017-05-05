package reader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class StalkerPOST {

	public static String httpPost(String path, String data) {
		StringBuilder page = new StringBuilder("");
		try {
			URL url = new URL(path);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "ISO8859-9"));
			String line;
			while ((line = rd.readLine()) != null) {
				page.append(line+"\n");
			}
			wr.close();
			rd.close();
		} 
		catch (Exception e) {
			System.err.println(e);
		}
		return page.toString();
	}

	public static String getHtml(String path) throws Exception {
		StringBuilder page = new StringBuilder("");

		URL url = new URL(path);
		URLConnection conn = url.openConnection();

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "ISO8859-9"));
		String line;
		while ((line = rd.readLine()) != null) {
			page.append(line+"\n");
		}
		rd.close();
		return page.toString();
	}
	
	public static String getStudentNote(String studentId) {
		try {
			String data = URLEncoder.encode("giris01", "UTF-8") + "=" + URLEncoder.encode(studentId, "UTF-8");
			return httpPost("http://kayit.etu.edu.tr/rapor/x14.php", data);
		}
		catch (Exception e) {
			System.err.println("student note exception:  " + e);
		}
		return "";
	}
}