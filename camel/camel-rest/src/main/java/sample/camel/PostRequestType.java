package sample.camel;

public class PostRequestType {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public PostRequestType(String name) {
		super();
		this.name = name;
	}
}