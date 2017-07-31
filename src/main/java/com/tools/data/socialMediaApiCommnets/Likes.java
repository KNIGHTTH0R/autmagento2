package com.tools.data.socialMediaApiCommnets;

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
