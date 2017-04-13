package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import org.json.*;
import java.net.*;
import java.util.logging.*;


public class JSONWebSource {
	private static String basicURL;
	
	public JSONWebSource(String basicURL) {
		if(basicURL.charAt(basicURL.length()-1) != '/') {
			this.basicURL = basicURL + "/";
		}
		else {
			this.basicURL = basicURL;
		}
	}
	
	public static String createConnection(String relativeURL) {
		String completeURL = basicURL + relativeURL;
		
		try{
			StringBuilder URLResult = new StringBuilder();
			URL url;
			
			if(basicURL != null) {
				url = new URL(completeURL);
				HttpURLConnection connect = (HttpURLConnection) url.openConnection();
				connect.setRequestMethod("GET");
				BufferedReader URLReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				String line;
				while((line = URLReader.readLine()) != null) {
					URLResult.append(line);
				}
				URLReader.close();
				
			}
			return URLResult.toString();
		}
		catch (MalformedURLException ex) {
			Logger logger = Logger.getLogger(JSONWebSource.class.getName());
			logger.log(Level.SEVERE, "MalformedURLException", ex);
        } 
		catch (IOException ex) {}
		return "Failed";
	}

	public List<String[]> getJSONStudent() {
		String IDList = createConnection("studentlist");
		JSONArray studentIDs = new JSONArray(IDList);
		List<String[]> studentList = new ArrayList<>();
		String[] header = {"ID", "First", "Last", "Email"};
		studentList.add(header);
		
		for(Object studentID : studentIDs) {
			String student = createConnection("student/" + studentID);
			JSONObject object = new JSONObject(student);
			String[] array = {object.getString("id"), object.getString("first"), object.getString("last"), object.getString("email")};
			studentList.add(array);
		}
		return studentList;
	}
	
	public List<String[]> getJSONCourse() {
		String IDList = createConnection("courselist");
		JSONArray courseIDS = new JSONArray(IDList);
		List<String[]> courseList = new ArrayList<>();
		String[] header = {"ID", "Term", "Year", "Size"};
		courseList.add(header);
		
		for(Object courseID : courseIDS) {
			String course = createConnection("course/" + courseID);
			JSONObject object = new JSONObject(course);
			String[] array = {object.getString("id"), object.getString("term"), object.getString("year"), "" + object.getInt("size")};
			courseList.add(array);
		}
		return courseList;
	}
		
	public List<String[]> getJSONGrades(String course) {
		List<String[]> gradeList = new ArrayList<String[]>();
		
		
		String courseID = createConnection("course/" + course);
		JSONObject temp = new JSONObject(courseID);
		JSONObject object = temp.getJSONObject("grades");
		
		JSONArray headerTemp = object.getJSONArray("colHeaders");
		JSONArray IDTemp = object.getJSONArray("rowHeaders");
		JSONArray dataTemp = object.getJSONArray("data");
		
		String[] header = new String[headerTemp.length()+1];
		header[0] = "ID";
		int k = 1;
		for(Object column : headerTemp) {
			header[k] = "" + column;
			System.out.println(header[k]);
			k += 1;
		}
		gradeList.add(header);
		
		for(int i = 0; i <IDTemp.length(); i++) {
			JSONArray subTemp = dataTemp.getJSONArray(i);
			String [] data = new String[subTemp.length()+1];
			data[0] = IDTemp.getString(i);
			for(int j = 0; j < subTemp.length(); j++) {
				data[j+1] = "" + subTemp.getInt(j);
			}
			gradeList.add(data);
		}
		
		return gradeList;
	}
}