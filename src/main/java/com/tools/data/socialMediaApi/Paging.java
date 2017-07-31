package com.tools.data.socialMediaApi;

public class Paging {
	private Cursors cursors;

    public Cursors getCursors ()
    {
        return cursors;
    }

    public void setCursors (Cursors cursors)
    {
        this.cursors = cursors;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cursors = "+cursors+"]";
    }
}
