package cagreer.persona.data;

public class MechaModel {
	private String model, requiredQuestStatus;
	private Integer currentProgress, totalProgress, quest;

	private MechaModel(String model, String requiredQuestStatus, Integer currentProgress, Integer totalProgress,
			Integer quest) {
		this.model = model;
		this.requiredQuestStatus = requiredQuestStatus;
		this.currentProgress = currentProgress;
		this.totalProgress = totalProgress;
		this.quest = quest;
	}

	public String getModel() {
		return model;
	}

	public String getRequiredQuestStatus() {
		return requiredQuestStatus;
	}

	public int getCurrentProgress() {
		return currentProgress;
	}

	public int getTotalProgress() {
		return totalProgress;
	}

	public int getQuest() {
		return quest;
	}

	public static class Builder {
		private String model, requiredQuestStatus;
		private int currentProgress, totalProgress, quest;

		public Builder setModel(String model) {
			this.model = model;
			return this;
		}

		public Builder setRequiredQuestStatus(String requiredQuestStatus) {
			this.requiredQuestStatus = requiredQuestStatus;
			return this;
		}

		public Builder setCurrentProgress(Integer currentProgress) {
			this.currentProgress = currentProgress;
			return this;
		}

		public Builder setTotalProgress(Integer totalProgress) {
			this.totalProgress = totalProgress;
			return this;
		}

		public Builder setQuest(Integer quest) {
			this.quest = quest;
			return this;
		}

		public MechaModel build() {
			return new MechaModel(model, requiredQuestStatus, currentProgress, totalProgress, quest);
		}
	}
}
