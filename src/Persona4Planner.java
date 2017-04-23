import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
	private final static String DATABASE_NAME = "Persona4_Schedule.db";
	private final static String ARCANA_TABLE_NAME = "ArcanaAvailability";

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

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
				Persona4Planner p4p = new Persona4Planner();
				p4p.createDatabaseTables();
				String line = null;
				try {
					BufferedReader br = new BufferedReader(new FileReader("documents/Arcana-Yearly-Schedule"));
					line = br.readLine();
					while ((line = br.readLine()) != null) {
						String[] dailyData = line.split("\t");
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

						p4p.insertToAvailability(new ArcanaTable()//
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

			}
		});

	}

	private static void createGUI() {
		JFrame frame = new JFrame("Persona 4 Planner");
		frame.setSize(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		Persona4Planner p4p = new Persona4Planner();
		p4p.addComponentToPane(frame.getContentPane());

		frame.setVisible(true);
	}

	public void createDatabaseTables() {
		String sql = "CREATE TABLE IF NOT EXISTS " + ARCANA_TABLE_NAME + " (" //
				+ "	Date nchar(6) PRIMARY KEY," //
				+ "	Day nchar(3) NOT NULL," //
				+ "	Weather varchar(3) NOT NULL," //
				+ "	Afternoon nchar(1) NOT NULL," //
				+ "	Night nchar(1) NOT NULL," //
				+ "	Mag nchar(3)," //
				+ "	Cha nchar(3)," //
				+ "	Pri nchar(3)," //
				+ "	Epr nchar(3)," //
				+ "	Lov nchar(3)," //
				+ "	For nchar(3)," //
				+ "	Str nchar(3)," //
				+ "	Sun nchar(3)," //
				+ "	Mon nchar(3)," //
				+ "	Hng nchar(3)," //
				+ "	Dea nchar(3)," //
				+ "	Tem nchar(3)," //
				+ "	Her nchar(3)," //
				+ "	Eps nchar(3)," //
				+ "	Hie nchar(3)," //
				+ "	Jus nchar(3)," //
				+ "	Dev nchar(3)," //
				+ "	Tow nchar(3));"; //

		try (Connection conn = this.connect(DATABASE_NAME); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertToAvailability(ArcanaTable table) {

		String sql = "INSERT INTO " + ARCANA_TABLE_NAME //
				+ "(Date, Day, Weather, Afternoon, Night, Mag, Cha, Pri, Epr, Lov, For, Str, Sun, Mon, Hng, Dea, Tem, Her, Eps, Hie, Jus, Dev, Tow)" //
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = this.connect(DATABASE_NAME); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, table.date());
			pstmt.setString(2, table.day());
			pstmt.setString(3, table.weather());
			pstmt.setString(4, table.afternoon());
			pstmt.setString(5, table.night());
			pstmt.setString(6, table.magician());
			pstmt.setString(7, table.chariot());
			pstmt.setString(8, table.priestess());
			pstmt.setString(9, table.emperor());
			pstmt.setString(10, table.lovers());
			pstmt.setString(11, table.fortune());
			pstmt.setString(12, table.strength());
			pstmt.setString(13, table.sun());
			pstmt.setString(14, table.moon());
			pstmt.setString(15, table.hangedMan());
			pstmt.setString(16, table.death());
			pstmt.setString(17, table.temperance());
			pstmt.setString(18, table.hermit());
			pstmt.setString(19, table.empress());
			pstmt.setString(20, table.hierophant());
			pstmt.setString(21, table.justice());
			pstmt.setString(22, table.devil());
			pstmt.setString(23, table.tower());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Connection connect(String fileName) {
		String url = "jdbc:sqlite:./database/" + fileName;

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static class ArcanaTable {
		private String date, day, weather, afternoon, night;
		private String magician, chariot, priestess, emperor, lovers, fortune, strength, sun, moon, hangedMan, death,
				temperance, hermit, empress, hierophant, justice, devil, tower;

		public ArcanaTable withDateOf(String date) {
			this.date = date;
			return this;
		}

		public ArcanaTable withDayOf(String day) {
			this.day = day;
			return this;
		}

		public ArcanaTable withWeatherOf(String weather) {
			this.weather = weather;
			return this;
		}

		public ArcanaTable withAfternoon(String afternoon) {
			this.afternoon = afternoon;
			return this;
		}

		public ArcanaTable withNight(String night) {
			this.night = night;
			return this;
		}

		public ArcanaTable withMagicianAvailability(String magician) {
			this.magician = magician;
			return this;
		}

		public ArcanaTable withChariotAvailability(String chariot) {
			this.chariot = chariot;
			return this;
		}

		public ArcanaTable withPriestessAvailability(String priestess) {
			this.priestess = priestess;
			return this;
		}

		public ArcanaTable withEmperorAvailability(String emperor) {
			this.emperor = emperor;
			return this;
		}

		public ArcanaTable withLoversAvailability(String lovers) {
			this.lovers = lovers;
			return this;
		}

		public ArcanaTable withFortuneAvailability(String fortune) {
			this.fortune = fortune;
			return this;
		}

		public ArcanaTable withStrengthAvailability(String strength) {
			this.strength = strength;
			return this;
		}

		public ArcanaTable withSunAvailability(String sun) {
			this.sun = sun;
			return this;
		}

		public ArcanaTable withMoonAvailability(String moon) {
			this.moon = moon;
			return this;
		}

		public ArcanaTable withHangedManAvailability(String hangedMan) {
			this.hangedMan = hangedMan;
			return this;
		}

		public ArcanaTable withDeathAvailability(String death) {
			this.death = death;
			return this;
		}

		public ArcanaTable withTemperanceAvailability(String temperance) {
			this.temperance = temperance;
			return this;
		}

		public ArcanaTable withHermitAvailability(String hermit) {
			this.hermit = hermit;
			return this;
		}

		public ArcanaTable withEmpressAvailability(String empress) {
			this.empress = empress;
			return this;
		}

		public ArcanaTable withHierophantAvailability(String hierophant) {
			this.hierophant = hierophant;
			return this;
		}

		public ArcanaTable withJusticeAvailability(String justice) {
			this.justice = justice;
			return this;
		}

		public ArcanaTable withDevilAvailability(String devil) {
			this.devil = devil;
			return this;
		}

		public ArcanaTable withTowerAvailability(String tower) {
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

}
