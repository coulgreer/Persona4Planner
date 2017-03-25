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

	private List<SocialLink> socialLinks = new ArrayList<SocialLink>() {
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
				for (int i = 0; i < 10; i++) {
					SocialLink socialLink = socialLinks.get(i);
					rank = socialLink.getSocialRank();
					arcana = socialLink.getArcana();
					name = socialLink.getName();
					int sectionHeight = metrics.getHeight() * 3;

					g2.setFont(font);
					g2.drawString("Rank " + rank, bufferX, metrics.getHeight() + sectionHeight * i);
					g2.drawString(arcana, bufferX, (metrics.getHeight() * 2) + sectionHeight * i);

					double x = metrics.stringWidth(findLongestString()) + (bufferX * 2);
					double y = bufferY + sectionHeight * i;
					double w = getParent().getWidth() - x - bufferX;
					double h = metrics.getAscent() + metrics.getLeading();
					Rectangle2D outline = new Rectangle2D.Double(x, y, w, h);
					g2.draw(outline);

					g2.drawString(name, metrics.stringWidth(findLongestString()) + (bufferX * 2),
							(metrics.getHeight() * 2) + sectionHeight * i);
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

	// Investigate for flexibility
	private String findLongestString() {
		String longestString = "Rank 99";
		int longestStringLength = 0;
		for(SocialLink sl : socialLinks){
			if(sl.getArcana().length() > longestStringLength) {
				longestStringLength = sl.getArcana().length();
				longestString = sl.getArcana();
			}
		}
		return longestString;
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
