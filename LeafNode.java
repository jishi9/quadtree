import java.awt.Point;
import java.util.Set;
import java.util.HashSet;

public class LeafNode implements Node {
	private static final int MAX_POINTS = 16;
	private final Set<Point> points = new HashSet<Point>();

	public boolean contains(Point point) {
		return points.contains(point);
	}

	public Node put(Point point) {
		points.add(point);
		if (points.size() <= MAX_POINTS) {
			return this;
		} else {
			return new QuadNode(points);
		}
	}
}