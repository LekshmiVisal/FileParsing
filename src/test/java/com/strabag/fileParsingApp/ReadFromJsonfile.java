package com.strabag.fileParsingApp;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadFromJsonfile {

	public static void main(String[] args) throws IOException, ParseException {

		JSONParser jsonparsor = new JSONParser();

		FileReader reader = new FileReader(".\\jsonFiles\\GeoClass.json");

		Object obj = jsonparsor.parse(reader);

		JSONObject geoClassjsonObj = (JSONObject) obj;

		String secName = (String) geoClassjsonObj.get("name");

		System.out.println("secName :" + secName);

		JSONArray array = (JSONArray) geoClassjsonObj.get("geologicalClasses");

		for (int i = 0; i < array.size(); i++) {
			JSONObject geoClass = (JSONObject) array.get(i);
			String className = (String) geoClass.get("name");
			String classCode = (String) geoClass.get("code");

			System.out.println("className :" + className + " & classCode :" + classCode);
		}
	}

}
