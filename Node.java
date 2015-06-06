import java.awt.Point;

public interface Node {
	boolean contains(Point point);
	Node put(Point point);
}