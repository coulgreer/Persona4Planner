import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BarChart extends JPanel {
	public static final int DEFAULT_RANK = 0;
	public static final int MAX_RANK = 10;

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
				String arcana;
				String name;
				for (int i = 0; i < socialLinkList.size(); i++) {
					SocialLink socialLink = socialLinkList.get(i);
					rank = socialLink.getSocialRank();
					arcana = socialLink.getArcana();
					name = socialLink.getName();
					int sectionHeight = metrics.getHeight() * 3;

					g2.setFont(font);
					g2.drawString("Rank " + rank, bufferX, metrics.getHeight() + sectionHeight * i);
					g2.drawString(arcana, bufferX, (metrics.getHeight() * 2) + sectionHeight * i);

					double outlineX = metrics.stringWidth(findLongestString()) + (bufferX * 2);
					double outlineY = bufferY + sectionHeight * i;
					double outlineWidth = getParent().getWidth() - outlineX - bufferX;
					double outlineHeight = metrics.getAscent() + metrics.getLeading();
					Rectangle2D outline = new Rectangle2D.Double(outlineX, outlineY, outlineWidth, outlineHeight);
					g2.setColor(Color.BLACK);
					g2.draw(outline);

					for (int j = 0; j < rank; j++) {
						double pipWidth = outlineWidth / MAX_RANK;
						double pipHeight = outlineHeight;
						double pipX = outlineX + (pipWidth * j);
						double pipY = outlineY;
						Rectangle2D pip = new Rectangle2D.Double(pipX, pipY, pipWidth, pipHeight);
						g2.setColor(Color.ORANGE);
						g2.fill(pip);
						g2.setColor(Color.BLACK);
						g2.draw(pip);
					}

					g2.setColor(Color.BLACK);
					g2.drawString(name, metrics.stringWidth(findLongestString()) + (bufferX * 2),
							(metrics.getHeight() * 2) + sectionHeight * i);
				}

			}

			// Make height programmatically
			@Override
			public Dimension getPreferredSize() {
				int width = getParent().getWidth();
				int height = 1600;
				return new Dimension(width, height);
			}
		};

		JScrollPane scrollPane = new JScrollPane(pane);
		scrollPane.setPreferredSize(new Dimension(800, 400));
		scrollPane.getViewport().setOpaque(false);

		return scrollPane;
	}

	public void updateParameters() {
		for (int i = 0; i < 4; i++) {
			socialLinkList.get(1).increaseSocialRank();
		}
	}

	// Investigate for flexibility
	private String findLongestString() {
		String longestString = "Rank 99";
		int longestStringLength = 0;
		for (SocialLink sl : socialLinkList) {
			if (sl.getArcana().length() > longestStringLength) {
				longestStringLength = sl.getArcana().length();
				longestString = sl.getArcana();
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
			socialRank++;
		}

		public void decreaseSocialRank() {
			socialRank--;
		}
	}
}
