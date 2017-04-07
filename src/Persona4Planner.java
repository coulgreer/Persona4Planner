import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
		radarChart = new RadarChart.RadarChartBuilder() //
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
				JFrame frame = new JFrame("Persona 4 Planner");
				frame.setSize(DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setResizable(false);

				Persona4Planner p4p = new Persona4Planner();
				p4p.addComponentToPane(frame.getContentPane());

				frame.setVisible(true);
			}
		});

	}

}
