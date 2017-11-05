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
import java.sql.Date;
import java.text.ParseException;
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

import cagreer.persona.data.AiyaChineseDiner;
import cagreer.persona.data.Book;
import cagreer.persona.data.SocialLinkAvailability;
import cagreer.persona.data.DailyData;
import cagreer.persona.data.Dungeon;
import cagreer.persona.data.Exam;
import cagreer.persona.data.FridgeEvent;
import cagreer.persona.data.Job;
import cagreer.persona.data.LectureEvent;
import cagreer.persona.data.LunchEvent;
import cagreer.persona.data.MechaModel;
import cagreer.persona.data.MiscellaneousActivity;
import cagreer.persona.data.PhoneEvent;
import cagreer.persona.data.Quest;
import cagreer.persona.data.SocialLinkEvent;
import cagreer.persona.data.SocialLinkPoints;
import cagreer.persona.data.SocialLinkStats;
import cagreer.persona.data.SocialQualityStats;
import cagreer.persona.data.StoryEvent;
import cagreer.persona.data.TanakasShopping;

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

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);

		Future<?> future = executor.submit(new Runnable() {
			@Override
			public void run() {
				Database db = new Database();

				try {
					parseAndInsertDinerData(db);
					parseAndInsertBookData(db);
					parseAndInsertAvailabilityData(db);
					parseAndInsertDailyData(db);
					parseAndInsertDungeonData(db);
					parseAndInsertExamData(db);
					parseAndInsertFridgeData(db);
					parseAndInsertJobData(db);
					parseAndInsertLectureData(db);
					parseAndInsertLunchData(db);
					parseAndInsertMechaData(db);
					parseAndInsertMiscellaneousData(db);
					parseAndInsertPhoneData(db);
					parseAndInsertQuestData(db);
					parseAndInsertSocialLinkEventData(db);
					parseAndInsertSocialLinkPointsData(db);
					parseAndInsertSocialLinkStatsData(db);
					parseAndInsertSocialQualityData(db);
					parseAndInsertStoryEvent(db);
					parseAndInsertTanakaShopping(db);
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
						e.printStackTrace();
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

	private static Date parseSQLDate(String date) {
		java.text.SimpleDateFormat format = null;
		java.util.Date utilDate = null;
		Date sqlDate = null;
		try {
			date = date.trim();
			format = new java.text.SimpleDateFormat("yyyy-MM-dd");
			utilDate = format.parse(date);
			sqlDate = new Date(utilDate.getTime());
			return sqlDate;
		} catch (ParseException e) {
			return sqlDate;
		}
	}

	private static Integer parseInt(String integer) {
		try {
			return Integer.parseInt(integer);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private static String parseString(String str) {
		str = str.trim();
		if (!str.equals("")) {
			return str;
		} else {
			return null;
		}
	}

	private static void parseAndInsertDinerData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_AiyaChineseDiner.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] aiyaData = line.split("\\|", -1);
			String day = parseString(aiyaData[0]);
			String socialQualityModifier = parseString(aiyaData[1]);
			Integer visits = parseInt(aiyaData[2]);
			Boolean isAfterSchoolActivity = Boolean.parseBoolean(aiyaData[3]);
			Boolean isEveningActivity = Boolean.parseBoolean(aiyaData[4]);

			db.insertIntoAiyaChineseDinerMenuTable(new AiyaChineseDiner.Builder() //
					.setDay(day) //
					.setSocialQualityModifier(socialQualityModifier) //
					.setNumberOfVisit(visits) //
					.setAfterSchoolActivity(isAfterSchoolActivity) //
					.setEveningActivity(isEveningActivity) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertBookData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_Books.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] bookData = line.split("\\|", -1);
			String name = parseString(bookData[0]);
			Date releaseDate = parseSQLDate(bookData[1]);
			String socialQualityModifier = parseString(bookData[2]);
			Integer readSections = parseInt(bookData[3]);
			Integer totalSections = parseInt(bookData[4]);
			Boolean isObtained = Boolean.parseBoolean(bookData[5]);
			Boolean isAfterSchoolActivity = Boolean.parseBoolean(bookData[6]);
			Boolean isEveningActivity = Boolean.parseBoolean(bookData[7]);

			db.insertIntoBook(new Book.Builder() //
					.setName(name) //
					.setReleaseDate(releaseDate) //
					.setSocialQualityModifier(socialQualityModifier) //
					.setReadChapters(readSections) //
					.setTotalChapters(totalSections) //
					.setObtained(isObtained) //
					.setAfterSchoolActivity(isAfterSchoolActivity) //
					.setEveningActivity(isEveningActivity) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertAvailabilityData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_SocialLinkAvailability.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] dailyAvailabilityData = line.split("\\|", -1);
			Date date = parseSQLDate(dailyAvailabilityData[0]);
			String magician = parseString(dailyAvailabilityData[1]);
			String chariot = parseString(dailyAvailabilityData[2]);
			String priestess = parseString(dailyAvailabilityData[3]);
			String emperor = parseString(dailyAvailabilityData[4]);
			String lovers = parseString(dailyAvailabilityData[5]);
			String fortune = parseString(dailyAvailabilityData[6]);
			String strength = parseString(dailyAvailabilityData[7]);
			String sun = parseString(dailyAvailabilityData[8]);
			String moon = parseString(dailyAvailabilityData[9]);
			String hangedMan = parseString(dailyAvailabilityData[10]);
			String death = parseString(dailyAvailabilityData[11]);
			String temperance = parseString(dailyAvailabilityData[12]);
			String hermit = parseString(dailyAvailabilityData[13]);
			String empress = parseString(dailyAvailabilityData[14]);
			String hierophant = parseString(dailyAvailabilityData[15]);
			String justice = parseString(dailyAvailabilityData[16]);
			String devil = parseString(dailyAvailabilityData[17]);
			String tower = parseString(dailyAvailabilityData[18]);

			db.insertIntoDailyAvailability(new SocialLinkAvailability.Builder() //
					.setDate(date) //
					.setMagicianAvailability(magician) //
					.setChariotAvailability(chariot) //
					.setPriestessAvailability(priestess) //
					.setEmperorAvailability(emperor) //
					.setLoversAvailability(lovers) //
					.setFortuneAvailability(fortune) //
					.setStrengthAvailability(strength) //
					.setSunAvailability(sun) //
					.setMoonAvailability(moon) //
					.setHangedManAvailability(hangedMan) //
					.setDeathAvailability(death) //
					.setTemperanceAvailability(temperance) //
					.setHermitAvailability(hermit) //
					.setEmpressAvailability(empress) //
					.setHierophantAvailability(hierophant) //
					.setJusticeAvailability(justice) //
					.setDevilAvailability(devil) //
					.setTowerAvailability(tower) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertDailyData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_DailyData.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] dailyData = line.split("\\|", -1);
			Date date = parseSQLDate(dailyData[0]);
			String day = parseString(dailyData[1]);
			String morningWeather = parseString(dailyData[2]);
			String eveningWeather = parseString(dailyData[3]);
			String afterSchoolFreeTime = parseString(dailyData[4]);
			String eveningFreeTime = parseString(dailyData[5]);
			Boolean isSchoolDay = Boolean.parseBoolean(dailyData[6]);

			db.insertIntoDailyData(new DailyData.Builder() //
					.setDate(date) //
					.setDay(day) //
					.setMorningWeather(morningWeather) //
					.setEveningWeather(eveningWeather) //
					.setAfterSchoolFreeTime(afterSchoolFreeTime) //
					.setEveningFreeTime(eveningFreeTime) //
					.setSchoolDay(isSchoolDay) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertDungeonData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_DungeonEvents.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] dungeonData = line.split("\\|", -1);
			String name = parseString(dungeonData[0]);
			String keyResponse = parseString(dungeonData[1]);
			String socialQualityModifier = parseString(dungeonData[2]);
			String autoSocialLinkLevel = parseString(dungeonData[3]);
			String socialLinkModifier = parseString(dungeonData[4]);
			String optionalBossReward = parseString(dungeonData[5]);
			Boolean isStoryBossDefeated = Boolean.parseBoolean(dungeonData[6]);
			Boolean isOptionalBossDefeated = Boolean.parseBoolean(dungeonData[7]);

			db.insertIntoDungeon(new Dungeon.Builder() //
					.setName(name) //
					.setKeyResponse(keyResponse) //
					.setSocialQualityModifier(socialQualityModifier) //
					.setAutoSocialLinkLevel(autoSocialLinkLevel) //
					.setSocialLinkModifier(socialLinkModifier) //
					.setOptionalBossReward(optionalBossReward) //
					.setStoryBossDefeated(isStoryBossDefeated) //
					.setOptionalBossDefeated(isOptionalBossDefeated) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertExamData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_Exams.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] examData = line.split("\\|", -1);
			Date startDate = parseSQLDate(examData[0]);
			Date endDate = parseSQLDate(examData[1]);
			String exam = parseString(examData[2]);
			String neededSocialQualityLevelForTop10 = parseString(examData[3]);
			String top10SocialLinkRewards = parseString(examData[4]);
			String neededSocialQualityLevelForTop = parseString(examData[5]);
			String topSocialLinkRewards = parseString(examData[6]);

			db.insertIntoExam(new Exam.Builder() //
					.setStartDate(startDate) //
					.setEndDate(endDate) //
					.setExam(exam) //
					.setRequiredSocialQualityLevelForTop10(neededSocialQualityLevelForTop10) //
					.setSocialLinkRewardsForTop10(top10SocialLinkRewards) //
					.setRequiredSocialQualityLevelForAce(neededSocialQualityLevelForTop) //
					.setSocialLinkRewardsForAce(topSocialLinkRewards) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertFridgeData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_FridgeEvents.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] fridgeData = line.split("\\|", -1);
			Date date = parseSQLDate(fridgeData[0]);
			String meal = parseString(fridgeData[1]);
			String socialQualityModifier = parseString(fridgeData[2]);
			Boolean isAfterSchoolActivity = Boolean.parseBoolean(fridgeData[3]);
			Boolean isEveningActivity = Boolean.parseBoolean(fridgeData[4]);

			db.insertIntoFridgeEvent(new FridgeEvent.Builder() //
					.setDate(date) //
					.setMeal(meal) //
					.setSocialQualityModifier(socialQualityModifier) //
					.setAfterSchoolActivity(isAfterSchoolActivity) //
					.setEveningActivity(isEveningActivity) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertJobData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_Jobs.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] jobData = line.split("\\|", -1);
			String name = parseString(jobData[0]);
			Date startDate = parseSQLDate(jobData[1]);
			String socialQualityRequirement = parseString(jobData[2]);
			String socialQualityModifier = parseString(jobData[3]);
			String socialLink = parseString(jobData[4]);
			Integer visits = parseInt(jobData[5]);
			Boolean isDuringRain = Boolean.parseBoolean(jobData[6]);
			Boolean isAfterSchoolActivity = Boolean.parseBoolean(jobData[7]);
			Boolean isEveningActivity = Boolean.parseBoolean(jobData[8]);

			db.insertIntoJob(new Job.Builder() //
					.setName(name) //
					.setStartDate(startDate) //
					.setSocialQualityRequirement(socialQualityRequirement) //
					.setSocialQualityModifier(socialQualityModifier) //
					.setSocialLink(socialLink) //
					.setVisits(visits) //
					.setDuringRain(isDuringRain) //
					.setAfterSchoolActivity(isAfterSchoolActivity) //
					.setEveningActivity(isEveningActivity) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertLectureData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_LectureEvents.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] lectureData = line.split("\\|", -1);
			Date date = parseSQLDate(lectureData[0]);
			String answer = parseString(lectureData[1]);
			String socialQualityModifier = parseString(lectureData[2]);
			String socialLinkModifier = parseString(lectureData[3]);

			db.insertIntoLectureEvent(new LectureEvent.Builder() //
					.setDate(date) //
					.setAnswer(answer) //
					.setSocialQualityModifier(socialQualityModifier) //
					.setSocialLinkModifier(socialLinkModifier) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertLunchData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_LunchEvents.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] lunchData = line.split("\\|", -1);
			Date date = parseSQLDate(lunchData[0]);
			String meal = parseString(lunchData[1]);
			String methodology = parseString(lunchData[2]);
			String socialLinksFavorite = parseString(lunchData[3]);
			Boolean isAfterSchoolActivity = Boolean.parseBoolean(lunchData[4]);
			Boolean isEveningActivity = Boolean.parseBoolean(lunchData[5]);

			db.insertIntoLunchEvent(new LunchEvent.Builder() //
					.setDate(date) //
					.setMeal(meal) //
					.setMethodology(methodology) //
					.setSocialLinksFavorite(socialLinksFavorite) //
					.setAfterSchoolActivity(isAfterSchoolActivity) //
					.setEveningActivity(isEveningActivity) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertMechaData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_MechaModels.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] mechaData = line.split("\\|", -1);
			String model = parseString(mechaData[0]);
			Integer currentProgress = parseInt(mechaData[1]);
			Integer totalProgress = parseInt(mechaData[2]);
			Integer quest = parseInt(mechaData[3]);
			String requiredQuestStatus = parseString(mechaData[4]);

			db.insertIntoMechaModel(new MechaModel.Builder() //
					.setModel(model) //
					.setCurrentProgress(currentProgress) //
					.setTotalProgress(totalProgress) //
					.setQuest(quest) //
					.setRequiredQuestStatus(requiredQuestStatus) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertMiscellaneousData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_MiscActivities.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] miscData = line.split("\\|", -1);
			String activity = parseString(miscData[0]);
			Boolean isAfterSchoolActivity = Boolean.parseBoolean(miscData[1]);
			Boolean isEveningActivity = Boolean.parseBoolean(miscData[2]);
			Boolean isRainRequired = Boolean.parseBoolean(miscData[3]);
			Integer requiredQuestNumber = parseInt(miscData[4]);
			String socialQualityModifier = parseString(miscData[5]);
			Integer visits = parseInt(miscData[6]);

			db.insertIntoMiscellaneous(new MiscellaneousActivity.Builder() //
					.setActivity(activity) //
					.setAfterSchoolActivity(isAfterSchoolActivity) //
					.setEveningSchoolActivity(isEveningActivity) //
					.setRainRequired(isRainRequired) //
					.setRequiredQuestNumber(requiredQuestNumber) //
					.setSocialQualityModifier(socialQualityModifier) //
					.setVisits(visits) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertPhoneData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_PhoneEvents.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] phoneData = line.split("\\|", -1);
			Date date = parseSQLDate(phoneData[0]);
			String initiatingSocialLink = parseString(phoneData[1]);
			String involvedSocialLink = parseString(phoneData[2]);
			String book = parseString(phoneData[3]);
			String keyResponses = parseString(phoneData[4]);
			String status = parseString(phoneData[5]);

			db.insertIntoPhoneEvent(new PhoneEvent.Builder() //
					.setDate(date) //
					.setInitiatingSocialLink(initiatingSocialLink) //
					.setInvolvedSocialLink(involvedSocialLink) //
					.setBook(book) //
					.setKeyResponses(keyResponses) //
					.setStatus(status) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertQuestData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_Quest.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] questData = line.split("\\|", -1);
			Integer number = parseInt(questData[0]);
			String name = parseString(questData[1]);
			String questGiver = parseString(questData[2]);
			String startLocation = parseString(questData[3]);
			Date startDate = parseSQLDate(questData[4]);
			Date endDate = parseSQLDate(questData[5]);
			String previousQuestNumber = parseString(questData[6]);
			String socialQualities = parseString(questData[7]);
			String reward = parseString(questData[8]);
			Integer neededAfterSchoolSlots = parseInt(questData[9]);
			Integer neededEveningSlots = parseInt(questData[10]);
			Integer daysSpentOnQuest = parseInt(questData[11]);
			Integer daysNeededToComplete = parseInt(questData[12]);
			String status = parseString(questData[13]);
			String instructions = parseString(questData[14]);

			db.insertIntoQuest(new Quest.Builder() //
					.setNumber(number) //
					.setName(name) //
					.setQuestGiver(questGiver) //
					.setStartLocation(startLocation) //
					.setStartDate(startDate) //
					.setEndDate(endDate) //
					.setPreviousQuest(previousQuestNumber) //
					.setRequiredSocialQualities(socialQualities) //
					.setReward(reward) //
					.setNeededAfterSchoolSlot(neededAfterSchoolSlots) //
					.setNeededEveningSlots(neededEveningSlots) //
					.setDaysSpentOnQuest(daysSpentOnQuest) //
					.setDaysNeededToComplete(daysNeededToComplete) //
					.setStatus(status) //
					.setInstructions(instructions) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertSocialLinkEventData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_SocialLinkEvents.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] socialLinkEventData = line.split("\\|", -1);
			String socialLink = parseString(socialLinkEventData[0]);
			String rank1Answers = parseString(socialLinkEventData[1]);
			String rank2Answers = parseString(socialLinkEventData[2]);
			String rank3Answers = parseString(socialLinkEventData[3]);
			String rank4Answers = parseString(socialLinkEventData[4]);
			String rank5Answers = parseString(socialLinkEventData[5]);
			String rank6Answers = parseString(socialLinkEventData[6]);
			String rank7Answers = parseString(socialLinkEventData[7]);
			String rank8Answers = parseString(socialLinkEventData[8]);
			String rank9Answers = parseString(socialLinkEventData[9]);
			String rank10Answers = parseString(socialLinkEventData[10]);

			db.insertIntoSocialLinkEvent(new SocialLinkEvent.Builder() //
					.setSocialLink(socialLink) //
					.setRank1Answers(rank1Answers) //
					.setRank2Answers(rank2Answers) //
					.setRank3Answers(rank3Answers) //
					.setRank4Answers(rank4Answers) //
					.setRank5Answers(rank5Answers) //
					.setRank6Answers(rank6Answers) //
					.setRank7Answers(rank7Answers) //
					.setRank8Answers(rank8Answers) //
					.setRank9Answers(rank9Answers) //
					.setRank10Answers(rank10Answers) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertSocialLinkPointsData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_SocialLinkPoints.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] socialLinkPointsData = line.split("\\|", -1);
			String socialLink = parseString(socialLinkPointsData[0]);
			Integer rank1Points = parseInt(socialLinkPointsData[1]);
			Integer rank2Points = parseInt(socialLinkPointsData[2]);
			Integer rank3Points = parseInt(socialLinkPointsData[3]);
			Integer rank4Points = parseInt(socialLinkPointsData[4]);
			Integer rank5Points = parseInt(socialLinkPointsData[5]);
			Integer rank6Points = parseInt(socialLinkPointsData[6]);
			Integer rank7Points = parseInt(socialLinkPointsData[7]);
			Integer rank8Points = parseInt(socialLinkPointsData[8]);
			Integer rank9Points = parseInt(socialLinkPointsData[9]);
			Integer rank10Points = parseInt(socialLinkPointsData[10]);

			db.insertIntoSocialLinkPoints(new SocialLinkPoints.Builder() //
					.setSocialLink(socialLink) //
					.setRank1Points(rank1Points) //
					.setRank2Points(rank2Points) //
					.setRank3Points(rank3Points) //
					.setRank4Points(rank4Points) //
					.setRank5Points(rank5Points) //
					.setRank6Points(rank6Points) //
					.setRank7Points(rank7Points) //
					.setRank8Points(rank8Points) //
					.setRank9Points(rank9Points) //
					.setRank10Points(rank10Points) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertSocialLinkStatsData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_SocialLinkStats.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] socialLinkStatsData = line.split("\\|", -1);
			String arcanaNumber = parseString(socialLinkStatsData[0]);
			String socialLink = parseString(socialLinkStatsData[1]);
			Boolean isAfterSchoolAvailable = Boolean.parseBoolean(socialLinkStatsData[2]);
			Boolean isEveningAvailable = Boolean.parseBoolean(socialLinkStatsData[3]);
			String requiredSocialQuality = parseString(socialLinkStatsData[4]);
			String requiredSocialLinkRanks = parseString(socialLinkStatsData[5]);
			Integer remainingFlags = parseInt(socialLinkStatsData[6]);
			Integer currentRank = parseInt(socialLinkStatsData[7]);
			Integer currentPoints = parseInt(socialLinkStatsData[8]);

			db.insertIntoSocialLinkStats(new SocialLinkStats.Builder() //
					.setArcanaNumber(arcanaNumber) //
					.setSocialLink(socialLink) //
					.setAfterSchoolAvailable(isAfterSchoolAvailable) //
					.setEveningAvailable(isEveningAvailable) //
					.setRequiredSocialQuality(requiredSocialQuality) //
					.setRequiredSocialLinkRanks(requiredSocialLinkRanks) //
					.setRemainingFlags(remainingFlags) //
					.setCurrentRank(currentRank) //
					.setCurrentPoints(currentPoints) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertSocialQualityData(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_SocialLinkPoints.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] socialQualityData = line.split("\\|", -1);
			String socialQuality = parseString(socialQualityData[0]);
			Integer level1Points = parseInt(socialQualityData[1]);
			Integer level2Points = parseInt(socialQualityData[2]);
			Integer level3Points = parseInt(socialQualityData[3]);
			Integer level4Points = parseInt(socialQualityData[4]);
			Integer level5Points = parseInt(socialQualityData[5]);
			Integer currentPoints = parseInt(socialQualityData[6]);
			Integer currentLevel = parseInt(socialQualityData[7]);

			db.insertIntoSocialQualityStats(new SocialQualityStats.Builder() //
					.setSocialQuality(socialQuality) //
					.setLevel1Points(level1Points) //
					.setLevel2Points(level2Points) //
					.setLevel3Points(level3Points) //
					.setLevel4Points(level4Points) //
					.setLevel5Points(level5Points) //
					.setCurrentPoints(currentPoints) //
					.setCurrentLevel(currentLevel) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertStoryEvent(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_StoryEvents.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] storyEventData = line.split("\\|", -1);
			Date date = parseSQLDate(storyEventData[0]);
			String name = parseString(storyEventData[1]);
			String keyResponses = parseString(storyEventData[2]);
			String socialLinkAutoLevel = parseString(storyEventData[3]);
			String socialLinkModifier = parseString(storyEventData[4]);
			String socialQualityModifier = parseString(storyEventData[5]);

			db.insertIntoStoryEvent(new StoryEvent.Builder() //
					.setDate(date) //
					.setName(name) //
					.setKeyResponses(keyResponses) //
					.setSocialLinkAutoLevel(socialLinkAutoLevel) //
					.setSocialLinkModifier(socialLinkModifier) //
					.setSocialQualityModifier(socialQualityModifier) //
					.build());
		}
		br.close();
	}

	private static void parseAndInsertTanakaShopping(Database db) throws IOException {
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader("data/P4_TanakaShopping.csv"));
		line = br.readLine();
		while ((line = br.readLine()) != null) {
			String[] tanakaShopping = line.split("\\|", -1);
			Date date = parseSQLDate(tanakaShopping[0]);
			String offer = parseString(tanakaShopping[1]);
			String price = parseString(tanakaShopping[2]);

			db.insertIntoTanakasShopping(new TanakasShopping.Builder() //
					.setDate(date) //
					.setOffer(offer) //
					.setPrice(price) //
					.build());
		}
		br.close();
	}
}
