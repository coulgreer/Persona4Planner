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

	private int maxLvl = 5;
	private int courageLvl, diligenceLvl, understandingLvl, expressionLvl, knowledgeLvl;
	private Point[] courageCoord = new Point[maxLvl];
	private Point[] diligenceCoord = new Point[maxLvl];
	private Point[] understandingCoord = new Point[maxLvl];
	private Point[] expressionCoord = new Point[maxLvl];
	private Point[] knowledgeCoord = new Point[maxLvl];

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
				g.setColor(Color.BLACK);
				drawRadarChartFrame(g);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
			}
		};

		return panel;
	}

	private void drawRadarChartFrame(Graphics g) {
		Polygon p = new Polygon();
		Ellipse2D circle;
		int numberOfSides = 5;
		double diameter = 175;
		double radius = diameter / 2;
		int pipDiameter = 6;
		int pipRadius = (int) Math.round(pipDiameter / 2);
		double theta = 2 * Math.PI / numberOfSides;
		double rotation = Math.toRadians(198);

		circle = new Ellipse2D.Double((PANEL_WIDTH / 2) - radius, (PANEL_HEIGHT / 2) - radius, diameter, diameter);
		for (int i = 0; i < numberOfSides; i++) {
			Graphics2D g2 = (Graphics2D) g;
			int x = (int) Math.round(((PANEL_WIDTH / 2) + radius * Math.cos((i * theta) + rotation)));
			int y = (int) Math.round(((PANEL_HEIGHT / 2) + radius * Math.sin((i * theta) + rotation)));

			g2.drawLine(x, y, (int) circle.getCenterX(), (int) circle.getCenterY());
			switch (i) {
			case 0:
				for (int j = 1; j <= maxLvl; j++) {
					int pointX = (int) (circle.getCenterX() - j * Math.abs(x - circle.getCenterX()) / maxLvl);
					int pointY = (int) (circle.getCenterY() - j * Math.abs(y - circle.getCenterY()) / maxLvl);
					knowledgeCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			case 1:
				for (int j = 1; j <= maxLvl; j++) {
					int pointX = (int) (circle.getCenterX() - j * Math.abs(x - circle.getCenterX()) / maxLvl);
					int pointY = (int) (circle.getCenterY() - j * Math.abs(y - circle.getCenterY()) / maxLvl);
					courageCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			case 2:
				for (int j = 1; j <= maxLvl; j++) {
					int pointX = (int) (circle.getCenterX() + j * Math.abs(x - circle.getCenterX()) / maxLvl);
					int pointY = (int) (circle.getCenterY() - j * Math.abs(y - circle.getCenterY()) / maxLvl);
					diligenceCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			case 3:
				for (int j = 1; j <= maxLvl; j++) {
					int pointX = (int) (circle.getCenterX() + j * Math.abs(x - circle.getCenterX()) / maxLvl);
					int pointY = (int) (circle.getCenterY() + j * Math.abs(y - circle.getCenterY()) / maxLvl);
					understandingCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			case 4:
				for (int j = 1; j <= maxLvl; j++) {
					int pointX = (int) (circle.getCenterX() - j * Math.abs(x - circle.getCenterX()) / maxLvl);
					int pointY = (int) (circle.getCenterY() + j * Math.abs(y - circle.getCenterY()) / maxLvl);
					expressionCoord[j - 1] = new Point(pointX, pointY);
					g2.fillOval(pointX - pipRadius, pointY - pipRadius, pipDiameter, pipDiameter);
				}
				break;
			default:
				break;
			}
			p.addPoint(x, y);
		}

		g.drawPolygon(p);
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
