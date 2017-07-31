package com.tools.data.socialMediaApi;

public class MeModel {
	  private String id;

	    private Friends friends;

	    private String name;

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    public Friends getFriends ()
	    {
	        return friends;
	    }

	    public void setFriends (Friends friends)
	    {
	        this.friends = friends;
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
	        return "ClassPojo [id = "+id+", friends = "+friends+", name = "+name+"]";
	    }
}
