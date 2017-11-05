import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

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

public class Database {
	private final static String DATABASE = "Persona4_Schedule.db";
	private final static String AIYA_CHINESE_DINER_TABLE = "AiyaChineseDinerDailyMenu";
	private final static String BOOK_TABLE = "Book";
	private final static String DAILY_DATA_TABLE = "DailyData";
	private final static String DAILY_AVAILABILITY_TABLE = "DailyAvailability";
	private final static String DUNGEON_TABLE = "Dungeon";
	private final static String EXAM_TABLE = "Exam";
	private final static String FRIDGE_TABLE = "FridgeInventory";
	private final static String JOB_TABLE = "Job";
	private final static String LECTURE_EVENT_TABLE = "LectureEvent";
	private final static String LUNCH_EVENT_TABLE = "LunchDateEvent";
	private final static String MISCELLANEOUS_ACTIVITY_TABLE = "MiscellaneousActivity";
	private final static String MECHA_MODEL_TABLE = "MechaModel";
	private final static String PHONE_EVENT_TABLE = "PhoneDateEvent";
	private final static String QUEST_TABLE = "Quest";
	private final static String SOCIAL_LINK_EVENT_TABLE = "SocialLinkAnswerKey";
	private final static String SOCIAL_LINK_POINTS_TABLE = "SocialLinkPoints";
	private final static String SOCIAL_LINK_TABLE = "SocialLinkStats";
	private final static String SOCIAL_QUALITY_TABLE = "SocialQuality";
	private final static String STORY_EVENT_TABLE = "StoryEvent";
	private final static String TANAKAS_SHOPPING_TABLE = "TanakaShoppingCatelog";

	public Database() {
		createBookTable();
		createAiyaChineseDinerMenuTable();
		createDailyDataTable();
		createDungeonEventsTable();
		createExamTable();
		createFridgeTable();
		createJobTable();
		createLectureAnswerTable();
		createLunchEventTable();
		createMechaModelsTable();
		createMiscActivitiesTable();
		createPhoneEventTable();
		createQuestTable();
		createSocialLinkAvailabilityTable();
		createSocialLinkEventsTable();
		createSocialLinkPointsTable();
		createSocialLinkStatsTable();
		createSocialQualityStatsTable();
		createStoryEventTable();
		createTanakasShoppingTable();
	}

	public void insertIntoAiyaChineseDinerMenuTable(AiyaChineseDiner data) {
		String sql = "INSERT INTO " + AIYA_CHINESE_DINER_TABLE //
				+ " (`Day`, `SocialQualityModifier`, `Visits`, `IsAfterSchoolActivity`, `IsEveningActivity`)" //
				+ " VALUES (?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getDay());
			pstmt.setString(2, data.getSocialQualityModifier());
			pstmt.setInt(3, data.getVisits());
			pstmt.setBoolean(4, data.isAfterSchoolActivity());
			pstmt.setBoolean(5, data.isEveningSchoolActivity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoBook(Book data) {
		String sql = "INSERT INTO " + BOOK_TABLE //
				+ " (`Title`, `ReleaseDate`, `SocialQualityModifier`, `ReadChapters`, `TotalChapters`, `IsObtained`, `IsAfterSchoolActivity`, `IsEveningActivity`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getTitle());
			pstmt.setDate(2, data.getReleaseDate());
			pstmt.setString(3, data.getSocialQualityModifier());
			pstmt.setInt(4, data.getReadChapters());
			pstmt.setInt(5, data.getTotalChapters());
			pstmt.setBoolean(6, data.isObtained());
			pstmt.setBoolean(7, data.isAfterSchoolActivity());
			pstmt.setBoolean(8, data.isEveningActivity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoDailyAvailability(SocialLinkAvailability data) {
		String sql = "INSERT INTO " + DAILY_AVAILABILITY_TABLE //
				+ " (`Date`, `Magician`, `Chariot`, `Priestess`, `Emperor`, `Lovers`, `Fortune`, `Strength`, `Sun`, `Moon`, `HangedMan`, `Death`, `Temperance`, `Hermit`, `Empress`, `Hierophant`, `Justice`, `Devil`, `Tower`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getDate());
			pstmt.setString(2, data.getMagician());
			pstmt.setString(3, data.getChariot());
			pstmt.setString(4, data.getPriestess());
			pstmt.setString(5, data.getEmperor());
			pstmt.setString(6, data.getLovers());
			pstmt.setString(7, data.getFortune());
			pstmt.setString(8, data.getStrength());
			pstmt.setString(9, data.getSun());
			pstmt.setString(10, data.getMoon());
			pstmt.setString(11, data.getHangedMan());
			pstmt.setString(12, data.getDeath());
			pstmt.setString(13, data.getTemperance());
			pstmt.setString(14, data.getHermit());
			pstmt.setString(15, data.getEmpress());
			pstmt.setString(16, data.getHierophant());
			pstmt.setString(17, data.getJustice());
			pstmt.setString(18, data.getDevil());
			pstmt.setString(19, data.getTower());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoDailyData(DailyData data) {
		String sql = "INSERT INTO " + DAILY_DATA_TABLE //
				+ " (`Date`, `Day`, `MorningWeather`, `EveningWeather`, `AfterSchoolFreeTime`, `EveningFreeTime`, `IsSchoolDay`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getDate());
			pstmt.setString(2, data.getDay());
			pstmt.setString(3, data.getMorningWeather());
			pstmt.setString(4, data.getEveningWeather());
			pstmt.setString(5, data.getAfterSchoolFreeTime());
			pstmt.setString(6, data.getEveningFreeTime());
			pstmt.setBoolean(7, data.isSchoolDay());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoDungeon(Dungeon data) {
		String sql = "INSERT INTO " + DUNGEON_TABLE //
				+ " (`Name`, `KeyResponse`, `SocialQualityModifier`, `AutoSocialLinkModifier`, `SocialLinkModifier`, `OptionalBossReward`, `IsStoryBossDefeated`, `IsOptionalBossDefeated`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getKeyResponse());
			pstmt.setString(3, data.getSocialQualityModifier());
			pstmt.setString(4, data.getAutoSocialLinkLevel());
			pstmt.setString(5, data.getSocialLinkModifier());
			pstmt.setString(6, data.getOptionalBossReward());
			pstmt.setBoolean(7, data.isStoryBossDefeated());
			pstmt.setBoolean(8, data.isOptionalBossDefeated());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoExam(Exam data) {
		String sql = "INSERT INTO " + EXAM_TABLE //
				+ " (`StartDate`, `EndDate`, `Exam`, `RequiredSocialQualityLevelForTop10`, `SocialLinkRewardForTop10`, `RequiredSocialQualityLevelForAce`, `SocialLinkRewardForAce`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getStartDate());
			pstmt.setDate(2, data.getEndDate());
			pstmt.setString(3, data.getExam());
			pstmt.setString(4, data.getRequiredSocialQualityLevelForTop10());
			pstmt.setString(5, data.getSocialLinkRewardsForTop10());
			pstmt.setString(6, data.getRequiredSocialQualityLevelForAce());
			pstmt.setString(7, data.getSocialLinkRewardsForAce());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoFridgeEvent(FridgeEvent data) {
		String sql = "INSERT INTO " + FRIDGE_TABLE //
				+ " (`Date`, `Food`, `SocialQualityModifier`, `IsAfterSchoolActivity`, `IsEveningActivity`)" //
				+ " VALUES (?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getDate());
			pstmt.setString(2, data.getMeal());
			pstmt.setString(3, data.getSocialQualityModifier());
			pstmt.setBoolean(4, data.isAfterSchoolActivity());
			pstmt.setBoolean(5, data.isEveningActivity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoJob(Job data) {
		String sql = "INSERT INTO " + JOB_TABLE //
				+ " (`Name`, `StartDate`, `SocialQualityRequirement`, `SocialQualityModifier`, `SocialLink`, `Visits`, `IsDuringRain`, `IsAfterSchoolActivity`, `IsEveningActivity`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getName());
			pstmt.setDate(2, data.getStartDate());
			pstmt.setString(3, data.getSocialQualityRequirement());
			pstmt.setString(4, data.getSocialQualityModifier());
			pstmt.setString(5, data.getSocialLink());
			pstmt.setInt(6, data.getVisits());
			pstmt.setBoolean(7, data.isDuringRain());
			pstmt.setBoolean(8, data.isAfterSchoolActivity());
			pstmt.setBoolean(9, data.isEveningActivity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoLectureEvent(LectureEvent data) {
		String sql = "INSERT INTO " + LECTURE_EVENT_TABLE //
				+ " (`Date`, `Answer`, `SocialQualityModifier`, `SocialLinkModifier`)" //
				+ " VALUES (?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getDate());
			pstmt.setString(2, data.getAnswer());
			pstmt.setString(3, data.getSocialQualityModifier());
			pstmt.setString(4, data.getSocialLinkModifier());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoLunchEvent(LunchEvent data) {
		String sql = "INSERT INTO " + LUNCH_EVENT_TABLE //
				+ " (`Date`, `Meal`, `Methodology`, `SocialLinksFavorite`, `IsAfterSchoolActivity`, `IsEveningActivity`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getDate());
			pstmt.setString(2, data.getMeal());
			pstmt.setString(3, data.getMethodology());
			pstmt.setString(4, data.getSocialLinksFavorite());
			pstmt.setBoolean(5, data.isAfterSchoolActivity());
			pstmt.setBoolean(6, data.isEveningActivity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoMechaModel(MechaModel data) {
		String sql = "INSERT INTO " + MECHA_MODEL_TABLE //
				+ " (`Model`, `CurrentProgress`, `TotalProgress`, `Quest`, `RequiredQuestStatus`)" //
				+ " VALUES (?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getModel());
			pstmt.setInt(2, data.getCurrentProgress());
			pstmt.setInt(3, data.getTotalProgress());
			pstmt.setInt(4, data.getQuest());
			pstmt.setString(5, data.getRequiredQuestStatus());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoMiscellaneous(MiscellaneousActivity data) {
		String sql = "INSERT INTO " + MISCELLANEOUS_ACTIVITY_TABLE //
				+ " (`Activity`, `IsAfterSchoolActivity`, `IsEveningActivity`, `IsRainRequired`, `RequiredQuest`, `SocialQualityModifier`, `Visits`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getActivity());
			pstmt.setBoolean(2, data.isAfterSchoolActivity());
			pstmt.setBoolean(3, data.isEveningActivity());
			pstmt.setBoolean(4, data.isRainRequired());
			if (data.getRequiredQuestNumber().equals(null)) {
				pstmt.setNull(5, Types.NULL);
			} else {
				pstmt.setInt(5, data.getRequiredQuestNumber());
			}
			pstmt.setString(6, data.getSocialQualityModifier());
			pstmt.setInt(7, data.getVisits());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoPhoneEvent(PhoneEvent data) {
		String sql = "INSERT INTO " + PHONE_EVENT_TABLE //
				+ " (`Date`, `InitiatingSocialLink`, `InvolvedSocialLink`, `Book`, `KeyResponses`, `Status`)"
				+ " VALUES (?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getDate());
			pstmt.setString(2, data.getInitiatingSocialLink());
			pstmt.setString(3, data.getInvolvedSocialLink());
			pstmt.setString(4, data.getBook());
			pstmt.setString(5, data.getKeyResponse());
			pstmt.setString(6, data.getStatus());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoQuest(Quest data) {
		String sql = "INSERT INTO " + QUEST_TABLE //
				+ " (`Number`, `Name`, `QuestGiver`, `StartLocation`, `StartDate`, `EndDate`, `PreviousQuestNumber`, `RequiredSocialQualities`, `Reward`, `RequiredAfterSchoolSlots`, `RequiredEveningSlots`, `DaysSpentOnQuest`, `DaysRequiredToComplete`, `Status`, `Instructions`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, data.getNumber());
			pstmt.setString(2, data.getName());
			pstmt.setString(3, data.getQuestGiver());
			pstmt.setString(4, data.getStartLocation());
			pstmt.setDate(5, data.getStartDate());
			pstmt.setDate(6, data.getEndDate());
			pstmt.setString(7, data.getPreviousQuestNumber());
			pstmt.setString(8, data.getRequiredSocialQualities());
			pstmt.setString(9, data.getReward());
			pstmt.setInt(10, data.getRequiredAfterSchoolSlots());
			pstmt.setInt(11, data.getRequiredEveningSlots());
			pstmt.setInt(12, data.getDaysSpentOnQuest());
			pstmt.setInt(13, data.getDaysRequiredToCompleteQuest());
			pstmt.setString(14, data.getStatus());
			pstmt.setString(15, data.getInstructions());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoSocialLinkEvent(SocialLinkEvent data) {
		String sql = "INSERT INTO " + SOCIAL_LINK_EVENT_TABLE //
				+ " (`SocialLink`, `Rank1Answers`, `Rank2Answers`, `Rank3Answers`, `Rank4Answers`, `Rank5Answers`, `Rank6Answers`, `Rank7Answers`, `Rank8Answers`, `Rank9Answers`, `Rank10Answers`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getSocialLink());
			pstmt.setString(2, data.getRank1Answers());
			pstmt.setString(3, data.getRank2Answers());
			pstmt.setString(4, data.getRank3Answers());
			pstmt.setString(5, data.getRank4Answers());
			pstmt.setString(6, data.getRank5Answers());
			pstmt.setString(7, data.getRank6Answers());
			pstmt.setString(8, data.getRank7Answers());
			pstmt.setString(9, data.getRank8Answers());
			pstmt.setString(10, data.getRank9Answers());
			pstmt.setString(11, data.getRank10Answers());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoSocialLinkPoints(SocialLinkPoints data) {
		String sql = "INSERT INTO " + SOCIAL_LINK_POINTS_TABLE //
				+ " (`SocialLink`, `Rank1Points`, `Rank2Points`, `Rank3Points`, `Rank4Points`, `Rank5Points`, `Rank6Points`, `Rank7Points`, `Rank8Points`, `Rank9Points`, `Rank10Points`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getSocialLink());
			pstmt.setInt(2, data.getRank1Points());
			pstmt.setInt(3, data.getRank2Points());
			pstmt.setInt(4, data.getRank3Points());
			pstmt.setInt(5, data.getRank5Points());
			pstmt.setInt(6, data.getRank5Points());
			pstmt.setInt(7, data.getRank6Points());
			pstmt.setInt(8, data.getRank7Points());
			pstmt.setInt(9, data.getRank8Points());
			pstmt.setInt(10, data.getRank9Points());
			pstmt.setInt(11, data.getRank10Points());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoSocialLinkStats(SocialLinkStats data) {
		String sql = "INSERT INTO " + SOCIAL_LINK_TABLE //
				+ " (`ArcanaNumber`, `SocialLink`, `IsAfterSchoolAvailable`, `IsEveningAvailable`, `RequiredSocialQualities`, `RequiredSocialLinkRanks`, `RemainingFlags`, `CurrentRank`, `CurrentPoints`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getArcanaNumber());
			pstmt.setString(2, data.getSocialLink());
			pstmt.setBoolean(3, data.isAfterSchoolAvailable());
			pstmt.setBoolean(4, data.isEveningAvailable());
			pstmt.setString(5, data.getRequiredSocialQuality());
			pstmt.setString(6, data.getRequiredSocialLinkRanks());
			pstmt.setInt(7, data.getRemainingFlags());
			pstmt.setInt(8, data.getCurrentRank());
			pstmt.setInt(9, data.getCurrentPoints());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertIntoSocialQualityStats(SocialQualityStats data) {
		String sql = "INSERT INTO " + SOCIAL_QUALITY_TABLE //
				+ " (`SocialQuality`, `Level1Points`, `Level2Points`, `Level3Points`, `Level4Points`, `Level5Points`, `CurrentPoints`, `CurrentLevel`)" //
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try(Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.getSocialQuality());
			pstmt.setInt(2, data.getLevel1Points());
			pstmt.setInt(3, data.getLevel2Points());
			pstmt.setInt(4, data.getLevel3Points());
			pstmt.setInt(5, data.getLevel4Points());
			pstmt.setInt(6, data.getLevel5Points());
			pstmt.setInt(7, data.getCurrentPoints());
			pstmt.setInt(8, data.getCurrentLevel());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertIntoStoryEvent(StoryEvent data) {
		String sql = "INSERT INTO " + STORY_EVENT_TABLE //
				 + " (`Date`, `EventName`, `KeyResponses`, `SocialLinkAutoLevel`, `SocialLinkModifier`, `SocialQualityModifier`)" //
				 + " VALUES (?, ?, ?, ?, ?, ?);";
		try(Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getDate());
			pstmt.setString(2, data.getName());
			pstmt.setString(3, data.getKeyResponses());
			pstmt.setString(4, data.getSocialLinkAutoLevel());
			pstmt.setString(5, data.getSocialLinkModifier());
			pstmt.setString(6, data.getSocialQualityModifier());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public void insertIntoTanakasShopping(TanakasShopping data) {
		String sql = "INSERT INTO " + TANAKAS_SHOPPING_TABLE //
				+ " (`Date`, `Offer`, `Price`)" //
				+ " VALUES (?, ?, ?);";
		try(Connection conn = this.connect(DATABASE); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, data.getDate());
			pstmt.setString(2, data.getOffer());
			pstmt.setString(3, data.getPrice());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	}

	private void createAiyaChineseDinerMenuTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + AIYA_CHINESE_DINER_TABLE + " (" //
				+ " `Day` TEXT NOT NULL UNIQUE," //
				+ " `SocialQualityModifier` TEXT NOT NULL," //
				+ " `Visits` TINYINT DEFAULT 0," //
				+ " `IsAfterSchoolActivity` BOOLEAN NOT NULL," //
				+ " `IsEveningActivity` BOOLEAN NOT NULL);";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createBookTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + BOOK_TABLE + " (" //
				+ " `Title` TEXT," //
				+ " `ReleaseDate` DATE," //
				+ " `SocialQualityModifier` TEXT NOT NULL," //
				+ " `ReadChapters` TINYINT NOT NULL," //
				+ " `TotalChapters` TINYINT NOT NULL," //
				+ " `IsObtained` BOOLEAN NOT NULL," //
				+ " `IsAfterSchoolActivity` BOOLEAN NOT NULL," //
				+ " `IsEveningActivity` BOOLEAN NOT NULL," //
				+ " PRIMARY KEY(`Name`)," //
				+ " FOREIGN KEY(ReleaseDate) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createDailyDataTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + DAILY_DATA_TABLE + " (" //
				+ " `Date` DATE," //
				+ " `Day` TEXT NOT NULL," //
				+ " `MorningWeather` TEXT NOT NULL," //
				+ " `EveningWeather` TEXT NOT NULL," //
				+ " `AfterSchoolFreeTime` TEXT NOT NULL," //
				+ " `EveningFreeTime` TEXT NOT NULL," //
				+ " `IsSchoolDay` BOOLEAN NOT NULL," //
				+ " PRIMARY KEY(`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createDungeonEventsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + DUNGEON_TABLE + " (" //
				+ " `Name` TEXT," //
				+ " `KeyResponse` TEXT," //
				+ " `SocialQualityModifier` TEXT," //
				+ " `AutoSocialLinkModifier` TEXT," //
				+ " `SocialLinkModifier` TEXT," //
				+ " `OptionalBossReward` TEXT," //
				+ " `IsStoryBossDefeated` BOOLEAN NOT NULL," //
				+ " `IsOptionalBossDefeated` BOOLEAN NOT NULL," //
				+ " PRIMARY KEY(`Name`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createExamTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + EXAM_TABLE + " (" //
				+ " `StartDate` DATE," //
				+ " `EndDate` DATE," //
				+ " `Exam` TINYTEXT NOT NULL," //
				+ " `RequiredSocialQualityLevelForTop10` varchar(15) NOT NULL," //
				+ " `SocialLinkRewardForTop10` TEXT NOT NULL," //
				+ " `RequiredSocialQualityLevelForAce` varchar(15) NOT NULL," //
				+ " `SocialLinkRewardForAce` TEXT NOT NULL," //
				+ " PRIMARY KEY(`StartDate`)," //
				+ " FOREIGN KEY(`StartDate`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`)," //
				+ " FOREIGN KEY(`EndDate`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createFridgeTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + FRIDGE_TABLE + " (" //
				+ " `Date` DATE," //
				+ " `Food` TEXT NOT NULL," //
				+ " `SocialQualityModifier` TEXT NOT NULL," //
				+ " `IsAfterSchoolActivity` BOOLEAN NOT NULL," //
				+ " `IsEveningActivity` BOOLEAN NOT NULL," //
				+ " FOREIGN KEY(`Date`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createJobTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + JOB_TABLE + " (" //
				+ " `Name` varchar(30)," //
				+ " `StartDate` DATE," //
				+ " `SocialQualityRequirement` varchar(20)," //
				+ " `SocialQualityModifier` varchar(20)," //
				+ " `SocialLink` varchar(20)," //
				+ " `Visits` TINYINT DEFAULT 0," //
				+ " `IsDuringRain` BOOLEAN NOT NULL," //
				+ " `IsAfterSchoolActivity` BOOLEAN NOT NULL," //
				+ " `IsEveningActivity` BOOLEAN NOT NULL," //
				+ " PRIMARY KEY(`Name`)," //
				+ " FOREIGN KEY(`StartDate`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createLectureAnswerTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + LECTURE_EVENT_TABLE + " (" //
				+ " `Date` DATE," //
				+ " `Answer` varchar(255) NOT NULL," //
				+ " `SocialQualityModifier` varchar(20)," //
				+ " `SocialLinkModifier` varchar(20)," //
				+ " FOREIGN KEY(`Date`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createLunchEventTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + LUNCH_EVENT_TABLE + " (" //
				+ " `Date` DATE," //
				+ " `Meal` TEXT NOT NULL," //
				+ " `Methodology` TEXT NOT NULL," //
				+ " `SocialLinksFavorite` TEXT," //
				+ " `IsAfterSchoolActivity` BOOLEAN NOT NULL," //
				+ " `IsEveningActivity` BOOLEAN NOT NULL," //
				+ " FOREIGN KEY(`Date`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createMechaModelsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + MECHA_MODEL_TABLE + " (" //
				+ " `Model` TEXT PRIMARY KEY," //
				+ " `CurrentProgress` TINYINT NOT NULL," //
				+ " `TotalProgress` TINYINT NOT NULL," //
				+ " `Quest` TINYINT NOT NULL," //
				+ " `RequiredQuestStatus` TEXT NOT NULL);";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createMiscActivitiesTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + MISCELLANEOUS_ACTIVITY_TABLE + "(" //
				+ " `Activity` TEXT NOT NULL," //
				+ " `IsAfterSchoolActivity` BOOLEAN NOT NULL," //
				+ " `IsEveningActivity` BOOLEAN NOT NULL," //
				+ " `IsRainRequired` BOOLEAN NOT NULL," //
				+ " `RequiredQuest` TINYINT," //
				+ " `SocialQualityModifier` TEXT," //
				+ " `Visits` TINYINT DEFAULT 0);";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createPhoneEventTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + PHONE_EVENT_TABLE + " (" //
				+ " `Date` DATE NOT NULL," //
				+ " `InitiatingSocialLink` TEXT NOT NULL," //
				+ " `InvolvedSocialLink` TEXT NOT NULL," //
				+ " `Book` TEXT," //
				+ " `KeyResponses` TEXT," //
				+ " `Status` varchar(10) NOT NULL," //
				+ " FOREIGN KEY(`Date`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createQuestTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + QUEST_TABLE + " (" //
				+ " `Number` TINYINT," //
				+ " `Name` TEXT NOT NULL," //
				+ " `QuestGiver` TEXT NOT NULL," //
				+ " `StartLocation` TEXT NOT NULL," //
				+ " `StartDate` DATE," //
				+ " `EndDate` DATE," //
				+ " `PreviousQuestNumber` TEXT," //
				+ " `RequiredSocialQualities` TEXT," //
				+ " `Reward` TEXT NOT NULL," //
				+ " `RequiredAfterSchoolSlots` TINYINT NOT NULL," //
				+ " `RequiredEveningSlots` TINYINT NOT NULL," //
				+ " `DaysSpentOnQuest` TINYINT NOT NULL," //
				+ " `DaysRequiredToComplete` TINYINT NOT NULL," //
				+ " `Status` TEXT NOT NULL," //
				+ " `Instructions` TEXT NOT NULL," //
				+ " PRIMARY KEY(`Number`)," //
				+ " FOREIGN KEY(`StartDate`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`)," //
				+ " FOREIGN KEY(`EndDate`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createSocialLinkAvailabilityTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + DAILY_AVAILABILITY_TABLE + " (" //
				+ "	`Date` DATE PRIMARY KEY," //
				+ "	`Magician` varchar(12)," //
				+ "	`Chariot` varchar(12)," //
				+ "	`Priestess` varchar(12)," //
				+ "	`Emperor` varchar(12)," //
				+ "	`Lovers` varchar(12)," //
				+ "	`Fortune` varchar(12)," //
				+ "	`Strength` varchar(12)," //
				+ "	`Sun` varchar(12)," //
				+ "	`Moon` varchar(12)," //
				+ "	`HangedMan` varchar(12)," //
				+ "	`Death` varchar(12)," //
				+ "	`Temperance` varchar(12)," //
				+ "	`Hermit` varchar(12)," //
				+ "	`Empress` varchar(12)," //
				+ "	`Hierophant` varchar(12)," //
				+ "	`Justice` varchar(12)," //
				+ "	`Devil` varchar(12)," //
				+ "	`Tower` varchar(12));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createSocialLinkEventsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + SOCIAL_LINK_EVENT_TABLE + " (" //
				+ " `SocialLink` varchar(25)," //
				+ " `Rank1Answers` TEXT NOT NULL," //
				+ " `Rank2Answers` TEXT NOT NULL," //
				+ " `Rank3Answers` TEXT NOT NULL," //
				+ " `Rank4Answers` TEXT NOT NULL," //
				+ " `Rank5Answers` TEXT NOT NULL," //
				+ " `Rank6Answers` TEXT NOT NULL," //
				+ " `Rank7Answers` TEXT NOT NULL," //
				+ " `Rank8Answers` TEXT NOT NULL," //
				+ " `Rank9Answers` TEXT NOT NULL," //
				+ " `Rank10Answers` TEXT NOT NULL," //
				+ " PRIMARY KEY(`SocialLink`));";

		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createSocialLinkPointsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + SOCIAL_LINK_POINTS_TABLE + " (" //
				+ " `SocialLink` TEXT PRIMARY KEY," //
				+ " `Rank1Points` TINYINT NOT NULL," //
				+ " `Rank2Points` TINYINT NOT NULL," //
				+ " `Rank3Points` TINYINT NOT NULL," //
				+ " `Rank4Points` TINYINT NOT NULL," //
				+ " `Rank5Points` TINYINT NOT NULL," //
				+ " `Rank6Points` TINYINT NOT NULL," //
				+ " `Rank7Points` TINYINT NOT NULL," //
				+ " `Rank8Points` TINYINT NOT NULL," //
				+ " `Rank9Points` TINYINT NOT NULL," //
				+ " `Rank10Points` TINYINT NOT NULL);";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createSocialLinkStatsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + SOCIAL_LINK_TABLE + " (" //
				+ " `ArcanaNumber` varchar(5) NOT NULL," //
				+ " `SocialLink` varchar(10) PRIMARY KEY," //
				+ " `IsAfterSchoolAvailable` BOOLEAN NOT NULL," //
				+ " `IsEveningAvailable` BOOLEAN NOT NULL," //
				+ " `RequiredSocialQualities` TEXT," //
				+ " `RequiredSocialLinkRanks` TEXT," //
				+ " `RemainingFlags` TINYINT NOT NULL," //
				+ " `CurrentRank` TINYINT NOT NULL," //
				+ " `CurrentPoints` TINYINT NOT NULL);";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createSocialQualityStatsTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + SOCIAL_QUALITY_TABLE + " (" //
				+ " `SocialQuality` TEXT PRIMARY KEY," //
				+ " `Level1Points` TINYINT NOT NULL," //
				+ " `Level2Points` TINYINT NOT NULL," //
				+ " `Level3Points` TINYINT NOT NULL," //
				+ " `Level4Points` TINYINT NOT NULL," //
				+ " `Level5Points` TINYINT NOT NULL," //
				+ " `CurrentPoints` TINYINT NOT NULL," //
				+ " `CurrentLevel` TINYINT NOT NULL);";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createStoryEventTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + STORY_EVENT_TABLE + " (" //
				+ " `Date` DATE," //
				+ " `EventName` TEXT NOT NULL," //
				+ " `KeyResponses` TEXT," //
				+ " `SocialLinkAutoLevel` TEXT," //
				+ " `SocialLinkModifier` TEXT," //
				+ " `SocialQualityModifier` TEXT," //
				+ " FOREIGN KEY(`Date`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createTanakasShoppingTable() {
		String sql = "CREATE TABLE IF NOT EXISTS " + TANAKAS_SHOPPING_TABLE + " (" //
				+ " `Date` DATE," //
				+ " `Offer` TEXT NOT NULL," //
				+ " `Price` TINYTEXT NOT NULL," //
				+ " FOREIGN KEY(`Date`) REFERENCES " + DAILY_DATA_TABLE + " (`Date`));";
		try (Connection conn = this.connect(DATABASE); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
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
