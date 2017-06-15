package cagreer.persona.data;

import java.sql.Date;

public class FridgeEvent {
	private Date date;
	private String meal, socialQualityModifier;
	private Boolean isAfterSchoolActivity, isEveningActivity;

	private FridgeEvent(Date date, String meal, String socialQualityModifier, Boolean isAfterSchoolActivity,
			Boolean isEveningActivity) {
		this.date = date;
		this.meal = meal;
		this.socialQualityModifier = socialQualityModifier;
		this.isAfterSchoolActivity = isAfterSchoolActivity;
		this.isEveningActivity = isEveningActivity;
	}

	public Date getDate() {
		return date;
	}

	public String getMeal() {
		return meal;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public boolean isAfterSchoolActivity() {
		return isAfterSchoolActivity;
	}

	public boolean isEveningActivity() {
		return isEveningActivity;
	}

	public static class Builder {
		private Date date;
		private String meal, socialQualityModifier;
		private boolean isAfterSchoolActivity, isEveningActivity;

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setMeal(String meal) {
			this.meal = meal;
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

		public FridgeEvent build() {
			return new FridgeEvent(date, meal, socialQualityModifier, isAfterSchoolActivity, isEveningActivity);
		}
	}
}
