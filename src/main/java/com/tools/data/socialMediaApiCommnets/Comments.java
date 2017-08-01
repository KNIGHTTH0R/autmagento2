package com.tools.data.socialMediaApiCommnets;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comments {
	 private List<Data> data;

	    private Paging paging;

	    

	    public List<Data> getData() {
			return data;
		}

		public void setData(List<Data> data) {
			this.data = data;
		}

		public Paging getPaging ()
	    {
	        return paging;
	    }

	    public void setPaging (Paging paging)
	    {
	        this.paging = paging;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [data = "+data+", paging = "+paging+"]";
	    }
}
