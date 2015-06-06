import java.awt.Point;

public class QuadTree {
	private Node root = new LeafNode();

	public boolean contains(Point point) {
		return root.contains(point);
	}

	public void put(Point point) {
		root = root.put(point);
	}
}