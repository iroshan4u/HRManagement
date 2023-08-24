package testData;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {

	public static Object[][] readdata() throws IOException, ParseException {

		JSONParser jsonparser = new JSONParser();
		FileReader reader = new FileReader("C:\\Users\\Iroshan\\eclipse-workspace\\Framework_HR_Management\\src\\test\\java\\testData\\Details.json");

		Object obj = jsonparser.parse(reader);		
		JSONObject userobj =  (JSONObject) obj;

		//String username = (String) userobj.get("username");
		//String password = (String) userobj.get("password");

		JSONArray usersarray = (JSONArray) userobj.get("users");

		Object[][] usersobj = new Object[usersarray.size()][2];

		for(int i=0; i < usersarray.size(); i ++) {

			JSONObject user = (JSONObject) usersarray.get(i);

			String uname = (String) user.get("username");
			String upass = (String) user.get("password");

			usersobj[i][0] = uname;
			usersobj[i][1] = upass;
		}

		return usersobj;
	}

}
