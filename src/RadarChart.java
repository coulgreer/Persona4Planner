import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class RadarChart extends JPanel {
	private static final int PANEL_WIDTH = 200;
	private static final int PANEL_HEIGHT = 200;
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
		Polygon chartFrame = new Polygon();
		Ellipse2D circumscribedCircle;
		int numberOfSides = 5;
		double diameter = 175;
		double radius = diameter / 2;
		int pipDiameter = 6;
		int pipRadius = (int) Math.round(pipDiameter / 2);
		double theta = 2 * Math.PI / numberOfSides;
		double rotation = Math.toRadians(198);

		g.setColor(Color.BLACK);

		circumscribedCircle = new Ellipse2D.Double((PANEL_WIDTH / 2) - radius, (PANEL_HEIGHT / 2) - radius, diameter,
				diameter);
		for (int i = 0; i < numberOfSides; i++) {
			Graphics2D g2 = (Graphics2D) g;
			int x = (int) Math.round(((PANEL_WIDTH / 2) + radius * Math.cos((i * theta) + rotation)));
			int y = (int) Math.round(((PANEL_HEIGHT / 2) + radius * Math.sin((i * theta) + rotation)));

			g2.drawLine(x, y, (int) circumscribedCircle.getCenterX(), (int) circumscribedCircle.getCenterY());

			// Draws the pips on the spokes in the pentagon
			switch (i) {
			case 0:
				for (int j = 1; j <= MAX_LEVEL; j++) {
					int pointX = (int) (circumscribedCircle.getCenterX()
							- j * Math.abs(x - circumscribedCircle.getCenterX()) / MAX_LEVEL);
					int pointY = (int) (circumscribedCircle.getCenterY()
							- j * Math.abs(y - circumscribedCircle.getCenterY()) / MAX_LEVEL);
					knowledgeCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			case 1:
				for (int j = 1; j <= MAX_LEVEL; j++) {
					int pointX = (int) (circumscribedCircle.getCenterX()
							- j * Math.abs(x - circumscribedCircle.getCenterX()) / MAX_LEVEL);
					int pointY = (int) (circumscribedCircle.getCenterY()
							- j * Math.abs(y - circumscribedCircle.getCenterY()) / MAX_LEVEL);
					courageCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			case 2:
				for (int j = 1; j <= MAX_LEVEL; j++) {
					int pointX = (int) (circumscribedCircle.getCenterX()
							+ j * Math.abs(x - circumscribedCircle.getCenterX()) / MAX_LEVEL);
					int pointY = (int) (circumscribedCircle.getCenterY()
							- j * Math.abs(y - circumscribedCircle.getCenterY()) / MAX_LEVEL);
					diligenceCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			case 3:
				for (int j = 1; j <= MAX_LEVEL; j++) {
					int pointX = (int) (circumscribedCircle.getCenterX()
							+ j * Math.abs(x - circumscribedCircle.getCenterX()) / MAX_LEVEL);
					int pointY = (int) (circumscribedCircle.getCenterY()
							+ j * Math.abs(y - circumscribedCircle.getCenterY()) / MAX_LEVEL);
					understandingCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			case 4:
				for (int j = 1; j <= MAX_LEVEL; j++) {
					int pointX = (int) (circumscribedCircle.getCenterX()
							- j * Math.abs(x - circumscribedCircle.getCenterX()) / MAX_LEVEL);
					int pointY = (int) (circumscribedCircle.getCenterY()
							+ j * Math.abs(y - circumscribedCircle.getCenterY()) / MAX_LEVEL);
					expressionCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			default:
				break;
			}

			chartFrame.addPoint(x, y);
		}

		drawStatParameters(g);

		g.setColor(Color.BLACK);
		g.drawPolygon(chartFrame);

	}

	/**
	 * Draws a semi-transparent polygon that visually represents characters social quality levels
	 * @param g - the graphics object to help draw the frame.
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
