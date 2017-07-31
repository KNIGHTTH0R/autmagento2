package com.tools.data.socialMediaApiCommnets;

public class Summary {
		private String can_like;

	    private String has_liked;

	    private String total_count;

	    public String getCan_like ()
	    {
	        return can_like;
	    }

	    public void setCan_like (String can_like)
	    {
	        this.can_like = can_like;
	    }

	    public String getHas_liked ()
	    {
	        return has_liked;
	    }

	    public void setHas_liked (String has_liked)
	    {
	        this.has_liked = has_liked;
	    }

	    public String getTotal_count ()
	    {
	        return total_count;
	    }

	    public void setTotal_count (String total_count)
	    {
	        this.total_count = total_count;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [can_like = "+can_like+", has_liked = "+has_liked+", total_count = "+total_count+"]";
	    }
}
