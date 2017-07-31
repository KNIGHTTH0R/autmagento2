package com.tools.data.socialMediaApi;

public class LiveStatusModel {
	private String duration;

    private String status;

    private String object_id;

    private String start_time;

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getObject_id ()
    {
        return object_id;
    }

    public void setObject_id (String object_id)
    {
        this.object_id = object_id;
    }

    public String getStart_time ()
    {
        return start_time;
    }

    public void setStart_time (String start_time)
    {
        this.start_time = start_time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", status = "+status+", object_id = "+object_id+", start_time = "+start_time+"]";
    }
}
