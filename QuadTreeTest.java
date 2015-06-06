import java.awt.Point;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuadTreeTest {
	private static final int NUM_POINTS = 200;

	@Test
	public void testRandom() {
		QuadTree qTree = new QuadTree();

		final Set<Point> points = generatePoints();
		for (Point point : points) {
			qTree.put(point);
			assertTrue(qTree.contains(point));
		}

		for (Point point : points) {
			assertTrue(qTree.contains(point));
		}
	}

	private Set<Point> generatePoints() {
		Set<Point> points = new HashSet<Point>();
		Random r = new Random();
		for (int i = 0 ; i < NUM_POINTS ; i++) {
			int x = r.nextInt();
			int y = r.nextInt();
			points.add(new Point(x, y));
		}
		return points;
	}
}