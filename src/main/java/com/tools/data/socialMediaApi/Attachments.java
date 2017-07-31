package com.tools.data.socialMediaApi;

public class Attachments {
	 private Data[] data;

	    public Data[] getData ()
	    {
	        return data;
	    }

	    public void setData (Data[] data)
	    {
	        this.data = data;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [data = "+data+"]";
	    }
}
