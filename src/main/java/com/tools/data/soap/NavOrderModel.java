package com.tools.data.soap;

import java.util.List;

public class NavOrderModel {

	private String incrementId;
	private List<NavOrderLinesModel> lines;

	public String getIncrementId() {
		return incrementId;
	}

	public void setIncrementId(String incrementId) {
		this.incrementId = incrementId;
	}

	public List<NavOrderLinesModel> getLines() {
		return lines;
	}

	public void setLines(List<NavOrderLinesModel> lines) {
		this.lines = lines;
	}
	

}
