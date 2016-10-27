package com.tools.geolocation;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Result {

	@JsonIgnore
	private String formatted_address;
	@JsonIgnore
	private String postcode_localities;
	@JsonIgnore
	private boolean partial_match;

	private Geometry geometry;

	@JsonIgnore
	private Object address_components;
	@JsonIgnore
	private Object types;
	@JsonIgnore
	private Object place_id;

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public boolean isPartial_match() {
		return partial_match;
	}

	public void setPartial_match(boolean partial_match) {
		this.partial_match = partial_match;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public Object getAddress_components() {
		return address_components;
	}

	public void setAddress_components(Object address_components) {
		this.address_components = address_components;
	}

	public Object getTypes() {
		return types;
	}

	public void setTypes(Object types) {
		this.types = types;
	}

}
