package com.tools.data.socialMediaApi;

public class Properties {
	 private String text;

	    private String name;

	    public String getText ()
	    {
	        return text;
	    }

	    public void setText (String text)
	    {
	        this.text = text;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [text = "+text+", name = "+name+"]";
	    }
}
