package cagreer.persona.data;

public class AiyaChineseDiner {
	private String day, socialQualityModifier;
	private Boolean isAfterSchoolActivity, isEveningActivity;
	private Integer visits;

	private AiyaChineseDiner(String day, String socialQualityModifier, Boolean isAfterSchoolActivity,
			Boolean isEveningActivity, Integer visits) {
		this.day = day;
		this.socialQualityModifier = socialQualityModifier;
		this.isAfterSchoolActivity = isAfterSchoolActivity;
		this.isEveningActivity = isEveningActivity;
		this.visits = visits;
	}

	public String getDay() {
		return day;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public Boolean isAfterSchoolActivity() {
		return isAfterSchoolActivity;
	}

	public Boolean isEveningSchoolActivity() {
		return isEveningActivity;
	}

	public Integer getVisits() {
		return visits;
	}

	public static class Builder {
		private String day, socialQualityModifier;
		private Boolean isAfterSchoolActivity, isEveningActivity;
		private Integer visits;

		public Builder setDay(String day) {
			this.day = day;
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

		public Builder setEveningActivity(Boolean isEveningActivity) {
			this.isEveningActivity = isEveningActivity;
			return this;
		}

		public Builder setNumberOfVisit(Integer visits) {
			this.visits = visits;
			return this;
		}

		public AiyaChineseDiner build() {
			return new AiyaChineseDiner(day, socialQualityModifier, isAfterSchoolActivity, isEveningActivity, visits);
		}
	}
}
