import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Persona4Database {
	private final static String DATABASE_NAME = "Persona4_Schedule.db";
	private final static String ARCANA_TABLE_NAME = "ArcanaAvailability";
	private final static String QUEST_TABLE_NAME = "Quest";
	private final static String ARCANA_CONVERSATION_TABLE_NAME = "ArcanaConversation";
	private final static String LECTURE_ANSWERS_TABLE_NAME = "LectureAnswers";

	public Persona4Database() {
		createArcanaTable();
		createQuestTable();
		createArcanaConversationTable();
		createLectureAnswerTable();
	}

	private void createArcanaTable() {
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
				+ "	Tow nchar(3));";

		try (Connection conn = this.connect(DATABASE_NAME); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createQuestTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + QUEST_TABLE_NAME + " (" //
				+ " QuestName varchar(50) NOT NULL," //
				+ " QuestNumber varchar(2) PRIMARY KEY," //
				+ " QuestGiver varchar(20) NOT NULL," //
				+ " TimeFrame varchar(15) NOT NULL," //
				+ " Reward varchar(25)," //
				+ " Remarks varchar(255));";

		try (Connection conn = this.connect(DATABASE_NAME); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createArcanaConversationTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + ARCANA_CONVERSATION_TABLE_NAME + " (" //
				+ " Arcana varchar(25) PRIMARY KEY," //
				+ " Rank1Answers varchar(255) NOT NULL," //
				+ " Rank2Answers varchar(255) NOT NULL," //
				+ " Rank3Answers varchar(255) NOT NULL," //
				+ " Rank4Answers varchar(255) NOT NULL," //
				+ " Rank5Answers varchar(255) NOT NULL," //
				+ " Rank6Answers varchar(255) NOT NULL," //
				+ " Rank7Answers varchar(255) NOT NULL," //
				+ " Rank8Answers varchar(255) NOT NULL," //
				+ " Rank9Answers varchar(255) NOT NULL," //
				+ " Rank10Answers varchar(255) NOT NULL," //
				+ " Remarks varchar(255) NOT NULL);";

		try (Connection conn = this.connect(DATABASE_NAME); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createLectureAnswerTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + LECTURE_ANSWERS_TABLE_NAME + " (" //
				+ " Date varchar(5) PRIMARY KEY," //
				+ " Answer varchar(255) NOT NULL," //
				+ " SocialQualityModifiers varchar(15)," //
				+ " SocialLinkModifiers varchar(15));";

		try (Connection conn = this.connect(DATABASE_NAME); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertToAvailability(Persona4Planner.ArcanaAvailabilityData data) {
		String sql = "INSERT INTO " + ARCANA_TABLE_NAME //
				+ "(Date, Day, Weather, Afternoon, Night, Mag, Cha, Pri, Epr, Lov, For, Str, Sun, Mon, Hng, Dea, Tem, Her, Eps, Hie, Jus, Dev, Tow)" //
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try (Connection conn = this.connect(DATABASE_NAME); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.date());
			pstmt.setString(2, data.day());
			pstmt.setString(3, data.weather());
			pstmt.setString(4, data.afternoon());
			pstmt.setString(5, data.night());
			pstmt.setString(6, data.magician());
			pstmt.setString(7, data.chariot());
			pstmt.setString(8, data.priestess());
			pstmt.setString(9, data.emperor());
			pstmt.setString(10, data.lovers());
			pstmt.setString(11, data.fortune());
			pstmt.setString(12, data.strength());
			pstmt.setString(13, data.sun());
			pstmt.setString(14, data.moon());
			pstmt.setString(15, data.hangedMan());
			pstmt.setString(16, data.death());
			pstmt.setString(17, data.temperance());
			pstmt.setString(18, data.hermit());
			pstmt.setString(19, data.empress());
			pstmt.setString(20, data.hierophant());
			pstmt.setString(21, data.justice());
			pstmt.setString(22, data.devil());
			pstmt.setString(23, data.tower());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertToQuests(Persona4Planner.QuestData data) {
		String sql = "INSERT INTO " + QUEST_TABLE_NAME //
				+ "(QuestName, QuestNumber, QuestGiver, TimeFrame, Reward, Remarks)" //
				+ "VALUES (?, ?, ?, ?, ?, ?);";

		try (Connection conn = this.connect(DATABASE_NAME); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.questName());
			pstmt.setString(2, data.questNumber());
			pstmt.setString(3, data.questGiver());
			pstmt.setString(4, data.questTimeFrame());
			pstmt.setString(5, data.reward());
			pstmt.setString(6, data.remarks());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertToConversationAnswers(Persona4Planner.ArcanaConversationData data) {
		String sql = "INSERT INTO " + ARCANA_CONVERSATION_TABLE_NAME //
				+ "(Arcana, Rank1Answers, Rank2Answers, Rank3Answers, Rank4Answers, Rank5Answers, Rank6Answers, Rank7Answers, Rank8Answers, Rank9Answers, Rank10Answers, Remarks)" //
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try (Connection conn = this.connect(DATABASE_NAME); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.arcana());
			pstmt.setString(2, data.rank1Answers());
			pstmt.setString(3, data.rank2Answers());
			pstmt.setString(4, data.rank3Answers());
			pstmt.setString(5, data.rank4Answers());
			pstmt.setString(6, data.rank5Answers());
			pstmt.setString(7, data.rank6Answers());
			pstmt.setString(8, data.rank7Answers());
			pstmt.setString(9, data.rank8Answers());
			pstmt.setString(10, data.rank9Answers());
			pstmt.setString(11, data.rank10Answers());
			pstmt.setString(12, data.remarks());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertToLecture(Persona4Planner.LectureData data) {
		String sql = "INSERT INTO " + LECTURE_ANSWERS_TABLE_NAME //
				+ "(Date, Answer, SocialLinkModifier, SocialQualityModifier)"
				+ "VALUES (?, ?, ?, ?);";
		
		try(Connection conn = this.connect(DATABASE_NAME); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.date());
			pstmt.setString(2, data.answer());
			pstmt.setString(3, data.socialLinkModifier());
			pstmt.setString(4, data.socialQualityModifier());
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

}
