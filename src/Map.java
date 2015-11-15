import java.util.*;

public class Map {

	private ArrayList<Route> availableRoutes;
	private Route currentRoute;
	Double[] routeLengths = new Double[] {100.0, 200.0, 500.0, 1000.0, 20000.0, 100000.0};

	public Map() {
		availableRoutes = new ArrayList<Route>();
		setupRoutes();
	}

	public void setupRoutes() {
		for (int i = 0; i < routeLengths.length; i++) {
			availableRoutes.add(new Route("Test Route " + Integer.toString(i + 1), routeLengths[i]));
		}
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
			if (route.equals(availableRoutes.get(i))) routeIndex = i;
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
