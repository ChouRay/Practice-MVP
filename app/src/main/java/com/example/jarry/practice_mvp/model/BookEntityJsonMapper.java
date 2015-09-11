package com.example.jarry.practice_mvp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by zhouzili on 2015/4/28.
 */
public class BookEntityJsonMapper {

    public BookEntityJsonMapper() {

    }

    public BookEntity transformBookEntity(String bookJsonResponse) throws JSONException{
        try {
            BookEntity bookEntity = new BookEntity();
            JSONObject jsonObject = new JSONObject(bookJsonResponse);

            bookEntity.setRating(jsonObject.getJSONObject("rating"));
            bookEntity.setSubtitle(jsonObject.getString("subtitle"));
            JSONArray jsonArrayAuthor = jsonObject.getJSONArray("author");
            ArrayList<String> author = new ArrayList<>();
            for (int i=0; i< jsonArrayAuthor.length(); i++) {
                author.add(jsonArrayAuthor.getString(i));
            }
            bookEntity.setAuthor(author);
            bookEntity.setPubdate(jsonObject.getString("pubdate"));
            JSONArray jsonArrayTags = jsonObject.getJSONArray("tags");
            ArrayList<JSONObject> tags = new ArrayList<>();
            for (int i=0; i< jsonArrayTags.length(); i++) {
                tags.add(jsonArrayTags.getJSONObject(i));
            }
            bookEntity.setTags(tags);
            bookEntity.setOrigin_title(jsonObject.getString("origin_title"));
            bookEntity.setImage(jsonObject.getString("image"));
            bookEntity.setBinding(jsonObject.getString("binding"));
            JSONArray jsonArrayTranslator = jsonObject.getJSONArray("translator");
            ArrayList<String> translator = new ArrayList<>();
            for (int i=0; i< jsonArrayTranslator.length(); i++) {
                translator.add(jsonArrayTranslator.getString(i));
            }
            bookEntity.setTranslator(translator);
            bookEntity.setCatalog(jsonObject.getString("catalog"));
            bookEntity.setPages(Integer.parseInt(jsonObject.getString("pages")));
            bookEntity.setImages(jsonObject.getJSONObject("images"));
            bookEntity.setAlt(jsonObject.getString("alt"));
            bookEntity.setId(jsonObject.getString("id"));
            bookEntity.setPublisher(jsonObject.getString("publisher"));
            bookEntity.setIsbn10(jsonObject.getString("isbn10"));
            bookEntity.setIsbn13(jsonObject.getString("isbn13"));
            bookEntity.setTitle(jsonObject.getString("title"));
            bookEntity.setUrl(jsonObject.getString("url"));
            bookEntity.setAlt_title(jsonObject.getString("alt_title"));
            bookEntity.setAuthor_intro(jsonObject.getString("author_intro"));
            bookEntity.setSummary(jsonObject.getString("summary"));
            bookEntity.setPrice(jsonObject.getString("price"));
            return bookEntity;

        }catch (JSONException e) {
            throw e;
        }

    }


}
