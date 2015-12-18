package com.tools.data.soap;

import java.util.List;

public class CategoryModel {

	private String parentId;
	private String name;
	private String isActive;
	private String includeInMenu;
	private String urlKey;
	private String defaultSortBy;
	private List<String> availableSortBy;
	private String id;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIncludeInMenu() {
		return includeInMenu;
	}

	public void setIncludeInMenu(String includeInMenu) {
		this.includeInMenu = includeInMenu;
	}

	public String getUrlKey() {
		return urlKey;
	}

	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}

	public String getDefaultSortBy() {
		return defaultSortBy;
	}

	public void setDefaultSortBy(String defaultSortBy) {
		this.defaultSortBy = defaultSortBy;
	}

	public List<String> getAvailableSortBy() {
		return availableSortBy;
	}

	public void setAvailableSortBy(List<String> availableSortBy) {
		this.availableSortBy = availableSortBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
