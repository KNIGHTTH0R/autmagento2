package com.tools.data.socialMediaApiCommnets;

public class Data {
	private String message;
	private String is_silhouette;
	private String url;
	private String id;
	private Likes likes;
	private Comments comments;
	private From from;
	private String created_time;

	
	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public String getIs_silhouette() {
		return is_silhouette;
	}

	public void setIs_silhouette(String is_silhouette) {
		this.is_silhouette = is_silhouette;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Likes getLikes() {
		return likes;
	}

	public void setLikes(Likes likes) {
		this.likes = likes;
	}

	public From getFrom() {
		return from;
	}

	public void setFrom(From from) {
		this.from = from;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	@Override
	public String toString() {
		return "ClassPojo [message = " + message + ", id = " + id + ", likes = " + likes + ", from = " + from
				+ ", created_time = " + created_time + "]";
	}
}
