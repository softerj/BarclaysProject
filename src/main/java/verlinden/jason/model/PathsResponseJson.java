package verlinden.jason.model;

import java.util.List;

public class PathsResponseJson {

	private String userName;
	private List<String> routes;
	
	public String getUserName() {
		return userName;
	}
	
	public List<String> getRoutes() {
		return routes;
	}
	
	public PathsResponseJson(String userName, List<String> routes) {
		this.userName = userName;
		this.routes = routes;
	}
}
