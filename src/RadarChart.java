import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class RadarChart extends JPanel {
	private static final int PANEL_WIDTH = 300;
	private static final int PANEL_HEIGHT = 300;
	private static final int MAX_LEVEL = 5;

	private int courageLvl, diligenceLvl, understandingLvl, expressionLvl, knowledgeLvl;
	private Point[] courageCoord = new Point[MAX_LEVEL];
	private Point[] diligenceCoord = new Point[MAX_LEVEL];
	private Point[] understandingCoord = new Point[MAX_LEVEL];
	private Point[] expressionCoord = new Point[MAX_LEVEL];
	private Point[] knowledgeCoord = new Point[MAX_LEVEL];

	public RadarChart(int courageLvl, int diligenceLvl, int understandingLvl, int expressionLvl, int knowledgeLvl) {
		this.courageLvl = courageLvl;
		this.diligenceLvl = diligenceLvl;
		this.understandingLvl = understandingLvl;
		this.expressionLvl = expressionLvl;
		this.knowledgeLvl = knowledgeLvl;
	}

	public JPanel initComponents() {
		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				super.setBackground(Color.RED); // Set to red to help view panel
												// bounds
				drawRadarChart(g);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
			}
		};

		return panel;
	}

	public void setCourage(int level) {
		this.courageLvl = level;
	}

	public void setdiligence(int level) {
		this.diligenceLvl = level;
	}

	public void setUnderstanding(int level) {
		this.understandingLvl = level;
	}

	public void setExpression(int level) {
		this.expressionLvl = level;
	}

	public void setKnowledge(int level) {
		this.knowledgeLvl = level;
	}

	/*
	 * Hard coded values to show that repainting the polygon works Will change
	 * so that the values are updated dynamically using getters and setters
	 */
	public void updateParameters() {
		this.courageLvl = 4;
		this.diligenceLvl = 2;
		this.understandingLvl = 3;
		this.expressionLvl = 5;
		this.knowledgeLvl = 3;
	}

	/**
	 * Draws the radar chart. This includes the frame, spokes from the center to
	 * a vertex, and pips on the spokes
	 * 
	 * @param g
	 *            - the graphics object to help draw the frame.
	 */
	private void drawRadarChart(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double diameter = 175;
		double radius = diameter / 2;

		int numberOfSides = 5;
		double theta = 2 * Math.PI / numberOfSides;
		double rotation = Math.toRadians(198);
		Polygon chartFrame = new Polygon();
		List<Point> polygonVertices = new ArrayList<Point>();
		for (int i = 0; i < numberOfSides; i++) {
			int x = (int) Math.round(((PANEL_WIDTH / 2) + radius * Math.cos((i * theta) + rotation)));
			int y = (int) Math.round(((PANEL_HEIGHT / 2) + radius * Math.sin((i * theta) + rotation)));

			polygonVertices.add(new Point(x, y));
			chartFrame.addPoint(x, y);
		}
		g2.setColor(new Color(255, 255, 255, 100));
		g2.fillPolygon(chartFrame);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(chartFrame);

		drawSpokes(g2, diameter, polygonVertices);
	}

	/**
	 * Draws the lines from the center of the circumscribed circle to the
	 * vertices of the inscribed polygon
	 * 
	 * @param g2
	 *            - the graphics object to help draw the object.
	 * @param diameter
	 *            - the width of the circumscribed circle.
	 * @param polygonVertices
	 *            - a list of all the vertex points used to form the polygon.
	 */
	private void drawSpokes(Graphics2D g2, double diameter, List<Point> polygonVertices) {
		double radius = diameter / 2;
		Ellipse2D circumscribedCircle = new Ellipse2D.Double((PANEL_WIDTH / 2) - radius, (PANEL_HEIGHT / 2) - radius,
				diameter, diameter);
		Point2D circumcenter = new Point2D.Double(circumscribedCircle.getCenterX(), circumscribedCircle.getCenterY());
		List<Line2D> spokes = new ArrayList<Line2D>();

		g2.setColor(Color.BLACK);
		for (Point p : polygonVertices) {
			spokes.add(new Line2D.Double(p, circumcenter));
			g2.draw(new Line2D.Double(p, circumcenter));
		}

		drawPips(g2, circumcenter, spokes);
	}

	/**
	 * Draws dots/pips onto the spokes within the polygon.
	 * 
	 * @param g2
	 *            - the graphics object to help draw the object.
	 * @param circumcenter
	 *            - the center of the circumscribed circle.
	 * @param spokes
	 *            - a list of lines from the center of the polygon to the
	 *            vertices.
	 */
	private void drawPips(Graphics2D g2, Point2D circumcenter, List<Line2D> spokes) {
		int pipDiameter = 6;
		int pipRadius = (int) Math.round(pipDiameter / 2);
		for (int i = 0; i < spokes.size(); i++) {
			Point2D outerPoint = new Point2D.Double();
			if (!spokes.get(i).getP1().equals(circumcenter)) {
				outerPoint = spokes.get(i).getP1();
			} else if (!spokes.get(i).getP2().equals(circumcenter)) {
				outerPoint = spokes.get(i).getP2();
			}
			int x1 = (int) outerPoint.getX();
			int y1 = (int) outerPoint.getY();
			for (int j = 1; j <= MAX_LEVEL; j++) {
				int x2 = x1 <= circumcenter.getX()
						? (int) (circumcenter.getX() - j * Math.abs(x1 - circumcenter.getX()) / MAX_LEVEL)
						: (int) (circumcenter.getX() + j * Math.abs(x1 - circumcenter.getX()) / MAX_LEVEL);

				int y2 = y1 <= circumcenter.getY()
						? y2 = (int) (circumcenter.getY() - j * Math.abs(y1 - circumcenter.getY()) / MAX_LEVEL)
						: (int) (circumcenter.getY() + j * Math.abs(y1 - circumcenter.getY()) / MAX_LEVEL);
				switch (i) {
				case 0:
					knowledgeCoord[j - 1] = new Point(x2, y2);
					break;
				case 1:
					courageCoord[j - 1] = new Point(x2, y2);
					break;
				case 2:
					diligenceCoord[j - 1] = new Point(x2, y2);
					break;
				case 3:
					understandingCoord[j - 1] = new Point(x2, y2);
					break;
				case 4:
					expressionCoord[j - 1] = new Point(x2, y2);
					break;
				default:
					break;
				}
				g2.fillOval(x2 - pipRadius, y2 - pipRadius, pipDiameter, pipDiameter);
			}
		}

		drawStatParameters(g2);
	}

	/**
	 * Draws a semi-transparent polygon that visually represents characters
	 * social quality levels.
	 * 
	 * @param g
	 *            - the graphics object to help draw the object.
	 */
	private void drawStatParameters(Graphics g) {
		Polygon parameterPolygon = new Polygon();

		g.setColor(new Color(255, 174, 32, 200));

		parameterPolygon.addPoint(knowledgeCoord[knowledgeLvl - 1].x, knowledgeCoord[knowledgeLvl - 1].y);
		parameterPolygon.addPoint(courageCoord[courageLvl - 1].x, courageCoord[courageLvl - 1].y);
		parameterPolygon.addPoint(diligenceCoord[diligenceLvl - 1].x, diligenceCoord[diligenceLvl - 1].y);
		parameterPolygon.addPoint(understandingCoord[understandingLvl - 1].x,
				understandingCoord[understandingLvl - 1].y);
		parameterPolygon.addPoint(expressionCoord[expressionLvl - 1].x, expressionCoord[expressionLvl - 1].y);

		g.fillPolygon(parameterPolygon);
	}

	public static class RadarChartBuilder {

		private int courageLvl, diligenceLvl, understandingLvl, expressionLvl, knowledgeLvl;

		public RadarChartBuilder withCourageLevelOf(int courageLvl) {
			this.courageLvl = courageLvl;
			return this;
		}

		public RadarChartBuilder withDiligenceLevelOf(int diligenceLvl) {
			this.diligenceLvl = diligenceLvl;
			return this;
		}

		public RadarChartBuilder withUnderstandingLevelOf(int understandingLvl) {
			this.understandingLvl = understandingLvl;
			return this;
		}

		public RadarChartBuilder withExpressionLevelOf(int expressionLvl) {
			this.expressionLvl = expressionLvl;
			return this;
		}

		public RadarChartBuilder withKnowledgeLevelOf(int knowledgeLvl) {
			this.knowledgeLvl = knowledgeLvl;
			return this;
		}

		public RadarChart createRadarChart() {
			return new RadarChart(courageLvl, diligenceLvl, understandingLvl, expressionLvl, knowledgeLvl);
		}

	}
}
