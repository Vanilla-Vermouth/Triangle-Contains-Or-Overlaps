import java.awt.geom.Line2D;

/**
 * Created by Zechen Wang on 2015/5/19.
 */
public class Triangle2D {
	private MyPoint p1, p2, p3;
	
	Triangle2D() {
		this.p1 = new MyPoint(0, 0);
		this.p2 = new MyPoint(1, 1);
		this.p3 = new MyPoint(2, 5);
//		  System.out.println(p1 + " " + p1.getX() + " " + p1.getY());
	}
	
	Triangle2D(double x1, double y1, double x2, double y2, double x3, double y3) {
//		  this();
		this.p1 = new MyPoint(x1, y1);
		this.p2 = new MyPoint(x2, y2);
		this.p3 = new MyPoint(x3, y3);
//		  System.out.println(p1 + " " + p1.getX() + " " + p1.getY());
	}
	
	void setP1(MyPoint p1) {
		this.p1 = p1;
	}
	
	MyPoint getP1() {
		return p1;
	}
	
	void setP2(MyPoint p2) {
		this.p2 = p2;
	}
	
	MyPoint getP2() {
		return p2;
	}
	
	void setP3(MyPoint p3) {
		this.p3 = p3;
	}
	
	MyPoint getP3() {
		return p3;
	}
	
	double getArea() {
		double side1 = p1.distance(p2);
//		  System.out.println(p1 + " " + p1.getX() + " " + p1.getY());
		double side2 = p1.distance(p3);
//		  System.out.println(p2 + " " + p2.getX() + " " + p2.getY());
		double side3 = p2.distance(p3);
//		  System.out.println(p3 + " " + p3.getX() + " " + p3.getY());
		
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}
	
	double getPerimeter() {
//		  System.out.println(p1 + " " + p1.getX() + " " + p1.getY());
		return p1.distance(p2) + p1.distance(p3) + p2.distance(p3);
	}
	
	double getArea(MyPoint PX, MyPoint PY, MyPoint PZ) {
		double side1 = PX.distance(PY);
//		  System.out.println(PX + " " + PX.getX() + " " + PX.getY());
		double side2 = PX.distance(PZ);
//		  System.out.println(PY + " " + PY.getX() + " " + PY.getY());
		double side3 = PY.distance(PZ);
//		  System.out.println(PZ + " " + PZ.getX() + " " + PZ.getY());
		
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}
	
	boolean contains(double x, double y) {
		MyPoint p = new MyPoint(x, y);
		double Area = getArea(p, p1, p2) + getArea(p, p1, p3) + getArea(p, p2, p3);
		return Area + 0.000000001 >= this.getArea() && Area - 0.000000001 <= this.getArea();
	}
	
	public boolean contains(Triangle2D t) {
		return contains(t.getP1().getX(), t.getP1().getY()) && contains(t.getP2().getX(), t.getP2().getY()) &&
				contains(t.getP3().getX(), t.getP3().getY());
	}
	
	public boolean overlaps(Triangle2D t) {
		if (this.contains(t) || t.contains(this)) {
			return true;
		}
		
		return overlaps(p1.getX(), p1.getY(), p2.getX(), p2.getY(), t.p1.getX(), t.p1.getY(), t.p2.getX(), t.p2.getY())
				|| overlaps(p1.getX(), p1.getY(), p2.getX(), p2.getY(), t.p1.getX(), t.p1.getY(), t.p3.getX(), t.p3.getY())
				|| overlaps(p1.getX(), p1.getY(), p2.getX(), p2.getY(), t.p2.getX(), t.p2.getY(), t.p3.getX(), t.p3.getY())
				
				|| overlaps(p1.getX(), p1.getY(), p3.getX(), p3.getY(), t.p1.getX(), t.p1.getY(), t.p2.getX(), t.p2.getY())
				|| overlaps(p1.getX(), p1.getY(), p3.getX(), p3.getY(), t.p1.getX(), t.p1.getY(), t.p3.getX(), t.p3.getY())
				|| overlaps(p1.getX(), p1.getY(), p3.getX(), p3.getY(), t.p2.getX(), t.p2.getY(), t.p3.getX(), t.p3.getY())
				
				|| overlaps(p2.getX(), p2.getY(), p3.getX(), p3.getY(), t.p1.getX(), t.p1.getY(), t.p2.getX(), t.p2.getY())
				|| overlaps(p2.getX(), p2.getY(), p3.getX(), p3.getY(), t.p1.getX(), t.p1.getY(), t.p3.getX(), t.p3.getY())
				|| overlaps(p2.getX(), p2.getY(), p3.getX(), p3.getY(), t.p2.getX(), t.p2.getY(), t.p3.getX(), t.p3.getY())
				;
	}
	
	private boolean overlaps(double px1, double py1, double px2, double py2, double tx1, double ty1, double tx2, double ty2) {
		// make sure px1 <= px2, tx1 <= tx2
		if (px1 > px2) {
			double tmp_x = px1;
			double tmp_y = py1;
			px1 = px2;
			py1 = py2;
			px2 = tmp_x;
			py2 = tmp_y;
		}
		if (tx1 > tx2) {
			double tmp_x = tx1;
			double tmp_y = ty1;
			tx1 = tx2;
			ty1 = ty2;
			tx2 = tmp_x;
			ty2 = tmp_y;
		}
		Line2D line1 = new Line2D.Double(px1, py1, px2, py2);
		Line2D line2 = new Line2D.Double(tx1, ty1, tx2, ty2);
		if (line1.intersectsLine(line2)) {
			if ((py2 - py1) * (tx2 - tx1) == (ty2 - ty1) * (px2 - px1)) { // same slope
				return false;
			} else {
				return (ty2 - ty1) * (tx2 - px1) != (ty2 - py1) * (tx2 - tx1) && (ty2 - ty1) * (tx2 - px2) != (ty2 - py2) * (tx2 - tx1)
						&& (py2 - py1) * (px2 - tx1) != (py2 - ty1) * (px2 - px1) && (py2 - py1) * (px2 - tx2) != (py2 - ty2) * (px2 - px1);
			}
		}
		return false;
	}
}
