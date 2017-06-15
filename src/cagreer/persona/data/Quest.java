package cagreer.persona.data;

import java.sql.Date;

public class Quest {
	private Date startDate, endDate;
	private Integer number, neededAfterSchoolSlots, neededEveningSlots, daysSpentOnQuest, daysRequiredToCompleteQuest;
	private String name, questGiver, startLocation, previousQuestNumber, requiredSocialQualities, reward, status,
			instructions;

	private Quest(Date startDate, Date endDate, Integer number, Integer neededAfterSchoolSlots,
			Integer neededEveningSlots, Integer daysSpentOnQuest, Integer daysRequiredToCompleteQuest, String name,
			String questGiver, String startLocation, String previousQuestNumber, String requiredSocialQualities,
			String reward, String status, String instructions) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.number = number;
		this.neededAfterSchoolSlots = neededAfterSchoolSlots;
		this.neededEveningSlots = neededEveningSlots;
		this.daysSpentOnQuest = daysSpentOnQuest;
		this.daysRequiredToCompleteQuest = daysRequiredToCompleteQuest;
		this.name = name;
		this.questGiver = questGiver;
		this.startLocation = startLocation;
		this.previousQuestNumber = previousQuestNumber;
		this.requiredSocialQualities = requiredSocialQualities;
		this.reward = reward;
		this.status = status;
		this.instructions = instructions;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public String getQuestGiver() {
		return questGiver;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getPreviousQuestNumber() {
		return previousQuestNumber;
	}

	public String getRequiredSocialQualities() {
		return requiredSocialQualities;
	}

	public String getReward() {
		return reward;
	}

	public int getRequiredAfterSchoolSlots() {
		return neededAfterSchoolSlots;
	}

	public int getRequiredEveningSlots() {
		return neededEveningSlots;
	}

	public int getDaysSpentOnQuest() {
		return daysSpentOnQuest;
	}

	public int getDaysRequiredToCompleteQuest() {
		return daysRequiredToCompleteQuest;
	}

	public String getStatus() {
		return status;
	}

	public String getInstructions() {
		return instructions;
	}

	public static class Builder {
		private Date startDate, endDate;
		private Integer number, neededAfterSchoolSlots, neededEveningSlots, daysSpentOnQuest,
				daysRequiredToCompleteQuest;
		private String name, questGiver, startLocation, previousQuestNumber, requiredSocialQualities, reward, status,
				instructions;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setNumber(Integer number) {
			this.number = number;
			return this;
		}

		public Builder setQuestGiver(String questGiver) {
			this.questGiver = questGiver;
			return this;
		}

		public Builder setStartLocation(String startLocation) {
			this.startLocation = startLocation;
			return this;
		}

		public Builder setStartDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder setEndDate(Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public Builder setPreviousQuest(String previousQuestNumber) {
			this.previousQuestNumber = previousQuestNumber;
			return this;
		}

		public Builder setRequiredSocialQualities(String socialQualities) {
			this.requiredSocialQualities = socialQualities;
			return this;
		}

		public Builder setNeededAfterSchoolSlot(Integer neededAfterSchoolSlots) {
			this.neededAfterSchoolSlots = neededAfterSchoolSlots;
			return this;
		}

		public Builder setNeededEveningSlots(Integer neededEveningSlots) {
			this.neededEveningSlots = neededEveningSlots;
			return this;
		}

		public Builder setDaysSpentOnQuest(Integer daysSpentOnQuest) {
			this.daysSpentOnQuest = daysSpentOnQuest;
			return this;
		}

		public Builder setDaysNeededToComplete(Integer daysRequiredToCompleteQuest) {
			this.daysRequiredToCompleteQuest = daysRequiredToCompleteQuest;
			return this;
		}

		public Builder setReward(String reward) {
			this.reward = reward;
			return this;
		}

		public Builder setStatus(String status) {
			this.status = status;
			return this;
		}

		public Builder setInstructions(String instructions) {
			this.instructions = instructions;
			return this;
		}

		public Quest build() {
			return new Quest(startDate, endDate, number, neededAfterSchoolSlots, neededEveningSlots, daysSpentOnQuest,
					daysRequiredToCompleteQuest, name, questGiver, startLocation, previousQuestNumber,
					requiredSocialQualities, reward, status, instructions);
		}
	}
}
