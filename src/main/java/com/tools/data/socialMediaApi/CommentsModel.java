package com.tools.data.socialMediaApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentsModel {
	 private Data[] data;

	    private Paging paging;

	    public Data[] getData ()
	    {
	        return data;
	    }

	    public void setData (Data[] data)
	    {
	        this.data = data;
	    }

	    public Paging getPaging ()
	    {
	        return paging;
	    }

	    public void setPaging (Paging paging)
	    {
	        this.paging = paging;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [data = "+data+", paging = "+paging+"]";
	    }
}
