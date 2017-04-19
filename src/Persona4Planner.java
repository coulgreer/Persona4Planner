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
				Persona4Planner p4p = new Persona4Planner();
				createGUI();
				p4p.createDatabaseTables();
				p4p.insertToAvailability(new ArcanaTable() //
						.withDateOf("11-Apr") //
						.withDayOf("Mon") //
						.withWeatherOf("C/R") //
						.withAfternoon("N") //
						.withNight("R") //
						.hasEvent(true));
				p4p.insertToAvailability(new ArcanaTable() //
						.withDateOf("12-Apr") //
						.withDayOf("Tue") //
						.withWeatherOf("R/C") //
						.withAfternoon("N") //
						.withNight("R") //
						.hasEvent(true));
				p4p.insertToAvailability(new ArcanaTable() //
						.withDateOf("13-Apr") //
						.withDayOf("Wed") //
						.withWeatherOf("C") //
						.withAfternoon("N") //
						.withNight("R") //
						.hasEvent(true));
				p4p.insertToAvailability(new ArcanaTable() //
						.withDateOf("14-Apr") //
						.withDayOf("Thu") //
						.withWeatherOf("R") //
						.withAfternoon("N") //
						.withNight("R") //
						.hasEvent(true));
				p4p.insertToAvailability(new ArcanaTable() //
						.withDateOf("15-Apr") //
						.withDayOf("Fri") //
						.withWeatherOf("R") //
						.withAfternoon("N") //
						.withNight("R") //
						.hasEvent(true));
				p4p.insertToAvailability(new ArcanaTable() //
						.withDateOf("16-Apr") //
						.withDayOf("Sat") //
						.withWeatherOf("R") //
						.withAfternoon("N") //
						.withNight("R") //
						.withMag("I") //
						.withCha("N") //
						.withPri("N") //
						.withEpr("N") //
						.withLov("N") //
						.withFort("N") //
						.withStr("N") //
						.withSun("N") //
						.withMon("N") //
						.withHng("N") //
						.withDea("N") //
						.withTem("N") //
						.withHer("N") //
						.withEps("N") //
						.withHie("N") //
						.withJus("N") //
						.withDev("N") //
						.withTow("N"));
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
				+ "	Mag nchar(1)," //
				+ "	Cha nchar(1)," //
				+ "	Pri nchar(1)," //
				+ "	Epr nchar(1)," //
				+ "	Lov nchar(1)," //
				+ "	For nchar(1)," //
				+ "	Str nchar(1)," //
				+ "	Sun nchar(1)," //
				+ "	Mon nchar(1)," //
				+ "	Hng nchar(1)," //
				+ "	Dea nchar(1)," //
				+ "	Tem nchar(1)," //
				+ "	Her nchar(1)," //
				+ "	Eps nchar(1)," //
				+ "	Hie nchar(1)," //
				+ "	Jus nchar(1)," //
				+ "	Dev nchar(1)," //
				+ "	Tow nchar(1)" //
				+ ");"; //

		try (Connection conn = this.connect("arcanaSchedule.db"); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertToAvailability(ArcanaTable table) {

		String sql = "INSERT INTO " + ARCANA_TABLE_NAME //
				+ "(Date, Day, Weather, Afternoon, Night, Mag, Cha, Pri, Epr, Lov, For, Str, Sun, Mon, Hng, Dea, Tem, Her, Eps, Hie, Jus, Dev, Tow)" //
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = this.connect("arcanaSchedule.db");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, table.date);
			pstmt.setString(2, table.day);
			pstmt.setString(3, table.weather);
			pstmt.setString(4, table.afternoon);
			pstmt.setString(5, table.night);
			if (table.event) {
				for (int x = 6; x <= 23; x++) {
					pstmt.setString(x, "E");
				}
			} else {
				pstmt.setString(6, table.magician);
				pstmt.setString(7, table.chariot);
				pstmt.setString(8, table.priestess);
				pstmt.setString(9, table.emperor);
				pstmt.setString(10, table.love);
				pstmt.setString(11, table.fortune);
				pstmt.setString(12, table.strength);
				pstmt.setString(13, table.sun);
				pstmt.setString(14, table.moon);
				pstmt.setString(15, table.hangedMan);
				pstmt.setString(16, table.death);
				pstmt.setString(17, table.temperance);
				pstmt.setString(18, table.hermit);
				pstmt.setString(19, table.empress);
				pstmt.setString(20, table.hierophant);
				pstmt.setString(21, table.justice);
				pstmt.setString(22, table.devil);
				pstmt.setString(23, table.tower);
			}
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
		private boolean event = false;
		private String date, day, weather, afternoon, night;
		private String magician, chariot, priestess, emperor, love, fortune, strength, sun, moon, hangedMan, death,
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

		public ArcanaTable withMag(String magician) {
			this.magician = magician;
			return this;
		}

		public ArcanaTable withCha(String chariot) {
			this.chariot = chariot;
			return this;
		}

		public ArcanaTable withPri(String priestess) {
			this.priestess = priestess;
			return this;
		}

		public ArcanaTable withEpr(String emperor) {
			this.emperor = emperor;
			return this;
		}

		public ArcanaTable withLov(String love) {
			this.love = love;
			return this;
		}

		public ArcanaTable withFort(String fortune) {
			this.fortune = fortune;
			return this;
		}

		public ArcanaTable withStr(String strength) {
			this.strength = strength;
			return this;
		}

		public ArcanaTable withSun(String sun) {
			this.sun = sun;
			return this;
		}

		public ArcanaTable withMon(String moon) {
			this.moon = moon;
			return this;
		}

		public ArcanaTable withHng(String hangedMan) {
			this.hangedMan = hangedMan;
			return this;
		}

		public ArcanaTable withDea(String death) {
			this.death = death;
			return this;
		}

		public ArcanaTable withTem(String temperance) {
			this.temperance = temperance;
			return this;
		}

		public ArcanaTable withHer(String hermit) {
			this.hermit = hermit;
			return this;
		}

		public ArcanaTable withEps(String empress) {
			this.empress = empress;
			return this;
		}

		public ArcanaTable withHie(String hierophant) {
			this.hierophant = hierophant;
			return this;
		}

		public ArcanaTable withJus(String justice) {
			this.justice = justice;
			return this;
		}

		public ArcanaTable withDev(String devil) {
			this.devil = devil;
			return this;
		}

		public ArcanaTable withTow(String tower) {
			this.tower = tower;
			return this;
		}

		public ArcanaTable hasEvent(boolean event) {
			this.event = event;
			return this;
		}

	}

}
