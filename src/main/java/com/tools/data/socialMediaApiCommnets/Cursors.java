package com.tools.data.socialMediaApiCommnets;

public class Cursors {
	 private String after;

	    private String before;

	    public String getAfter ()
	    {
	        return after;
	    }

	    public void setAfter (String after)
	    {
	        this.after = after;
	    }

	    public String getBefore ()
	    {
	        return before;
	    }

	    public void setBefore (String before)
	    {
	        this.before = before;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [after = "+after+", before = "+before+"]";
	    }
}
