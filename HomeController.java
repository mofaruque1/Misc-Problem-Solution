package controllers;

import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;

import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import play.Environment;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import play.mvc.*;
import redis.clients.jedis.Jedis;
import models.Blog;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

	/**
	 * An action that renders an HTML page with a welcome message. The configuration
	 * in the <code>routes</code> file means that this method will be called when
	 * the application receives a <code>GET</code> request with a path of
	 * <code>/</code>.
	 */

	private final Environment env;

	@Inject
	public HomeController(final Environment env) {
		// the environment is used to access local files
		this.env = env;
	}

	public Result index() {

		return ok("Hello world");
	}

	public Result hello(String name) {

		return ok("Hello " + name);
	}

	// Api for Feeding the data
	public Result data() throws IOException {

		JsonNode result = null;
		result = getDataFromFile();
		return ok(result);
	}
	
	
	// save data

	@SuppressWarnings("static-access")
	public Result save() {
		StringBuilder sb = new StringBuilder();

		Queue<JsonNode> queue = new LinkedList<>();
		String id = request().getQueryString("id");
		String title = request().getQueryString("title");
		String body = request().getQueryString("body");
		int idNo = Integer.parseInt(id);
		
		String receivedData = "{\"id\":"+idNo+",\"title\":\""+title+"\",\"body\":\""+body+"\"}";
		
		JsonNode jsonNode = getDataFromFile();
		
		int size = jsonNode.size();
		for(int i = 0;i<size;i++) {
			if((idNo-1)==i) {
				queue.offer(new Json().parse(receivedData));
				continue;
			}
			queue.offer(jsonNode.get(i));
		}
		sb.append("[");
		for(int j = 0;j<size;j++) {
			sb.append(queue.poll());
			if(j!=(size-1)) {
				sb.append(",");
			}
		}
		sb.append("]");
		
		// The name of the file to open.
        String fileName = "/Users/mdomor.faruque/eclipse-workspace/PlayJavaDemo/data/data.json";

        try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(sb.toString());
      

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
		
		return ok(sb.toString());
	}

	// get data from file
	public static JsonNode getDataFromFile() {
		JsonNode json  = null;
		File file = new File("/Users/mdomor.faruque/eclipse-workspace/PlayJavaDemo/data/data.json");
		try (FileInputStream is = new FileInputStream(file);) {
			json = Json.parse(is);
			
		} catch (IOException e) {
			e.getStackTrace();
		}
		return json;
	}

	
	
	// getdata from redis

	public static String getDataFromRedis() {

		String response = null;
		try {
			Jedis jedis = new Jedis("localhost");

			if (jedis.get("dataV1") == null) {
				String data = getDataFromServer();
				jedis.set("dataV1", data);
				response = data;
				// System.out.println("Data coming from the origin..........");
			} else {
				response = jedis.get("dataV1");
				// System.out.println("Data coming from redis server..........");
			}

		} catch (Exception e) {
			System.out.println("connection error...");
		}

		return response;

	}

	// getting the data from server

	private static String getDataFromServer() throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		// String urlString = "https://jsonplaceholder.typicode.com/posts/";
		String urlString = "https://raw.githubusercontent.com/mofaruque1/serverfile/master/data.json";

		URL url = new URL(urlString);
		InputStream is = null;
		InputStreamReader inputStreamReader = null;

		try {
			URLConnection conn = url.openConnection();
			is = conn.getInputStream();
			inputStreamReader = new InputStreamReader(is);
			int i;
			char c;
			while ((i = inputStreamReader.read()) != -1) {
				c = (char) i;
				stringBuilder.append(c);
				// System.out.print(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
		}
		return stringBuilder.toString();

		// System.out.println(stringBuilder.toString());
	}

}
