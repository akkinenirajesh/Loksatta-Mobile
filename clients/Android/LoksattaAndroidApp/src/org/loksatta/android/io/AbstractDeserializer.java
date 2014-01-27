package org.loksatta.android.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.loksatta.android.core.AbstractCore;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class AbstractDeserializer<T> implements JsonDeserializer<T> {

	/**
	 * Deserializes Core Object
	 * 
	 * @param o
	 * @param json
	 */
	protected void deserialize(AbstractCore o, JsonObject json) {
		o.setId(json.get("Id").getAsString());
		o.setCreatedAt(asDate(json.get("CreatedAt")));
		o.setUpdatedAt(asDate(json.get("UpdatedAt")));
		o.setLink(json.get("Link").getAsString());
	}

	/**
	 * Parse Json String to Date
	 * 
	 * @param json
	 * @return
	 */
	protected Date asDate(JsonElement json) {
		String strDate = json.getAsString();
		try {
			return SimpleDateFormat.getInstance().parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

}
