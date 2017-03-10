import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
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
				super.setBackground(new Color(254, 234, 44));
				drawRadarChart(g);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
			}
		};

		return panel;
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

	private void drawRadarChart(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double diameter = 175;
		double x = (PANEL_WIDTH / 2);
		double y = (PANEL_HEIGHT / 2);

		Point2D circumcenter = findCenter(x, y, diameter);
		List<Point> polygonVertices = createPolygonVertices(x, y, diameter);
		List<Ellipse2D> labels = createLabels(circumcenter, polygonVertices);
		List<Line2D> spokes = createSpokes(circumcenter, polygonVertices);

		List<List<Point2D>> pips = new ArrayList<List<Point2D>>();
		List<Point2D> knowledgePips = createPips(circumcenter, spokes.get(0));
		pips.add(knowledgePips);
		List<Point2D> couragePips = createPips(circumcenter, spokes.get(1));
		pips.add(couragePips);
		List<Point2D> diligencePips = createPips(circumcenter, spokes.get(2));
		pips.add(diligencePips);
		List<Point2D> understandingPips = createPips(circumcenter, spokes.get(3));
		pips.add(understandingPips);
		List<Point2D> expressionPips = createPips(circumcenter, spokes.get(4));
		pips.add(expressionPips);

		drawCircularLabels(g2, labels);
		drawPolygonFrameWithBackground(g2, polygonVertices);
		drawStatisticPolygon(g2, pips);
		drawSpokes(g2, spokes);
		drawPips(g2, pips);

	}

	private void drawStatisticPolygon(Graphics2D g2, List<List<Point2D>> pips) {
		g2.setColor(new Color(255, 174, 32));
		Polygon statisticsPolygon = new Polygon();
		List<Point2D> knowledgePips = pips.get(0);
		List<Point2D> couragePips = pips.get(1);
		List<Point2D> diligencePips = pips.get(2);
		List<Point2D> understandingPips = pips.get(3);
		List<Point2D> expressionPips = pips.get(4);

		Point2D knowledgeCoordinates = knowledgePips.get(knowledgeLvl - 1);
		Point2D courageCoordinates = couragePips.get(courageLvl - 1);
		Point2D diligenceCoordinates = diligencePips.get(diligenceLvl - 1);
		Point2D understandingCoordinates = understandingPips.get(understandingLvl - 1);
		Point2D expressionCoordinates = expressionPips.get(expressionLvl - 1);

		statisticsPolygon.addPoint((int) knowledgeCoordinates.getX(), (int) knowledgeCoordinates.getY());
		statisticsPolygon.addPoint((int) courageCoordinates.getX(), (int) courageCoordinates.getY());
		statisticsPolygon.addPoint((int) diligenceCoordinates.getX(), (int) diligenceCoordinates.getY());
		statisticsPolygon.addPoint((int) understandingCoordinates.getX(), (int) understandingCoordinates.getY());
		statisticsPolygon.addPoint((int) expressionCoordinates.getX(), (int) expressionCoordinates.getY());
		g2.fillPolygon(statisticsPolygon);
	}

	private Point2D findCenter(double x, double y, double diameter) {
		double radius = diameter / 2;
		Ellipse2D circumscribedCircle = new Ellipse2D.Double((PANEL_WIDTH / 2) - radius, (PANEL_HEIGHT / 2) - radius,
				diameter, diameter);
		Point2D circumcenter = new Point2D.Double(circumscribedCircle.getCenterX(), circumscribedCircle.getCenterY());
		return circumcenter;
	}

	private List<Point> createPolygonVertices(double x, double y, double diameter) {
		int numberOfSides = 5;
		double radius = diameter / 2;
		double theta = 2 * Math.PI / numberOfSides;
		double rotation = Math.toRadians(198);
		List<Point> polygonVertices = new ArrayList<Point>();
		for (int i = 0; i < numberOfSides; i++) {
			int vertexX = (int) Math.round((x + radius * Math.cos((i * theta) + rotation)));
			int vertexY = (int) Math.round((y + radius * Math.sin((i * theta) + rotation)));

			polygonVertices.add(new Point(vertexX, vertexY));
		}
		return polygonVertices;
	}

	private List<Ellipse2D> createLabels(Point2D circumcenter, List<Point> polygonVertices) {
		double diameter = 90;
		double radius = diameter / 2;
		List<Ellipse2D> labels = new ArrayList<Ellipse2D>();

		for (Point2D vertex : polygonVertices) {
			double xOffset;
			if (vertex.getX() < circumcenter.getX()) {
				xOffset = (diameter / 5);
			} else if (vertex.getX() > circumcenter.getX()) {
				xOffset = -(diameter / 5);
			} else {
				xOffset = 0;
			}

			double yOffset;
			if (vertex.getY() < circumcenter.getY()) {
				yOffset = (diameter / 5);
			} else if (vertex.getY() > circumcenter.getY()) {
				yOffset = -(diameter / 5);
			} else {
				yOffset = 0;
			}

			double x = (vertex.getX() - radius) - xOffset;
			double y = (vertex.getY() - radius) - yOffset;
			double width = diameter;
			double height = diameter;
			labels.add(new Ellipse2D.Double(x, y, width, height));
		}
		return labels;
	}

	private List<Line2D> createSpokes(Point2D circumcenter, List<Point> polygonVertices) {
		List<Line2D> spokes = new ArrayList<Line2D>();
		for (Point p : polygonVertices) {
			spokes.add(new Line2D.Double(p, circumcenter));
		}
		return spokes;
	}

	private List<Point2D> createPips(Point2D circumcenter, Line2D spoke) {
		List<Point2D> points = new ArrayList<Point2D>();
		Point2D outerPoint = new Point2D.Double();
		if (!spoke.getP1().equals(circumcenter)) {
			outerPoint = spoke.getP1();
		} else if (!spoke.getP2().equals(circumcenter)) {
			outerPoint = spoke.getP2();
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
			points.add(new Point(x2, y2));
		}
		return points;
	}

	private void drawCircularLabels(Graphics2D g2, List<Ellipse2D> labels) {
		for (int i = 0; i < labels.size(); i++) {
			String title = "";
			String subtitle = "";
			Ellipse2D e = labels.get(i);

			g2.setColor(new Color(255, 174, 32));
			g2.fill(labels.get(i));

			switch (i) {
			case 0:
				title = "Knowledge";
				switch (knowledgeLvl) {
				case 1:
					subtitle = "Aware";
					break;
				case 2:
					subtitle = "Informed";
					break;
				case 3:
					subtitle = "Expert";
					break;
				case 4:
					subtitle = "Professor";
					break;
				case 5:
					subtitle = "Sage";
					break;
				default:
					break;
				}
				break;
			case 1:
				title = "Courage";
				switch (courageLvl) {
				case 1:
					subtitle = "Average";
					break;
				case 2:
					subtitle = "Reliable";
					break;
				case 3:
					subtitle = "Brave";
					break;
				case 4:
					subtitle = "Daring";
					break;
				case 5:
					subtitle = "Heroic";
					break;
				default:
					break;
				}
				break;
			case 2:
				title = "Diligence";
				switch (diligenceLvl) {
				case 1:
					subtitle = "Callow";
					break;
				case 2:
					subtitle = "Persistent";
					break;
				case 3:
					subtitle = "Strong";
					break;
				case 4:
					subtitle = "Persuasive";
					break;
				case 5:
					subtitle = "Rock Solid";
					break;
				default:
					break;
				}
				break;
			case 3:
				title = "Understanding";
				switch (understandingLvl) {
				case 1:
					subtitle = "Basic";
					break;
				case 2:
					subtitle = "Kind";
					break;
				case 3:
					subtitle = "Generous";
					break;
				case 4:
					subtitle = "Motherly";
					break;
				case 5:
					subtitle = "Saintly";
					break;
				default:
					break;
				}
				break;
			case 4:
				title = "Expression";
				switch (expressionLvl) {
				case 1:
					subtitle = "Rough";
					break;
				case 2:
					subtitle = "Eloquent";
					break;
				case 3:
					subtitle = "Persuasice";
					break;
				case 4:
					subtitle = "Touching";
					break;
				case 5:
					subtitle = "Entrhralling";
					break;
				default:
					break;
				}

				break;
			default:
				break;
			}
			drawCenteredTitle(g2, title, e);
			drawCenteredSubtitle(g2, subtitle, e);
		}
	}

	private void drawPolygonFrameWithBackground(Graphics2D g2, List<Point> polygonVertices) {
		g2.setColor(Color.BLACK);
		Polygon chartFrame = new Polygon();
		for (Point p : polygonVertices) {
			chartFrame.addPoint((int) p.getX(), (int) p.getY());
		}
		g2.drawPolygon(chartFrame);

		g2.setColor(new Color(255, 255, 255, 90));
		g2.fillPolygon(chartFrame);
	}

	private void drawSpokes(Graphics2D g2, List<Line2D> spokes) {
		g2.setColor(Color.BLACK);
		for (Line2D l2d : spokes) {
			g2.draw(l2d);
		}
	}

	private void drawPips(Graphics2D g2, List<List<Point2D>> pips) {
		g2.setColor(Color.BLACK);
		for (List<Point2D> pts : pips) {
			int pipDiameter = 6;
			int pipRadius = (int) Math.round(pipDiameter / 2);
			for (Point2D p : pts) {
				g2.fillOval((int) p.getX() - pipRadius, (int) p.getY() - pipRadius, pipDiameter, pipDiameter);
			}
		}
	}

	private void drawCenteredTitle(Graphics2D g2, String title, Ellipse2D e) {
		g2.setColor(Color.BLACK);
		Font font = new Font("Arial", Font.BOLD, 12);
		FontMetrics metrics = g2.getFontMetrics(font);
		int x = (int) Math.round(e.getCenterX() - metrics.stringWidth(title) / 2);
		int y = (int) e.getCenterY() - (metrics.getHeight() / 3);

		g2.setFont(font);
		g2.drawString(title, x, y);
	}

	private void drawCenteredSubtitle(Graphics2D g2, String subtitle, Ellipse2D e) {
		g2.setColor(Color.WHITE);
		Font font = new Font("Arial", Font.PLAIN, 11);
		FontMetrics metrics = g2.getFontMetrics(font);
		int x = (int) Math.round(e.getCenterX() - metrics.stringWidth(subtitle) / 2);
		int y = (int) e.getCenterY() + (metrics.getHeight() / 2);

		g2.setFont(font);
		g2.drawString(subtitle, x, y);
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
