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
	private static final int MAX_LEVEL = 5;
	private static final Color ORANGE = new Color(255, 158, 7);

	private int field2Level, field3Level, field4Level, field5Level, field1Level;
	private int width, height;

	public RadarChart(int width, int height, int field2Level, int field3Level, int field4Level, int field5Level,
			int field1Level) {
		this.width = width;
		this.height = height;
		this.field2Level = field2Level;
		this.field3Level = field3Level;
		this.field4Level = field4Level;
		this.field5Level = field5Level;
		this.field1Level = field1Level;
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
				return new Dimension(width, height);
			}
		};

		return panel;
	}

	/*
	 * Hard coded values to show that repainting the polygon works Will change
	 * so that the values are updated dynamically using getters and setters
	 */
	public void updateParameters() {
		this.field2Level = 4;
		this.field3Level = 2;
		this.field4Level = 3;
		this.field5Level = 5;
		this.field1Level = 3;
	}

	private void drawRadarChart(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double diameter = 175;
		double x = (width / 2);
		double y = (height / 2);

		Point2D circumcenter = findCenter(x, y, diameter);
		List<Point> polygonVertices = createPolygonVertices(x, y, diameter);
		List<Ellipse2D> labels = createLabels(circumcenter, polygonVertices);
		List<Line2D> spokes = createSpokes(circumcenter, polygonVertices);

		List<List<Point2D>> pips = new ArrayList<List<Point2D>>();
		List<Point2D> field1Pips = createPips(circumcenter, spokes.get(0));
		pips.add(field1Pips);
		List<Point2D> field2Pips = createPips(circumcenter, spokes.get(1));
		pips.add(field2Pips);
		List<Point2D> field3Pips = createPips(circumcenter, spokes.get(2));
		pips.add(field3Pips);
		List<Point2D> field4Pips = createPips(circumcenter, spokes.get(3));
		pips.add(field4Pips);
		List<Point2D> field5Pips = createPips(circumcenter, spokes.get(4));
		pips.add(field5Pips);

		drawCircularLabels(g2, labels);
		drawPolygonFrameWithBackground(g2, polygonVertices);
		drawStatisticPolygon(g2, pips);
		drawSpokes(g2, spokes);
		drawPips(g2, pips);

	}

	private void drawStatisticPolygon(Graphics2D g2, List<List<Point2D>> pips) {
		g2.setColor(ORANGE);
		Polygon statisticsPolygon = new Polygon();
		List<Point2D> field1Pips = pips.get(0);
		List<Point2D> field2Pips = pips.get(1);
		List<Point2D> field3Pips = pips.get(2);
		List<Point2D> field4Pips = pips.get(3);
		List<Point2D> field5Pips = pips.get(4);

		Point2D field1Coordinates = field1Pips.get(field1Level - 1);
		Point2D field2Coordinates = field2Pips.get(field2Level - 1);
		Point2D field3Coordinates = field3Pips.get(field3Level - 1);
		Point2D field4Coordinates = field4Pips.get(field4Level - 1);
		Point2D field5Coordinates = field5Pips.get(field5Level - 1);

		statisticsPolygon.addPoint((int) field1Coordinates.getX(), (int) field1Coordinates.getY());
		statisticsPolygon.addPoint((int) field2Coordinates.getX(), (int) field2Coordinates.getY());
		statisticsPolygon.addPoint((int) field3Coordinates.getX(), (int) field3Coordinates.getY());
		statisticsPolygon.addPoint((int) field4Coordinates.getX(), (int) field4Coordinates.getY());
		statisticsPolygon.addPoint((int) field5Coordinates.getX(), (int) field5Coordinates.getY());
		g2.fillPolygon(statisticsPolygon);
	}

	private Point2D findCenter(double x, double y, double diameter) {
		double radius = diameter / 2;
		Ellipse2D circumscribedCircle = new Ellipse2D.Double((width / 2) - radius, (height / 2) - radius, diameter,
				diameter);
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
		String fieldTitle1 = "Knowledge";
		String fieldTitle2 = "Courage";
		String fieldTitle3 = "Diligence";
		String fieldTitle4 = "Understanding";
		String fieldTitle5 = "Expression";

		String[] titleSubtitles1 = { "Aware", "Informed", "Expert", "Professor", "Sage" };
		String[] titleSubtitles2 = { "Average", "Reliable", "Brave", "Daring", "Heroic" };
		String[] titleSubtitles3 = { "Callow", "Persistant", "Strong", "Persuasive", "Rock Solid" };
		String[] titleSubtitles4 = { "Basic", "Kindly", "Generous", "Motherly", "Saintly" };
		String[] titleSubtitles5 = { "Rough", "Eloquent", "Persuasive", "Touching", "Enthralling" };

		for (int i = 0; i < labels.size(); i++) {
			String title = "";
			String subtitle = "";
			Ellipse2D e = labels.get(i);

			g2.setColor(ORANGE);
			g2.fill(labels.get(i));

			switch (i + 1) {
			case 1:
				title = fieldTitle1;
				switch (field1Level) {
				case 1:
					subtitle = titleSubtitles1[0];
					break;
				case 2:
					subtitle = titleSubtitles1[1];
					break;
				case 3:
					subtitle = titleSubtitles1[2];
					break;
				case 4:
					subtitle = titleSubtitles1[3];
					break;
				case 5:
					subtitle = titleSubtitles1[4];
					break;
				default:
					break;
				}
				break;
			case 2:
				title = fieldTitle2;
				switch (field2Level) {
				case 1:
					subtitle = titleSubtitles2[0];
					break;
				case 2:
					subtitle = titleSubtitles2[1];
					break;
				case 3:
					subtitle = titleSubtitles2[2];
					break;
				case 4:
					subtitle = titleSubtitles2[3];
					break;
				case 5:
					subtitle = titleSubtitles2[4];
					break;
				default:
					break;
				}
				break;
			case 3:
				title = fieldTitle3;
				switch (field3Level) {
				case 1:
					subtitle = titleSubtitles3[0];
					break;
				case 2:
					subtitle = titleSubtitles3[1];
					break;
				case 3:
					subtitle = titleSubtitles3[2];
					break;
				case 4:
					subtitle = titleSubtitles3[3];
					break;
				case 5:
					subtitle = titleSubtitles3[4];
					break;
				default:
					break;
				}
				break;
			case 4:
				title = fieldTitle4;
				switch (field4Level) {
				case 1:
					subtitle = titleSubtitles4[0];
					break;
				case 2:
					subtitle = titleSubtitles4[1];
					break;
				case 3:
					subtitle = titleSubtitles4[2];
					break;
				case 4:
					subtitle = titleSubtitles4[3];
					break;
				case 5:
					subtitle = titleSubtitles4[4];
					break;
				default:
					break;
				}
				break;
			case 5:
				title = fieldTitle5;
				switch (field5Level) {
				case 1:
					subtitle = titleSubtitles5[0];
					break;
				case 2:
					subtitle = titleSubtitles5[1];
					break;
				case 3:
					subtitle = titleSubtitles5[2];
					break;
				case 4:
					subtitle = titleSubtitles5[3];
					break;
				case 5:
					subtitle = titleSubtitles5[4];
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

	public static class Builder {
		private int field2Level, field3Level, field4Level, field5Level, field1Level;
		private int width, height;

		public Builder withField2LevelOf(int field2Level) {
			this.field2Level = field2Level;
			return this;
		}

		public Builder withWidth(int width) {
			this.width = width;
			return this;
		}

		public Builder withHeight(int height) {
			this.height = height;
			return this;
		}

		public Builder withField3LevelOf(int field3Level) {
			this.field3Level = field3Level;
			return this;
		}

		public Builder withField4LevelOf(int field4Level) {
			this.field4Level = field4Level;
			return this;
		}

		public Builder withField5LevelOf(int field5Level) {
			this.field5Level = field5Level;
			return this;
		}

		public Builder withField1LevelOf(int field1Level) {
			this.field1Level = field1Level;
			return this;
		}

		public RadarChart createRadarChart() {
			return new RadarChart(width, height, field2Level, field3Level, field4Level, field5Level, field1Level);
		}

	}
}
