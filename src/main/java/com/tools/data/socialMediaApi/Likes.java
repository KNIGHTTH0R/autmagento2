package com.tools.data.socialMediaApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Likes {
	 private Summary summary;

	    private String[] data;

	    public Summary getSummary ()
	    {
	        return summary;
	    }

	    public void setSummary (Summary summary)
	    {
	        this.summary = summary;
	    }

	    public String[] getData ()
	    {
	        return data;
	    }

	    public void setData (String[] data)
	    {
	        this.data = data;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [summary = "+summary+", data = "+data+"]";
	    }
}
