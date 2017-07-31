package com.tools.data.socialMediaApi;

public class Friends {
	 private Summary summary;

	    private Data[] data;

	    private Paging paging;

	    public Summary getSummary ()
	    {
	        return summary;
	    }

	    public void setSummary (Summary summary)
	    {
	        this.summary = summary;
	    }

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
	        return "ClassPojo [summary = "+summary+", data = "+data+", paging = "+paging+"]";
	    }
}
