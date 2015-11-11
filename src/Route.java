public class Route {

	private double routeDistance;
	private String routeName;
	private double distanceIntoRoute;

	public Route(String name, double distance) {
		routeName = name;
		routeDistance = distance;
	}

	public String getRouteName() {
		return routeName;
	}

	public double getRouteDistance() {
		return routeDistance;
	}

	public void setRouteDistance(double distance) {
		routeDistance = distance;
	}

	public double getDistanceIntoRoute() {
		return distanceIntoRoute;
	}

	public double incrementDistanceIntoRoute(double increment) {
		distanceIntoRoute += increment;
		if (distanceIntoRoute > routeDistance) distanceIntoRoute = routeDistance;
		return distanceIntoRoute;
	}

	public String toString() {
		return routeName;
	}
}
