package cl.subdere.componente.negocio.alfresco;

import java.io.File;
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
import org.json.JSONObject;

import cl.subdere.componente.negocio.dto.AlfrescoSucces;

import com.google.gson.Gson;

public class UtilidadesAlfresco {

	private static final Gson OBJ_GSON = new Gson();

	public static String generarTicket() {
		
		try {
			URL url = new URL(
					"http://alfresco.imit.cl:8080/alfresco/service/api/login?u=rsalazar&pw=redhat2015&format=json");
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			String encoding = con.getContentEncoding();
			encoding = encoding == null ? "UTF-8" : encoding;
			String json = IOUtils.toString(in, encoding);
			JSONObject getData = new JSONObject(json);

			return getData.getJSONObject("data").get("ticket").toString();
		} catch (Exception error) {
			System.out
					.println("error al crear un ticket en alfresco causado por -->"
							+ error.getMessage());
			return null;
		}
	}

		public static void main(String args[])
		{
			System.out.println(generarTicket());
		}
	
	public static void uploadDocument(String authTicket, File fileobj,
			String filename, String filetype, String description,
			String destination) {
		
		try {
			String urlString = "http://alfresco.imit.cl:8080/alfresco/service/api/upload?alf_ticket="
					+ authTicket;
			System.out.println("The upload url:::" + urlString);
			HttpClient client = new HttpClient();
			PostMethod mPost = new PostMethod(urlString);
			Part[] parts = {
					new FilePart("filedata", filename, fileobj, filetype, null),
					new StringPart("filename", filename),
					new StringPart("description", description),
					new StringPart("description", description),
					new StringPart("siteid", "subdere-site"),
					new StringPart("containerid", "documentLibrary"),
					new StringPart("uploaddirectory", "/ucontrol/dipres") };
			mPost.setRequestEntity(new MultipartRequestEntity(parts, mPost
					.getParams()));
			int statusCode1 = client.executeMethod(mPost);
			if (statusCode1 == 200) {

				AlfrescoSucces objAlfrescoSucces = OBJ_GSON.fromJson(
						mPost.getResponseBodyAsString(), AlfrescoSucces.class);
				System.out.println(objAlfrescoSucces.getNodeRef());
			} else {
				throw new Exception("problemas al subir el archivo");
			}
			// System.out.println("statusLine>>>" + statusCode1 + "......"
			// + "\n status line \n" + mPost.getStatusLine() + "\nbody \n"
			// + mPost.getResponseBodyAsString());
			mPost.releaseConnection();

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
