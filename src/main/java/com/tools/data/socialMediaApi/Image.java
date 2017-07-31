package com.tools.data.socialMediaApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	private String height;

    private String width;

    private String src;

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getSrc ()
    {
        return src;
    }

    public void setSrc (String src)
    {
        this.src = src;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [height = "+height+", width = "+width+", src = "+src+"]";
    }
}
