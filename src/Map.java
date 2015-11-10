import java.util.*;

// #TODO : Routes less than 1 mile make error since upper bound for slider map must be at least 1.
/* #TODO : The GUI slider and all fields associated with it in map and Route need to be changed to "tenths of mile"
 * 			units since the slider only uses INT values. This should help alleviate TODO #1 issue. 
 */
		
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
		availableRoutes.add(new Route("Test Route 4", 20.0));
		availableRoutes.add(new Route("Test Route 5", 67.5));
		availableRoutes.add(new Route("Test Route 6", 1));
		currentRoute = availableRoutes.get(0);
	}
	
	public Route getCurrentRoute() {
		return currentRoute;
	}
	
	public void setCurrentRoute(int routeIndex) {
		currentRoute = availableRoutes.get(routeIndex);
	}
	
	public Vector<String> getRouteList() {
		Vector<String> routeList = new Vector<String>();
		
		for (int i = 0; i < availableRoutes.size(); i++) {
			routeList.addElement( availableRoutes.get(i).getRouteName() + "  -  " +
					Double.toString(availableRoutes.get(i).getRouteDistance()) + " Mi");	
		}
		
		return routeList;
	}
}
