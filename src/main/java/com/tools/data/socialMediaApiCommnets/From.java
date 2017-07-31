package com.tools.data.socialMediaApiCommnets;

public class From {
	 private Picture picture;

	    private String id;

	    private String name;

	    public Picture getPicture ()
	    {
	        return picture;
	    }

	    public void setPicture (Picture picture)
	    {
	        this.picture = picture;
	    }

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
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
	        return "ClassPojo [picture = "+picture+", id = "+id+", name = "+name+"]";
	    }
}
