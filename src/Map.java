import java.util.*;

public class Map {

	private ArrayList<Route> availableRoutes;
	private Route currentRoute;
	
	public Map() {
		availableRoutes = new ArrayList<Route>();
		setupRoutes();
	}
	
	public void setupRoutes() {
		availableRoutes.add(new Route("Test Route 1", 100.0));
		availableRoutes.add(new Route("Test Route 2", 200.0));
		availableRoutes.add(new Route("Test Route 3", 500.0));
		availableRoutes.add(new Route("Test Route 4", 10000.0));
		availableRoutes.add(new Route("Test Route 5", 20000.0));
		availableRoutes.add(new Route("Test Route 6", 100000.0));
		currentRoute = availableRoutes.get(0);
	}
	
	public Route getCurrentRoute() {
		return currentRoute;
	}
	
	public void setCurrentRoute(int routeIndex) {
		currentRoute = availableRoutes.get(routeIndex);
	}
	
	public int getRouteIndex(Route route) {
		int routeIndex = -1;
		for (int i = 0; i < availableRoutes.size(); i++) {
			if (route.equals(availableRoutes.get(i))) {
				routeIndex = i;
			}
		}
		return routeIndex;
	}
	
	public Vector<String> getRouteList() {
		Vector<String> routeList = new Vector<String>();
		
		for (int i = 0; i < availableRoutes.size(); i++) {
			routeList.addElement( availableRoutes.get(i).getRouteName() + "  -  " +
					Double.toString(availableRoutes.get(i).getRouteDistance() / 100 ) + " Mi");	
		}
		
		return routeList;
	}
}
