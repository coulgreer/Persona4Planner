import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class BarChart extends JPanel {
	private static final int HORIZONTAL_BUFFER = 10;
	private static final int VERTICAL_BUFFER = 10;
	private static final int PIP_HORIZONTAL_BUFFER = 6;
	private static final int PIP_VERTICAL_BUFFER = 6;
	private static final int DEFAULT_RANK = 0;
	private static final int MAX_RANK = 10;
	private static final Color DARK_ORANGE = new Color(236, 116, 3);
	private static final Color DARK_YELLOW = new Color(230, 230, 116);
	private static final Color LIGHT_YELLOW = new Color(255, 255, 129);

	private int width;
	private int height;
	private int sectionHeight;
	private List<SocialLink> socialLinkList = new ArrayList<SocialLink>() {
		{
			add(new SocialLink("Fool", "Investigation Team", DEFAULT_RANK,
					new Integer[] { null, null, null, null, null, null, null, null, null, null }));
			add(new SocialLink("Magician", "Yosuke Hanamura", DEFAULT_RANK,
					new Integer[] { 0, 0, 2, 3, 4, 4, 6, 6, 4, 8 }));
			add(new SocialLink("Priestess", "Yukiko Amagi", DEFAULT_RANK,
					new Integer[] { 0, 0, 2, 2, 4, 5, 6, 7, 8, 8 }));
			add(new SocialLink("Empress", "Margaret", DEFAULT_RANK,
					new Integer[] { null, null, null, null, null, null, null, null, null, null }));
			add(new SocialLink("Emperor", "Kanji Tatsumi", DEFAULT_RANK,
					new Integer[] { 0, 0, 2, 2, 4, 6, 4, 5, 6, 6 }));
			add(new SocialLink("Hierophant", "Ryotaro Dojima", DEFAULT_RANK,
					new Integer[] { 0, 0, 4, 4, 6, 6, 5, 5, 6, 8 }));
			add(new SocialLink("Lovers", "Rise Kujikawa", DEFAULT_RANK,
					new Integer[] { 0, 0, 2, 4, 6, 6, 6, 8, 6, 9 }));
			add(new SocialLink("Chariot", "Chie Satonaka", DEFAULT_RANK,
					new Integer[] { 0, 0, 3, 3, 4, 4, 6, 5, 6, 10 }));
			add(new SocialLink("Justice", "Nanako Dojima", DEFAULT_RANK,
					new Integer[] { 0, 3, 6, 9, 10, 12, 9, 6, 10, 12 }));
			add(new SocialLink("Hermit", "Fox", DEFAULT_RANK,
					new Integer[] { null, null, null, null, null, null, null, null, null, null }));
			add(new SocialLink("Fortune", "Naoto Shirogane", DEFAULT_RANK,
					new Integer[] { 0, 0, 0, 3, 5, 6, 8, 8, 4, 8 }));
			add(new SocialLink("Strength", "Fellow Atheletes", DEFAULT_RANK,
					new Integer[] { 0, 0, 6, 6, 6, 4, 0, 5, 7, 6 }, new Integer[] { 0, 0, 4, 2, 3, 2, 4, 0, 6, 6 }));
			add(new SocialLink("Hanged Man", "Naoki Konishi", DEFAULT_RANK,
					new Integer[] { 0, 0, 0, 4, 4, 4, 4, 4, 6, 6 }));
			add(new SocialLink("Death", "Hisano Kuroda", DEFAULT_RANK,
					new Integer[] { null, null, null, null, null, null, null, null, null, null }));
			add(new SocialLink("Temperance", "Eri Minami", DEFAULT_RANK,
					new Integer[] { 0, 0, 2, 2, 4, 3, 4, 6, 4, 13 }));
			add(new SocialLink("Devil", "Sayoko Uehara", DEFAULT_RANK, new Integer[] { 0, 0, 0, 3, 3, 4, 2, 6, 6, 3 }));
			add(new SocialLink("Tower", "Shu Nakajima", DEFAULT_RANK, new Integer[] { 0, 0, 2, 6, 3, 5, 5, 4, 4, 0 }));
			add(new SocialLink("Star", "Teddie", DEFAULT_RANK,
					new Integer[] { null, null, null, null, null, null, null, null, null, null }));
			add(new SocialLink("Moon", "Ai Ebihara", DEFAULT_RANK, new Integer[] { 0, 0, 4, 4, 4, 0, 4, 8, 8, 6 }));
			add(new SocialLink("Sun", "Culture Club", DEFAULT_RANK, new Integer[] { 0, 0, 2, 2, 6, 8, 6, 7, 0, 5 },
					new Integer[] { 0, 0, 2, 2, 4, 6, 6, 6, 4, 6 }));
			add(new SocialLink("Judgement", "Seekers of Truth", DEFAULT_RANK,
					new Integer[] { null, null, null, null, null, null, null, null, null, null }));
		}
	};

	public BarChart(Dimension d) {
		this.width = d.width;
		this.height = d.height;
	}

	public JScrollPane initComponents() {
		JPanel socialLinkPanel = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				super.setBackground(LIGHT_YELLOW);

				Graphics2D g2 = (Graphics2D) g;
				Font rankFont = new Font("Comic Sans MS", Font.BOLD, 21);
				Font arcanaFont = new Font("Monospaced", Font.BOLD, 19);
				Font nameFont = new Font("Arial", Font.BOLD, 21);
				Font pointFont = new Font("Arial", Font.PLAIN, 18);
				FontMetrics rankMetrics = g2.getFontMetrics(rankFont);
				FontMetrics arcanaMetrics = g2.getFontMetrics(arcanaFont);
				FontMetrics nameMetrics = g2.getFontMetrics(nameFont);
				FontMetrics pointMetrics = g2.getFontMetrics(pointFont);
				List<String> arcanaList = new ArrayList<String>() {
					{
						for (SocialLink sl : socialLinkList) {
							add(sl.getArcana());
						}
					}
				};

				int rank;
				String arcana;
				String name;
				String points;
				for (int i = 0; i < socialLinkList.size(); i++) {
					int column1Width = rankMetrics.stringWidth("Rank " + MAX_RANK) > arcanaMetrics
							.stringWidth(findLongestString(arcanaList)) ? rankMetrics.stringWidth("Rank " + MAX_RANK)
									: arcanaMetrics.stringWidth(findLongestString(arcanaList));
					int column2Width = (getParent().getWidth() / 3 * 2) - (HORIZONTAL_BUFFER * 2);
					int column3Width = getParent().getWidth() - column1Width - column2Width - (HORIZONTAL_BUFFER * 2);
					int row1Height = rankMetrics.getAscent() + VERTICAL_BUFFER * 2;
					int row2Height = arcanaMetrics.getHeight() > nameMetrics.getHeight()
							? arcanaMetrics.getAscent() + arcanaMetrics.getLeading()
							: nameMetrics.getAscent() + nameMetrics.getLeading();
					int x1 = HORIZONTAL_BUFFER;
					int y1 = VERTICAL_BUFFER + (sectionHeight * i);
					int x2 = column1Width + HORIZONTAL_BUFFER;
					int y2 = row1Height + (sectionHeight * i);
					int x3 = column1Width + column2Width + HORIZONTAL_BUFFER;
					SocialLink socialLink = socialLinkList.get(i);
					rank = socialLink.getSocialRank();
					arcana = socialLink.getArcana();
					name = socialLink.getName();
					points = socialLink.getPointsForRankUp(rank);
					sectionHeight = row1Height + row2Height + (VERTICAL_BUFFER * 3);

					drawRank(g2, rankMetrics, x1, y1, rank);
					drawArcana(g2, arcanaMetrics, x1, y2, column1Width, row2Height, arcana);
					drawBars(g2, x2, y1, column2Width, row1Height, rank);
					drawName(g2, nameMetrics, x2, y2, name);
					drawPoints(g2, pointMetrics, x3, y1, column3Width, row1Height, points);

					// Drawing divider
					g2.setColor(DARK_ORANGE);
					g2.drawLine(x1, sectionHeight + (sectionHeight * i), getParent().getWidth() - HORIZONTAL_BUFFER * 2,
							sectionHeight + (sectionHeight * i));
				}

			}

			@Override
			public Dimension getPreferredSize() {
				int width = getParent().getWidth();
				int height = sectionHeight * socialLinkList.size();
				return new Dimension(width, height);
			}
		};

		JScrollPane statScrollPane = new JScrollPane(socialLinkPanel);
		statScrollPane.setPreferredSize(new Dimension(width - 100, height - 75));

		JScrollBar statScrollBar = statScrollPane.getVerticalScrollBar();
		statScrollBar.setPreferredSize(new Dimension(18, Integer.MAX_VALUE));
		statScrollBar.setUI(new MyScrollBarUI());
		statScrollBar.setUnitIncrement(16);

		return statScrollPane;
	}

	public void updateParameters() {
		for (int i = 0; i < 3; i++) {
			socialLinkList.get(1).increaseSocialRank();
		}
	}

	private void drawRank(Graphics2D g2, FontMetrics fontMetrics, int x, int y, int rank) {
		g2.setColor(DARK_ORANGE);
		g2.setFont(fontMetrics.getFont());
		g2.drawString("Rank " + rank, x, fontMetrics.getAscent() + y);
	}

	private void drawArcana(Graphics2D g2, FontMetrics fontMetrics, double x, double y, double columnWidth,
			double rowHeight, String arcana) {
		double backgroundWidth = columnWidth - HORIZONTAL_BUFFER;
		double backgroundHeight = rowHeight + VERTICAL_BUFFER - 3;
		double arcWidth = 10;
		double arcHeight = 10;
		RoundRectangle2D cellBackground = new RoundRectangle2D.Double(x, y, backgroundWidth, backgroundHeight, arcWidth,
				arcHeight);
		g2.setColor(DARK_ORANGE);
		g2.fill(cellBackground);

		g2.setColor(LIGHT_YELLOW);
		g2.setFont(fontMetrics.getFont());
		g2.drawString(arcana, (int) Math.round((x + backgroundWidth / 2 - fontMetrics.stringWidth(arcana) / 2)),
				(int) Math.round((y + backgroundHeight) - (backgroundHeight - fontMetrics.getDescent()) / 2));
	}

	private void drawBars(Graphics2D g2, double x, double y, double columnWidth, double rowHeight, int rank) {
		double outlineWidth = columnWidth - HORIZONTAL_BUFFER;
		double outlineHeight = rowHeight - VERTICAL_BUFFER * 2;
		Rectangle2D outline = new Rectangle2D.Double(x, y, outlineWidth, outlineHeight);
		g2.setColor(DARK_ORANGE);
		g2.fill(outline);
		g2.setColor(Color.BLACK);
		g2.draw(outline);

		for (int j = 0; j < rank; j++) {
			double pipWidth = (outline.getWidth() - PIP_HORIZONTAL_BUFFER * 2) / MAX_RANK;
			double pipHeight = outline.getHeight() - PIP_VERTICAL_BUFFER * 2;
			double pipX = outline.getX() + PIP_HORIZONTAL_BUFFER + (pipWidth * j);
			double pipY = outline.getY() + PIP_VERTICAL_BUFFER;
			Rectangle2D pip = new Rectangle2D.Double(pipX, pipY, pipWidth, pipHeight);
			g2.setColor(LIGHT_YELLOW);
			g2.fill(pip);
			g2.setColor(DARK_ORANGE);
			g2.draw(pip);
		}
	}

	private void drawName(Graphics2D g2, FontMetrics fontMetrics, int x, int y, String name) {
		g2.setFont(fontMetrics.getFont());
		g2.setColor(DARK_ORANGE);
		g2.drawString(name, x, fontMetrics.getAscent() + y);
	}

	private void drawPoints(Graphics2D g2, FontMetrics fontMetrics, int x, int y, int columnWidth, int rowHeight,
			String points) {
		int centerX = x - (fontMetrics.stringWidth(points) / 2) + (columnWidth / 2);
		int centerY = y - (fontMetrics.getHeight() / 2) + (rowHeight / 2) + VERTICAL_BUFFER;
		g2.setFont(fontMetrics.getFont());
		g2.setColor(Color.BLACK);
		g2.drawString(points, centerX, centerY);
	}

	// Investigate for flexibility
	private String findLongestString(List<String> list) {
		String longestString = "";
		int longestStringLength = 0;
		for (String str : list) {
			if (str.length() > longestStringLength) {
				longestStringLength = str.length();
				longestString = str;
			}
		}
		return longestString + "/t";
	}

	public class SocialLink {
		String arcana;
		String name;
		int socialRank;
		Integer[] pointsForRankUpArray;

		public SocialLink(String arcana, String name, int socialRank, Integer[] pointsForRankUpArray) {
			this.arcana = arcana;
			this.name = name;
			this.socialRank = socialRank;
			this.pointsForRankUpArray = pointsForRankUpArray;
		}

		public SocialLink(String arcana, String name, int socialRank, Integer[] pointsForRankUpArray1,
				Integer[] pointsForRankUpArray2) {
			this.arcana = arcana;
			this.name = name;
			this.socialRank = socialRank;
			this.pointsForRankUpArray = pointsForRankUpArray1;
		}

		public String getArcana() {
			return arcana;
		}

		public String getName() {
			return name;
		}

		public int getSocialRank() {
			return socialRank;
		}

		public String getPointsForRankUp(int currentRank) {
			if (currentRank + 1 <= 10) {
				return pointsForRankUpArray[currentRank] == null ? "-"
						: String.valueOf(pointsForRankUpArray[currentRank]);
			} else {
				return "MAX";
			}
		}

		public void increaseSocialRank() {
			if (socialRank < 10) {
				socialRank++;
			}
		}

		public void decreaseSocialRank() {
			if (socialRank > 0) {
				socialRank--;
			}
		}
	}

	class MyScrollBarUI extends BasicScrollBarUI {
		private final Dimension d = new Dimension();
		private ImageIcon downArrow, upArrow, leftArrow, rightArrow;

		public MyScrollBarUI() {
			upArrow = new ImageIcon("images/arrow-up-icon.png");
			downArrow = new ImageIcon("images/arrow-down-icon.png");
			rightArrow = new ImageIcon("images/arrow-right-icon.png");
			leftArrow = new ImageIcon("images/arrow-left-icon.png");
		}

		@Override
		protected JButton createDecreaseButton(int orientation) {
			JButton decreaseButton = new JButton(getAppropriateIcon(orientation)) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(22, 22);
				}
			};
			decreaseButton.setBackground(Color.BLACK);
			decreaseButton.setBorderPainted(false);
			decreaseButton.setFocusPainted(false);
			return decreaseButton;
		}

		@Override
		protected JButton createIncreaseButton(int orientation) {
			JButton increaseButton = new JButton(getAppropriateIcon(orientation)) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(22, 22);
				}
			};
			increaseButton.setBackground(Color.BLACK);
			increaseButton.setBorderPainted(false);
			increaseButton.setFocusPainted(false);
			return increaseButton;
		}

		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(Color.BLACK);
			g2.fillRect(r.x, r.y, r.width, r.height);
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Color color = null;
			JScrollBar sb = (JScrollBar) c;
			if (!sb.isEnabled() || r.width > r.height) {
				return;
			} else if (isDragging) {
				color = DARK_YELLOW;
			} else if (isThumbRollover()) {
				color = LIGHT_YELLOW;
			} else {
				color = LIGHT_YELLOW;
			}
			g2.setPaint(color);
			g2.fillRoundRect(r.x - ((int) Math.round((r.width - 8) / 2.0)) + (int) Math.round(r.width / 2.0), r.y,
					r.width - 8, r.height, 10, 10);
			g2.dispose();
		}

		@Override
		protected void setThumbBounds(int x, int y, int width, int height) {
			super.setThumbBounds(x, y, width, height);
			scrollbar.repaint();
		}

		private ImageIcon getAppropriateIcon(int orientation) {
			switch (orientation) {
			case SwingConstants.SOUTH:
				return downArrow;
			case SwingConstants.NORTH:
				return upArrow;
			case SwingConstants.EAST:
				return rightArrow;
			default:
				return leftArrow;
			}
		}
	}
}
