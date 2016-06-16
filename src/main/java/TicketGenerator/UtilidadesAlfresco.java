package TicketGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;


public class UtilidadesAlfresco {

	private File file;

	private static String getAlfticket() throws IOException, JSONException {
		URL url = new URL(
				"http://alfresco.imit.cl:8080/alfresco/service/api/login?u=rsalazar&pw=redhat2015&format=json");
		URLConnection con = url.openConnection();
		InputStream in = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		String json = IOUtils.toString(in, encoding);
		JSONObject getData = new JSONObject(json);

		return getData.getJSONObject("data").get("ticket").toString();
	}

	public static void main(String args[]) throws IOException {
		String ticketURLResponse = getAlfticket(); 
		File f = new File("D:/bloqueo1.pdf"); 
		uploadDocument(ticketURLResponse, f, "bloqueo1.pdf", "application/pdf", "description", null); 

	}

	

	public static void uploadDocument(String authTicket, File fileobj,
			String filename, String filetype, String description,
			String destination) {
		try { 
			String urlString = "http://alfresco.imit.cl:8080/alfresco/service/api/upload?alf_ticket="+ authTicket;
			System.out.println("The upload url:::" + urlString);
			HttpClient client = new HttpClient();							
			PostMethod mPost = new PostMethod(urlString);
			Part[] parts = {
					new FilePart("filedata", filename, fileobj, filetype, null),
					new StringPart("filename", filename),
					new StringPart("description", description),
//					new StringPart("destination", "workspace://SpacesStore/5e8fece9-e5dc-4482-8ebf-322127550cdd"),
					new StringPart("description", description),
					new StringPart("siteid", "subdere-site"),
					new StringPart("containerid", "documentLibrary"),
					new StringPart("uploaddirectory", "/ucontrol/dipres")
			};
			mPost.setRequestEntity(new MultipartRequestEntity(parts, mPost.getParams()));
			int statusCode1 = client.executeMethod(mPost);
			System.out.println("statusLine>>>" + statusCode1 + "......"
					+ "\n status line \n" + mPost.getStatusLine() + "\nbody \n"
					+ mPost.getResponseBodyAsString());
			mPost.releaseConnection();
 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	


}
