package com.tools.data.salesOnSpeed;

public class PrimaryPhone {
	 private String _creator;

	    private String _id;

	    private String created;

	    private String updated;

	    private String customFields;

	    private String __v;

	    private String number;

	    private String type;

	    public String get_creator ()
	    {
	        return _creator;
	    }

	    public void set_creator (String _creator)
	    {
	        this._creator = _creator;
	    }

	    public String get_id ()
	    {
	        return _id;
	    }

	    public void set_id (String _id)
	    {
	        this._id = _id;
	    }

	    public String getCreated ()
	    {
	        return created;
	    }

	    public void setCreated (String created)
	    {
	        this.created = created;
	    }

	    public String getUpdated ()
	    {
	        return updated;
	    }

	    public void setUpdated (String updated)
	    {
	        this.updated = updated;
	    }

	    public String getCustomFields ()
	    {
	        return customFields;
	    }

	    public void setCustomFields (String customFields)
	    {
	        this.customFields = customFields;
	    }

	    public String get__v ()
	    {
	        return __v;
	    }

	    public void set__v (String __v)
	    {
	        this.__v = __v;
	    }

	    public String getNumber ()
	    {
	        return number;
	    }

	    public void setNumber (String number)
	    {
	        this.number = number;
	    }

	    public String getType ()
	    {
	        return type;
	    }

	    public void setType (String type)
	    {
	        this.type = type;
	    }

	    @Override
	    public String toString()
	    {
	        return "PromaryPhone [_creator = "+_creator+", _id = "+_id+", created = "+created+", updated = "+updated+", customFields = "+customFields+", __v = "+__v+", number = "+number+", type = "+type+"]";
	    }
}
