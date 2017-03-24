import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BarChart extends JPanel {

	public static final int DEFAULT_RANK = 0;

	public JScrollPane initComponents() {
		JPanel pane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				super.setBackground(new Color(254, 234, 44));

				JPanel stat = new JPanel();
				Graphics2D g2 = (Graphics2D) g;
				Font font = new Font("Arial", Font.BOLD, 21);
				FontMetrics metrics = g2.getFontMetrics(font);
				int bufferX = 10;
				int bufferY = 10;

				int rank;
				String arcana = "Magician";
				String name = "Yosuke H";

				for (int i = 0; i < 10; i++) {
					rank = i;
					int sectionHeight = metrics.getHeight() * 3;

					g2.setFont(font);
					g2.drawString("Rank " + rank, bufferX, metrics.getHeight() + sectionHeight * i);
					g2.drawString(arcana, bufferX, (metrics.getHeight() * 2) + sectionHeight * i);

					double x = metrics.stringWidth(arcana) + (bufferX * 2);
					double y = bufferY + sectionHeight * i;
					double w = getParent().getWidth() - x - bufferX;
					double h = metrics.getAscent() + metrics.getLeading();
					Rectangle2D outline = new Rectangle2D.Double(x, y, w, h);
					g2.draw(outline);

					g2.drawString(name, metrics.stringWidth(arcana) + (bufferX * 2), (metrics.getHeight() * 2) + sectionHeight * i);
				}

			}

			@Override
			public Dimension getPreferredSize() {
				int width = getParent().getWidth();
				int height = 800;
				return new Dimension(width, height);
			}
		};

		JScrollPane scrollPane = new JScrollPane(pane);
		scrollPane.setPreferredSize(new Dimension(800, 400));
		scrollPane.getViewport().setOpaque(false);

		return scrollPane;
	}
}
