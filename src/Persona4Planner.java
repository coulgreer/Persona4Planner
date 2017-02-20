import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Persona4Planner {
	JPanel cards;

	public void addComponentToPane(Container pane) {
		JPanel navigationPanel = new JPanel();
		JButton statusButton, calendarButton;
		statusButton = new JButton("Status Screen");
		statusButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.show(cards, e.getActionCommand());
			}
		});

		calendarButton = new JButton("Calendar Screen");
		calendarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) cards.getLayout();
				cardLayout.show(cards, e.getActionCommand());
			}
		});

		navigationPanel.setBackground(new Color(255, 174, 32));
		navigationPanel.add(statusButton);
		navigationPanel.add(calendarButton);

		JPanel statusCard = new JPanel();
		statusCard.setBackground(new Color(254, 234, 44));

		JPanel calendarCard = new JPanel();
		calendarCard.setBackground(Color.PINK);

		cards = new JPanel(new CardLayout());
		cards.add(statusCard, "Status Screen");
		cards.add(calendarCard, "Calendar Screen");

		pane.add(navigationPanel, BorderLayout.PAGE_START);
		pane.add(cards, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Persona 4 Planner");
		frame.setSize(800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Persona4Planner p4p = new Persona4Planner();
		p4p.addComponentToPane(frame.getContentPane());

		frame.setVisible(true);
	}

}
