package verlinden.jason.model;

public class TestDataFileOption {
	
	private String id;
	private String display;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDisplay() {
		return display;
	}
	
	public void setDisplay(String display) {
		this.display = display;
	}

	public TestDataFileOption() {}
	
	public TestDataFileOption(String id, String display) {
		this.id = id;
		this.display = display;
	}	
}
