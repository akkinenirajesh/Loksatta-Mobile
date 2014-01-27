package org.loksatta.android.io;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import org.loksatta.android.core.Feed;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * Feeds DeSerializer
 * 
 * <pre>
 * {
 * posts: [{
 * 	AuthorName: “Rajesh Akkineni”,
 * 	AuthorId: 1,
 * 	created_at: “18/01/2014 3:29:05 PM IST”,
 * 	updated_at: “18/01/2014 3:29:05 PM IST”,
 * 	title: “Some Title”,
 * 	summary: “Short summary of the content”,
 * 	content: “full text content”,
 * 	link: “http://loksatta.org/content/guid”,
 * 	Id: “unique post id”,
 * 	fb_id: “facebook_id_of_the_post”,
 * 	g_id: “google_id_of_the_post”,
 * 	likes_count: 125, 
 * 	share_count: 25,
 * 	comments_count: 25,
 * 	images: [“image1_guid”,“image2_guid”]
 * 	}],
 * }
 * 
 * <pre>
 * 
 * @author PrasannaKumar
 * 
 */
public class FeedDeserializer extends AbstractDeserializer<ArrayList<Feed>> {

	@Override
	public ArrayList<Feed> deserialize(JsonElement json, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		if (!json.isJsonObject()) {
			new JsonParseException("Invalid Data");
		}
		ArrayList<Feed> result = new ArrayList<Feed>();

		JsonObject feedsObj = json.getAsJsonObject();
		JsonArray feeds = feedsObj.get("posts").getAsJsonArray();

		Iterator<JsonElement> iterator = feeds.iterator();
		if (iterator.hasNext()) {
			JsonElement next = iterator.next();
			JsonObject feedJson = next.getAsJsonObject();
			// Parse Feed Json
			Feed feed = desirialize(feedJson);
			result.add(feed);
		}

		return result;
	}

	/**
	 * Deserializes the Feed json
	 * 
	 * @param json
	 * @return
	 */
	private Feed desirialize(JsonObject json) {
		Feed f = new Feed();
		deserialize(f, json);
		f.setAuthorName(json.get("AuthorName").getAsString());
		f.setAuthorId(json.get("AuthorId").getAsString());
		f.setTitle(json.get("Title").getAsString());
		f.setSummary(json.get("Summary").getAsString());
		f.setContent(json.get("Content").getAsString());
		f.setFacebookId(json.get("FbId").getAsString());
		f.setgPlusId(json.get("gId").getAsString());
		f.setLikesCount(json.get("LikesCount").getAsLong());
		f.setSharesCount(json.get("SharesCount").getAsLong());
		f.setCommentsCount(json.get("CommentsCount").getAsLong());
		JsonArray images = json.get("images").getAsJsonArray();
		Iterator<JsonElement> imagesItr = images.iterator();
		ArrayList<String> imgResult = new ArrayList<String>();
		while (imagesItr.hasNext()) {
			JsonElement next = imagesItr.next();
			imgResult.add(next.getAsString());
		}
		f.setImages(imgResult.toArray(new String[] {}));
		return f;
	}

}
