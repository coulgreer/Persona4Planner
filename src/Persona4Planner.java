import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class Persona4Planner {
	private final static String STATUS_SCREEN = "Status Screen";
	private final static String CALENDAR_SCREEN = "Calendar Screen";
	private static final Color ORANGE = new Color(255, 158, 7);
	private final static Color YELLOW = new Color(255, 232, 44);
	private final static int DEFAULT_SCREEN_WIDTH = 1200;
	private final static int DEFAULT_SCREEN_HEIGHT = 800;
	private final static int DEFAULT_NAVBAR_WIDTH = (int) Math.round((DEFAULT_SCREEN_WIDTH * .25));
	private final static int DEFAULT_CARD_WIDTH = (int) Math.round((DEFAULT_SCREEN_WIDTH * .75));
	private final static int NAVBAR_BUTTON_BUFFER = 5;
	private final static int DEFAULT_NAVBAR_BUTTON_WIDTH = DEFAULT_NAVBAR_WIDTH - (NAVBAR_BUTTON_BUFFER * 2);
	private final static int DEFAULT_NAVBAR_BUTTON_HEIGHT = 50;
	private final static int DEFAUL_LEVEL = 1;

	private JPanel cards;
	private RadarChart radarChart;
	private BarChart barChart;

	public void addComponentToPane(Container panel) {
		JPanel navigationPanel = new JPanel();
		navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
		navigationPanel.setPreferredSize(new Dimension(DEFAULT_NAVBAR_WIDTH, DEFAULT_SCREEN_HEIGHT));
		navigationPanel.setBackground(ORANGE);
		navigationPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buildNavigationButton(STATUS_SCREEN, navigationPanel);
		buildNavigationButton(CALENDAR_SCREEN, navigationPanel);

		int radarChartWidth = DEFAULT_CARD_WIDTH;
		int radarChartHeight = (int) Math.round(DEFAULT_SCREEN_HEIGHT * .375);
		radarChart = new RadarChart.Builder() //
				.withWidth(radarChartWidth) //
				.withHeight(radarChartHeight) //
				.withField2LevelOf(DEFAUL_LEVEL) //
				.withField3LevelOf(DEFAUL_LEVEL) //
				.withField4LevelOf(DEFAUL_LEVEL) //
				.withField5LevelOf(DEFAUL_LEVEL) //
				.withField1LevelOf(DEFAUL_LEVEL) //
				.createRadarChart();

		int barChartWidth = DEFAULT_CARD_WIDTH;
		int barChartHeight = (int) Math.round(DEFAULT_SCREEN_HEIGHT * .625);
		barChart = new BarChart(new Dimension(barChartWidth, barChartHeight));

		JPanel statusCard = new JPanel();
		statusCard.setBackground(YELLOW);
		statusCard.add(radarChart.initComponents(), BorderLayout.NORTH);
		statusCard.add(barChart.initComponents(), BorderLayout.SOUTH);
		statusCard.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent evt) {
				radarChart.updateParameters(); // Will be removed and updating
												// will happen elsewhere when
												// calendar is created
				radarChart.repaint();

				barChart.updateParameters();// Will be removed and updating
											// will happen elsewhere when
											// calendar is created
				barChart.repaint();
			}
		});

		JPanel calendarCard = new JPanel();
		calendarCard.setBackground(YELLOW);

		cards = new JPanel(new CardLayout());
		cards.setPreferredSize(new Dimension(DEFAULT_CARD_WIDTH, DEFAULT_SCREEN_HEIGHT));
		cards.add(statusCard, STATUS_SCREEN);
		cards.add(calendarCard, CALENDAR_SCREEN);

		panel.add(navigationPanel, BorderLayout.WEST);
		panel.add(cards, BorderLayout.CENTER);

	}

	private void buildNavigationButton(String buttonName, Container panel) {
		JButton button = new JButton(buttonName);
		button.setMaximumSize(new Dimension(DEFAULT_NAVBAR_BUTTON_WIDTH, DEFAULT_NAVBAR_BUTTON_HEIGHT));
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setBorder(new LineBorder(Color.BLACK, 3));
		button.setBackground(YELLOW);
		button.setForeground(ORANGE);
		button.setFont(new Font("Arial", Font.BOLD, 21));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.show(cards, e.getActionCommand());

			}
		});

		panel.add(Box.createRigidArea(new Dimension(0, 5)));
		panel.add(button);
		panel.add(Box.createRigidArea(new Dimension(0, 5)));
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<?> future = executor.submit(new Runnable() {
			@Override
			public void run() {
				Persona4Database db = new Persona4Database();

				try {
					String line = null;
					BufferedReader br = new BufferedReader(new FileReader("documents/Arcana-Yearly-Schedule"));
					line = br.readLine();
					while ((line = br.readLine()) != null) {
						String[] dailyData = line.split("\\|");
						String date = dailyData[0];
						String day = dailyData[1];
						String weather = dailyData[2];
						String afternoon = dailyData[3];
						String night = dailyData[4];
						String magician = dailyData[5];
						String chariot = dailyData[6];
						String priestess = dailyData[7];
						String emperor = dailyData[8];
						String lovers = dailyData[9];
						String fortune = dailyData[10];
						String strength = dailyData[11];
						String sun = dailyData[12];
						String moon = dailyData[13];
						String hangedMan = dailyData[14];
						String death = dailyData[15];
						String temperance = dailyData[16];
						String hermit = dailyData[17];
						String empress = dailyData[18];
						String hierophant = dailyData[19];
						String justice = dailyData[20];
						String devil = dailyData[21];
						String tower = dailyData[22];

						db.insertToAvailability(new ArcanaAvailabilityData() //
								.withDateOf(date) //
								.withDayOf(day) //
								.withWeatherOf(weather) //
								.withAfternoon(afternoon) //
								.withNight(night) //
								.withMagicianAvailability(magician) //
								.withChariotAvailability(chariot) //
								.withPriestessAvailability(priestess) //
								.withEmperorAvailability(emperor) //
								.withLoversAvailability(lovers) //
								.withFortuneAvailability(fortune) //
								.withStrengthAvailability(strength) //
								.withSunAvailability(sun) //
								.withMoonAvailability(moon) //
								.withHangedManAvailability(hangedMan) //
								.withDeathAvailability(death) //
								.withTemperanceAvailability(temperance) //
								.withHermitAvailability(hermit) //
								.withEmpressAvailability(empress) //
								.withHierophantAvailability(hierophant) //
								.withJusticeAvailability(justice) //
								.withDevilAvailability(devil) //
								.withTowerAvailability(tower));
					}
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					String line = null;
					BufferedReader br = new BufferedReader(new FileReader("documents/Quests"));
					line = br.readLine();
					while ((line = br.readLine()) != null) {
						String[] questData = line.split("\\|");
						String questName = questData[0];
						String questNumber = questData[1];
						String questGiver = questData[2];
						String questTimeFrame = questData[3];
						String questReward = questData[4];
						String remarks = questData[5];

						db.insertToQuests(new QuestData() //
								.withQuestName(questName) //
								.withQuestNumber(questNumber) //
								.withQuestGiver(questGiver) //
								.withAvailabilityDate(questTimeFrame) //
								.withRewardOf(questReward) //
								.withRemarks(remarks));
					}
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					String line = null;
					BufferedReader br = new BufferedReader(new FileReader("documents/Social-Link-Answers"));
					line = br.readLine();
					while ((line = br.readLine()) != null) {
						String[] lectureData = line.split("\\|");
						String arcana = lectureData[0];
						String rank1Answers = lectureData[1];
						String rank2Answers = lectureData[2];
						String rank3Answers = lectureData[3];
						String rank4Answers = lectureData[4];
						String rank5Answers = lectureData[5];
						String rank6Answers = lectureData[6];
						String rank7Answers = lectureData[7];
						String rank8Answers = lectureData[8];
						String rank9Answers = lectureData[9];
						String rank10Answers = lectureData[10];
						String remarks = lectureData[11];

						db.insertToConversationAnswers(new ArcanaConversationData() //
								.withArcana(arcana) //
								.withRank1Answers(rank1Answers) //
								.withRank2Answers(rank2Answers) //
								.withRank3Answers(rank3Answers) //
								.withRank4Answers(rank4Answers) //
								.withRank5Answers(rank5Answers) //
								.withRank6Answers(rank6Answers) //
								.withRank7Answers(rank7Answers) //
								.withRank8Answers(rank8Answers) //
								.withRank9Answers(rank9Answers) //
								.withRank10Answers(rank10Answers) //
								.withRemarks(remarks));
					}
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		executor.submit(new Runnable() {
			@Override
			public void run() {
				final SplashScreen splash = SplashScreen.getSplashScreen();
				if (splash == null) {
					System.out.println("SplashScreen.getSplashScreen() returned null");
					return;
				}
				Graphics2D g = splash.createGraphics();
				if (g == null) {
					System.out.println("g is null");
					return;
				}
				for (int i = 0; !future.isDone(); i++) {
					renderSplashFrame(g, i);
					splash.update();
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
					}
				}
				splash.close();
			}
		});
		executor.shutdown();

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGUI();

			}
		});

	}

	private static void renderSplashFrame(Graphics2D g, int frame) {
		final String[] comps = { "Creating Database", "This may take a minute" };
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(70, 130, 200, 40);
		g.setPaintMode();
		g.setColor(Color.BLACK);
		g.drawString("Loading " + comps[(frame / 5) % comps.length] + "...", 70, 140);
	}

	private static void createGUI() {
		JFrame frame = new JFrame("Persona 4 Planner");
		frame.setSize(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		Persona4Planner p4p = new Persona4Planner();
		p4p.addComponentToPane(frame.getContentPane());

		frame.setVisible(true);
		forceToFront(frame);
	}

	private static void forceToFront(JFrame frame) {
		frame.setAlwaysOnTop(true);
		frame.setAlwaysOnTop(false);
	}

	public static class ArcanaAvailabilityData {
		private String date, day, weather, afternoon, night;
		private String magician, chariot, priestess, emperor, lovers, fortune, strength, sun, moon, hangedMan, death,
				temperance, hermit, empress, hierophant, justice, devil, tower;

		public ArcanaAvailabilityData withDateOf(String date) {
			this.date = date;
			return this;
		}

		public ArcanaAvailabilityData withDayOf(String day) {
			this.day = day;
			return this;
		}

		public ArcanaAvailabilityData withWeatherOf(String weather) {
			this.weather = weather;
			return this;
		}

		public ArcanaAvailabilityData withAfternoon(String afternoon) {
			this.afternoon = afternoon;
			return this;
		}

		public ArcanaAvailabilityData withNight(String night) {
			this.night = night;
			return this;
		}

		public ArcanaAvailabilityData withMagicianAvailability(String magician) {
			this.magician = magician;
			return this;
		}

		public ArcanaAvailabilityData withChariotAvailability(String chariot) {
			this.chariot = chariot;
			return this;
		}

		public ArcanaAvailabilityData withPriestessAvailability(String priestess) {
			this.priestess = priestess;
			return this;
		}

		public ArcanaAvailabilityData withEmperorAvailability(String emperor) {
			this.emperor = emperor;
			return this;
		}

		public ArcanaAvailabilityData withLoversAvailability(String lovers) {
			this.lovers = lovers;
			return this;
		}

		public ArcanaAvailabilityData withFortuneAvailability(String fortune) {
			this.fortune = fortune;
			return this;
		}

		public ArcanaAvailabilityData withStrengthAvailability(String strength) {
			this.strength = strength;
			return this;
		}

		public ArcanaAvailabilityData withSunAvailability(String sun) {
			this.sun = sun;
			return this;
		}

		public ArcanaAvailabilityData withMoonAvailability(String moon) {
			this.moon = moon;
			return this;
		}

		public ArcanaAvailabilityData withHangedManAvailability(String hangedMan) {
			this.hangedMan = hangedMan;
			return this;
		}

		public ArcanaAvailabilityData withDeathAvailability(String death) {
			this.death = death;
			return this;
		}

		public ArcanaAvailabilityData withTemperanceAvailability(String temperance) {
			this.temperance = temperance;
			return this;
		}

		public ArcanaAvailabilityData withHermitAvailability(String hermit) {
			this.hermit = hermit;
			return this;
		}

		public ArcanaAvailabilityData withEmpressAvailability(String empress) {
			this.empress = empress;
			return this;
		}

		public ArcanaAvailabilityData withHierophantAvailability(String hierophant) {
			this.hierophant = hierophant;
			return this;
		}

		public ArcanaAvailabilityData withJusticeAvailability(String justice) {
			this.justice = justice;
			return this;
		}

		public ArcanaAvailabilityData withDevilAvailability(String devil) {
			this.devil = devil;
			return this;
		}

		public ArcanaAvailabilityData withTowerAvailability(String tower) {
			this.tower = tower;
			return this;
		}

		public String date() {
			return date;
		}

		public String day() {
			return day;
		}

		public String weather() {
			return weather;
		}

		public String afternoon() {
			return afternoon;
		}

		public String night() {
			return night;
		}

		public String magician() {
			return magician;
		}

		public String chariot() {
			return chariot;
		}

		public String priestess() {
			return priestess;
		}

		public String emperor() {
			return emperor;
		}

		public String lovers() {
			return lovers;
		}

		public String fortune() {
			return fortune;
		}

		public String strength() {
			return strength;
		}

		public String sun() {
			return sun;
		}

		public String moon() {
			return moon;
		}

		public String hangedMan() {
			return hangedMan;
		}

		public String death() {
			return death;
		}

		public String temperance() {
			return temperance;
		}

		public String hermit() {
			return hermit;
		}

		public String empress() {
			return empress;
		}

		public String hierophant() {
			return hierophant;
		}

		public String justice() {
			return justice;
		}

		public String devil() {
			return devil;
		}

		public String tower() {
			return tower;
		}
	}

	public static class QuestData {
		private String questName, questNumber, questGiver, questTimeFrame, reward, remarks;

		public QuestData withQuestName(String questName) {
			this.questName = questName;
			return this;
		}

		public QuestData withQuestNumber(String questNumber) {
			this.questNumber = questNumber;
			return this;
		}

		public QuestData withQuestGiver(String questGiver) {
			this.questGiver = questGiver;
			return this;
		}

		public QuestData withAvailabilityDate(String questTimeFrame) {
			this.questTimeFrame = questTimeFrame;
			return this;
		}

		public QuestData withRewardOf(String reward) {
			this.reward = reward;
			return this;
		}

		public QuestData withRemarks(String remarks) {
			this.remarks = remarks;
			return this;
		}

		public String questName() {
			return questName;
		}

		public String questNumber() {
			return questNumber;
		}

		public String questGiver() {
			return questGiver;
		}

		public String questTimeFrame() {
			return questTimeFrame;
		}

		public String reward() {
			return reward;
		}

		public String remarks() {
			return remarks;
		}
	}

	public static class ArcanaConversationData {
		private String arcana;
		private String rank1Answers, rank2Answers, rank3Answers, rank4Answers, rank5Answers;
		private String rank6Answers, rank7Answers, rank8Answers, rank9Answers, rank10Answers;
		private String remarks;

		public ArcanaConversationData withArcana(String arcana) {
			this.arcana = arcana;
			return this;
		}

		public ArcanaConversationData withRank1Answers(String rank1Answers) {
			this.rank1Answers = rank1Answers;
			return this;
		}

		public ArcanaConversationData withRank2Answers(String rank2Answers) {
			this.rank2Answers = rank2Answers;
			return this;
		}

		public ArcanaConversationData withRank3Answers(String rank3Answers) {
			this.rank3Answers = rank3Answers;
			return this;
		}

		public ArcanaConversationData withRank4Answers(String rank4Answers) {
			this.rank4Answers = rank4Answers;
			return this;
		}

		public ArcanaConversationData withRank5Answers(String rank5Answers) {
			this.rank5Answers = rank5Answers;
			return this;
		}

		public ArcanaConversationData withRank6Answers(String rank6Answers) {
			this.rank6Answers = rank6Answers;
			return this;
		}

		public ArcanaConversationData withRank7Answers(String rank7Answers) {
			this.rank7Answers = rank7Answers;
			return this;
		}

		public ArcanaConversationData withRank8Answers(String rank8Answers) {
			this.rank8Answers = rank8Answers;
			return this;
		}

		public ArcanaConversationData withRank9Answers(String rank9Answers) {
			this.rank9Answers = rank9Answers;
			return this;
		}

		public ArcanaConversationData withRank10Answers(String rank10Answers) {
			this.rank10Answers = rank10Answers;
			return this;
		}

		public ArcanaConversationData withRemarks(String remarks) {
			this.remarks = remarks;
			return this;
		}

		public String arcana() {
			return arcana;
		}

		public String rank1Answers() {
			return rank1Answers;
		}

		public String rank2Answers() {
			return rank2Answers;
		}

		public String rank3Answers() {
			return rank3Answers;
		}

		public String rank4Answers() {
			return rank4Answers;
		}

		public String rank5Answers() {
			return rank5Answers;
		}

		public String rank6Answers() {
			return rank6Answers;
		}

		public String rank7Answers() {
			return rank7Answers;
		}

		public String rank8Answers() {
			return rank8Answers;
		}

		public String rank9Answers() {
			return rank9Answers;
		}

		public String rank10Answers() {
			return rank10Answers;
		}

		public String remarks() {
			return remarks;
		}
	}
	
	public static class LectureData {
		private String date;
		private String answer;
		private String socialLinkModifier;
		private String socialQualityModifier;
		
		public LectureData withDate(String date) {
			this.date = date;
			return this;
		}
		
		public LectureData withAnswer(String answer) {
			this.answer = answer;
			return this;
		}
		
		public LectureData withSocialLinkModifier(String socialLinkModifier) {
			this.socialLinkModifier = socialLinkModifier;
			return this;
		}
		
		public LectureData withSocialQualityModifier(String socialQualityModifier) {
			this.socialQualityModifier = socialQualityModifier;
			return this;
		}
		
		public String date() {
			return date;
		}
		
		public String answer() {
			return answer;
		}
		
		public String socialLinkModifier() {
			return socialLinkModifier;
		}
		
		public String socialQualityModifier() {
			return socialQualityModifier;
		}
	}

}
