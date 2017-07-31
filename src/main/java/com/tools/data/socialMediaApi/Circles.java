package com.tools.data.socialMediaApi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Circles {
	private String facebook_name;

    private String facebook_group_id;

    private String facebook_privacy;

    @JsonIgnore
  //  private String facebook_source;
    

    public String getFacebook_name ()
    {
        return facebook_name;
    }

    public void setFacebook_name (String facebook_name)
    {
        this.facebook_name = facebook_name;
    }

    public String getFacebook_group_id ()
    {
        return facebook_group_id;
    }

    public void setFacebook_group_id (String facebook_group_id)
    {
        this.facebook_group_id = facebook_group_id;
    }

    public String getFacebook_privacy ()
    {
        return facebook_privacy;
    }

    public void setFacebook_privacy (String facebook_privacy)
    {
        this.facebook_privacy = facebook_privacy;
    }

   /* public String getFacebook_source ()
    {
        return facebook_source;
    }*/

  /*  public void setFacebook_source (String facebook_source)
    {
        this.facebook_source = facebook_source;
    }*/

    @Override
    public String toString()
    {
        return "ClassPojo [facebook_name = "+facebook_name+", facebook_group_id = "+facebook_group_id+", facebook_privacy = "+facebook_privacy+", facebook_source = ]";
    }
}
