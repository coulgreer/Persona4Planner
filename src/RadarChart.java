import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class RadarChart extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
                g.setColor(Color.BLACK);
                drawRadarChartFrame(g);
			}
			
			@Override
            public Dimension getPreferredSize() {
                return new Dimension(200, 150);
            }
		};
		
		return panel;
	}

	private void drawRadarChartFrame(Graphics g) {
		Polygon p = new Polygon();
		int numberOfSides = 5;
		double radius = 50;
		double theta = 2 * Math.PI / numberOfSides;
		for (int i = 0; i < numberOfSides; i++) {
			int x = (int) (100 + radius * Math.cos(i * theta));
			int y = (int) (100 + radius * Math.sin(i * theta));
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
