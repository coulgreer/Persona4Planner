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
	private static final int DEFAULT_RANK = 0;
	private static final int MAX_RANK = 10;
	private static final Color DARK_ORANGE = new Color(236, 116, 3);
	private static final Color DARK_YELLOW = new Color(230, 230, 116);
	private static final Color LIGHT_YELLOW = new Color(255, 255, 129);

	private int width;
	private int height;
	private List<SocialLink> socialLinkList = new ArrayList<SocialLink>() {
		{
			add(new SocialLink("Fool", "Investigation Team", DEFAULT_RANK));
			add(new SocialLink("Magician", "Yosuke Hanamura", DEFAULT_RANK));
			add(new SocialLink("Priestess", "Yukiko Hanamura", DEFAULT_RANK));
			add(new SocialLink("Empress", "Margaret", DEFAULT_RANK));
			add(new SocialLink("Emperor", "Kanji Tatsumi", DEFAULT_RANK));
			add(new SocialLink("Hierophant", "Ryotaro Dojima", DEFAULT_RANK));
			add(new SocialLink("Lovers", "Rise Kujikawa", DEFAULT_RANK));
			add(new SocialLink("Chariot", "Chie Satonaka", DEFAULT_RANK));
			add(new SocialLink("Justice", "Nanako Dojima", DEFAULT_RANK));
			add(new SocialLink("Hermit", "Fox", DEFAULT_RANK));
			add(new SocialLink("Fortune", "Naoto Shirogane", DEFAULT_RANK));
			add(new SocialLink("Strength", "Fellow Atheletes", DEFAULT_RANK));
			add(new SocialLink("Hanged Man", "Naoki Konishi", DEFAULT_RANK));
			add(new SocialLink("Death", "Hisano Kuroda", DEFAULT_RANK));
			add(new SocialLink("Temperance", "Eri Minami", DEFAULT_RANK));
			add(new SocialLink("Devil", "Sayoko Uehara", DEFAULT_RANK));
			add(new SocialLink("Tower", "Shu Nakajima", DEFAULT_RANK));
			add(new SocialLink("Star", "Teddie", DEFAULT_RANK));
			add(new SocialLink("Moon", "Ai Ebihara", DEFAULT_RANK));
			add(new SocialLink("Sun", "Culture Club", DEFAULT_RANK));
			add(new SocialLink("Judgement", "Seekers of Truth", DEFAULT_RANK));
		}
	};

	public BarChart(Dimension d) {
		this.width = d.width;
		this.height = d.height;
	}

	public JScrollPane initComponents() {
		JPanel socialLinkPanel = new JPanel() {
			int sectionHeight;
			int horizontalBuffer = 10;
			int verticalBuffer = 10;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				super.setBackground(LIGHT_YELLOW);

				Graphics2D g2 = (Graphics2D) g;
				Font rankFont = new Font("Comic Sans MS", Font.BOLD, 21);
				Font arcanaFont = new Font("Monospaced", Font.BOLD, 19);
				Font nameFont = new Font("Arial", Font.BOLD, 21);
				FontMetrics rankMetrics = g2.getFontMetrics(rankFont);
				FontMetrics arcanaMetrics = g2.getFontMetrics(arcanaFont);
				FontMetrics nameMetrics = g2.getFontMetrics(nameFont);
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
				for (int i = 0; i < socialLinkList.size(); i++) {
					int column1Width = rankMetrics.stringWidth("Rank " + MAX_RANK) > arcanaMetrics
							.stringWidth(findLongestString(arcanaList)) ? rankMetrics.stringWidth("Rank " + MAX_RANK)
									: arcanaMetrics.stringWidth(findLongestString(arcanaList));
					int column2Width = getParent().getWidth() - column1Width - (horizontalBuffer * 2);
					int row1Height = rankMetrics.getAscent() + verticalBuffer * 2;
					int row2Height = arcanaMetrics.getHeight() > nameMetrics.getHeight()
							? arcanaMetrics.getAscent() + arcanaMetrics.getLeading()
							: nameMetrics.getAscent() + nameMetrics.getLeading();
					int x1 = horizontalBuffer;
					int y1 = verticalBuffer + (sectionHeight * i);
					int x2 = column1Width + horizontalBuffer;
					int y2 = row1Height + (sectionHeight * i);
					double backgroundX = x1;
					double backgroundY = y2;
					double backgroundWidth = column1Width - horizontalBuffer;
					double backgroundHeight = row2Height + verticalBuffer - 3;
					double backgroundArcWidth = 10;
					double backgroundArcHeight = 10;
					double outlineX = x2;
					double outlineY = y1;
					double outlineWidth = column2Width - horizontalBuffer;
					double outlineHeight = row1Height - verticalBuffer * 2;
					SocialLink socialLink = socialLinkList.get(i);
					rank = socialLink.getSocialRank();
					arcana = socialLink.getArcana();
					name = socialLink.getName();
					sectionHeight = row1Height + row2Height + (verticalBuffer * 3);

					drawRank(g2, rankMetrics, x1, y1, rank);
					drawArcana(g2, arcanaMetrics, backgroundX, backgroundY, backgroundWidth, backgroundHeight,
							backgroundArcWidth, backgroundArcHeight, arcana);
					drawBars(g2, outlineX, outlineY, outlineWidth, outlineHeight, rank);
					drawName(g2, nameMetrics, x2, y2, name);

					// Drawing divider
					g2.setColor(DARK_ORANGE);
					g2.drawLine(x1, sectionHeight + (sectionHeight * i), getParent().getWidth() - horizontalBuffer * 2,
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
		statScrollPane.getVerticalScrollBar().setUnitIncrement(16);

		JScrollBar statScrollBar = statScrollPane.getVerticalScrollBar();
		statScrollBar.setPreferredSize(new Dimension(18, Integer.MAX_VALUE));
		statScrollBar.setUI(new MyScrollBarUI());

		return statScrollPane;
	}

	public void updateParameters() {
		for (int i = 0; i < 4; i++) {
			socialLinkList.get(1).increaseSocialRank();
		}
	}

	private void drawRank(Graphics2D g2, FontMetrics fontMetrics, int x, int y, int rank) {
		g2.setColor(DARK_ORANGE);
		g2.setFont(fontMetrics.getFont());
		g2.drawString("Rank " + rank, x, fontMetrics.getAscent() + y);
	}

	private void drawArcana(Graphics2D g2, FontMetrics fontMetrics, double x, double y, double width, double height,
			double arcWidth, double arcHeight, String arcana) {
		RoundRectangle2D cellBackground = new RoundRectangle2D.Double(x, y, width, height, arcWidth, arcHeight);
		g2.setColor(DARK_ORANGE);
		g2.fill(cellBackground);

		g2.setColor(LIGHT_YELLOW);
		g2.setFont(fontMetrics.getFont());
		g2.drawString(arcana, (int) Math.round((x + width / 2 - fontMetrics.stringWidth(arcana) / 2)),
				(int) Math.round((y + height) - (height - fontMetrics.getDescent()) / 2));
	}

	private void drawBars(Graphics2D g2, double outlineX, double outlineY, double outlineWidth, double outlineHeight,
			int rank) {
		Rectangle2D outline = new Rectangle2D.Double(outlineX, outlineY, outlineWidth, outlineHeight);
		g2.setColor(DARK_ORANGE);
		g2.fill(outline);
		g2.setColor(Color.BLACK);
		g2.draw(outline);

		for (int j = 0; j < rank; j++) {
			double pipWidth = outline.getWidth() / MAX_RANK - 1;
			double pipHeight = outline.getHeight() - 10;
			double pipX = outline.getX() + 6 + (pipWidth * j);
			double pipY = outline.getY() + 5;
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

		public SocialLink(String arcana, String name, int socialRank) {
			this.arcana = arcana;
			this.name = name;
			this.socialRank = socialRank;
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
