package com.monsterhunter.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.logging.log4j.Level;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraftforge.fml.common.FMLLog;

/**
 * Toolkit used to read and write Json information from files.
 * @author Landon
 *
 */
public class GsonToolkit {
	public static <T> T gsonFromFile(String path, Class<?> clazz) {
		Gson gson = new Gson();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			File file = new File(path);
			if(!file.exists()) {
				FMLLog.log(Level.FATAL, "[GsonToolkit] ERROR: FILE %S DOES NOT EXIST. CREATING FILE.", path);
				file.createNewFile();
			}

			System.out.println();
			return gson.fromJson(br, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void gsonToFile(Object obj, String path) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(obj);

		try {
			if(!path.toLowerCase().endsWith(".json"))
				path = path + ".json";

			FileWriter fw = new FileWriter(path);
			fw.write(json);
			fw.close();
			System.out.println("Successfully wrote file.");
		} catch (Exception e) {

		}
	}

	public static JsonObject jsonFromFile(String path) {
		try {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(new FileReader(path));
			return jsonElement.getAsJsonObject();
		} catch (Exception e) {
			return  null;
		}
	}
}
