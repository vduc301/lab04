package Model;

import com.google.gson.Gson;

public class JsonReturn {
	private int id;
	private String message;
	private Object data;
	public JsonReturn(int id, String message, Object data) {
		super();
		this.id = id;
		this.message = message;
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}
