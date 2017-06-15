package cagreer.persona.data;

public class MiscellaneousActivity {
	private String activity, socialQualityModifier;
	private Boolean isAfterSchoolActivity, isEveningActivity, isRainRequired;
	private Integer requiredQuestNumber, visits;

	private MiscellaneousActivity(String activity, String socialQualityModifier, Boolean isAfterSchoolActivity,
			Boolean isEveningActivity, Boolean isRainRequired, Integer requiredQuestNumber, Integer visits) {
		this.activity = activity;
		this.socialQualityModifier = socialQualityModifier;
		this.isAfterSchoolActivity = isAfterSchoolActivity;
		this.isEveningActivity = isEveningActivity;
		this.isRainRequired = isRainRequired;
		this.requiredQuestNumber = requiredQuestNumber;
		this.visits = visits;
	}

	public String getActivity() {
		return activity;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public Boolean isAfterSchoolActivity() {
		return isAfterSchoolActivity;
	}

	public Boolean isEveningActivity() {
		return isEveningActivity;
	}

	public Boolean isRainRequired() {
		return isRainRequired;
	}

	public Integer getRequiredQuestNumber() {
		return requiredQuestNumber;
	}

	public Integer getVisits() {
		return visits;
	}

	public static class Builder {
		private String activity, socialQualityModifier;
		private Boolean isAfterSchoolActivity, isEveningActivity, isRainRequired;
		private Integer requiredQuestNumber, visits;

		public Builder setActivity(String activity) {
			this.activity = activity;
			return this;
		}

		public Builder setSocialQualityModifier(String socialQualityModifier) {
			this.socialQualityModifier = socialQualityModifier;
			return this;
		}

		public Builder setAfterSchoolActivity(Boolean isAfterSchoolActivity) {
			this.isAfterSchoolActivity = isAfterSchoolActivity;
			return this;
		}

		public Builder setEveningSchoolActivity(Boolean isEveningActivity) {
			this.isEveningActivity = isEveningActivity;
			return this;
		}

		public Builder setRainRequired(Boolean isRainRequired) {
			this.isRainRequired = isRainRequired;
			return this;
		}

		public Builder setRequiredQuestNumber(Integer requiredQuestNumber) {
			this.requiredQuestNumber = requiredQuestNumber;
			return this;
		}

		public Builder setVisits(Integer visits) {
			this.visits = visits;
			return this;
		}

		public MiscellaneousActivity build() {
			return new MiscellaneousActivity(activity, socialQualityModifier, isAfterSchoolActivity, isEveningActivity,
					isRainRequired, requiredQuestNumber, visits);
		}
	}
}
