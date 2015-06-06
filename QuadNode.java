import java.awt.Point;
import java.util.Collection;

public class QuadNode implements Node {

	private final int xBoundary;
	private final int yBoundary;

	private Node geXgeY = new LeafNode();
	private Node geXltY = new LeafNode();
	private Node ltXgeY = new LeafNode();
	private Node ltXltY = new LeafNode();

	public QuadNode(Collection<Point> points) {
		assert (!points.isEmpty());
		Median medians = calculateMedians(points);
		xBoundary = (int) medians.getX();
		yBoundary = (int) medians.getY();

		// TODO check that children are distributed!
		for (Point point : points) {
			put(point);
		}
	}

	public boolean contains(Point point) {
		return getNodeFor(point).contains(point);
	}

	public Node put(Point point) {
		Child child = getChildFor(point);
		Node newNode = getNodeFor(child).put(point);
		setNodeFor(child, newNode);
		return this;
	}

	private Child getChildFor(Point point) {
		boolean geX = point.getX() >= xBoundary;
		boolean geY = point.getY() >= yBoundary;

		if (geX) {
			return geY ? Child.GEX_GEY : Child.GEX_LTY;
		} else {
			return geY ? Child.LTX_GEY : Child.LTX_LTY;
		}
	}

	private Node getNodeFor(Child child) {
		switch (child) {
			case GEX_GEY:
				return geXgeY;
			case GEX_LTY:
				return geXltY;
			case LTX_GEY:
				return ltXgeY;
			case LTX_LTY:
				return ltXltY;
		}
		throw new IllegalStateException("Unhandled case");
	}

	private Node getNodeFor(Point point) {
		return getNodeFor(getChildFor(point));
	}

	private void setNodeFor(Child child, Node newNode) {
		switch (child) {
			case GEX_GEY:
				geXgeY = newNode;
				return;
			case GEX_LTY:
				geXltY = newNode;
				return;
			case LTX_GEY:
				ltXgeY = newNode;
				return;
			case LTX_LTY:
				ltXltY = newNode;
				return;
		}
		throw new IllegalStateException("Unhandled case: " + child);
	}

	private static Median calculateMedians(Collection<Point> points) {
		// TODO change to median
		double sumX = 0;
		double sumY = 0;
		for (Point point : points) {
			sumX += point.getX();
			sumY += point.getY();
		}
		int meanX = (int) (sumX / points.size());
		int meanY = (int) (sumY / points.size());
		return new Median(meanX, meanY);
	}

	private static class Median extends Point {
		public Median(int x, int y) {
			super(x, y);
		}
	}

	private static enum Child {
		GEX_GEY, GEX_LTY, LTX_GEY, LTX_LTY;
	}

}