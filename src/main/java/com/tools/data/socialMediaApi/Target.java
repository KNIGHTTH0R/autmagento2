package com.tools.data.socialMediaApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Target {
	private String id;

    private String embed_html;

    private String status;

    private String broadcast_start_time;

    private String live_status;

    private Video video;

    private String url;
    
    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmbed_html ()
    {
        return embed_html;
    }

    public void setEmbed_html (String embed_html)
    {
        this.embed_html = embed_html;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getBroadcast_start_time ()
    {
        return broadcast_start_time;
    }

    public void setBroadcast_start_time (String broadcast_start_time)
    {
        this.broadcast_start_time = broadcast_start_time;
    }

    public String getLive_status ()
    {
        return live_status;
    }

    public void setLive_status (String live_status)
    {
        this.live_status = live_status;
    }

    public Video getVideo ()
    {
        return video;
    }

    public void setVideo (Video video)
    {
        this.video = video;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", embed_html = "+embed_html+", status = "+status+", broadcast_start_time = "+broadcast_start_time+", live_status = "+live_status+", video = "+video+"]";
    }
}
