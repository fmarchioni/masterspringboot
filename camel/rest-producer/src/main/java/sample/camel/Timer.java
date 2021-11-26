package sample.camel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

 
public class Timer {

 

	private String data;

    public Timer() {
    }

    public Timer(String data) {
        this.data = data;
    }

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

 

   
}