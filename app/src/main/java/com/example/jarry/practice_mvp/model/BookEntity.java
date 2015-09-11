package com.example.jarry.practice_mvp.model;

import org.json.JSONObject;

import java.util.ArrayList;

public class BookEntity {

    private JSONObject rating;
    private String subtitle;
    private ArrayList<String> author;
    private String pubdate;
    private ArrayList<JSONObject> tags;
    private String origin_title;
    private String image;
    private String binding;
    private ArrayList<String> translator;
    private String catalog;
    private int pages;
    private JSONObject images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;

    private int stock;

    public void setRating(JSONObject rating) {
        this.rating = rating;
    }
    public JSONObject getRating() {
        return this.rating;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public String getSubtitle() {
        return this.subtitle;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }
    public ArrayList<String > getAuthor() {
        return this.author;
    }

    public void setPubdate(String pubdate) {
        this.pubdate =pubdate;
    }
    public String getPubdate() {
        return this.pubdate;
    }

    public void setTags(ArrayList<JSONObject> tags) {
        this.tags = tags;
    }
    public ArrayList<JSONObject> getTags() {
        return this.tags;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }
    public String getOrigin_title () {
        return this.origin_title;
    }

    public void setImage (String image) {
        this.image = image;
    }
    public String getImage() {
        return this.image;
    }

    public void setBinding (String binding) {
        this.binding = binding;
    }
    public String getBinding() {
        return this.binding;
    }

    public void setTranslator (ArrayList<String> translator) {
        this.translator = translator;
    }
    public ArrayList<String> getTranslator () {
        return this.translator = translator;
    }

    public void setCatalog (String catalog) {
        this.catalog = catalog;
    }
    public String getCatalog() {
        return this.catalog;
    }

    public void setPages (int pages) {
        this.pages = pages;
    }
    public int getPages (){
        return this.pages;
    }

    public void setImages(JSONObject images) {
        this.images = images;
    }
    public JSONObject getImages() {
        return  this.images;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
    public String getAlt () {
        return this.alt;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId () {
        return this.id;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getPublisher () {
        return this.publisher;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }
    public String getIsbn10() {
        return this.isbn10;
    }

    public void setIsbn13 (String isbn13) {
        this.isbn13 = isbn13;
    }
    public String getIsbn13() {
        return this.isbn13;
    }

    public void setTitle (String title) {
        this.title =title;
    }
    public String getTitle () {
        return this.title;
    }

    public void setUrl (String url) {
        this.url = url;
    }
    public String getUrl () {
        return this.url;
    }

    public void setAlt_title (String alt_title) {
        this.alt_title = alt_title;
    }
    public String getAlt_title () {
        return this.alt_title;
    }

    public void setAuthor_intro (String author_intro) {
        this.author_intro = author_intro;
    }
    public String getAuthor_intro () {
        return this.author_intro;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getSummary() {
        return this.summary;
    }

    public void setPrice (String price) {
        this.price = price;
    }
    public String getPrice () {
        return this.price;
    }

    public void setStock (int stock) {
        this.stock = stock;
    }
    public int getStock () {
        return this.stock;
    }

}
