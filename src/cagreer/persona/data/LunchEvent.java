package cagreer.persona.data;

import java.sql.Date;

public class LunchEvent {
	private Date date;
	private String meal, methodology, socialLinksFavorite;
	private Boolean isAfterSchoolActivity, isEveningActivity;

	private LunchEvent(Date date, String meal, String methodology, String socialLinksFavorite,
			Boolean isAfterSchoolActivity, Boolean isEveningActivity) {
		this.date = date;
		this.meal = meal;
		this.methodology = methodology;
		this.socialLinksFavorite = socialLinksFavorite;
		this.isAfterSchoolActivity = isAfterSchoolActivity;
		this.isEveningActivity = isEveningActivity;
	}

	public Date getDate() {
		return date;
	}

	public String getMeal() {
		return meal;
	}

	public String getMethodology() {
		return methodology;
	}

	public String getSocialLinksFavorite() {
		return socialLinksFavorite;
	}

	public boolean isAfterSchoolActivity() {
		return isAfterSchoolActivity;
	}

	public boolean isEveningActivity() {
		return isEveningActivity;
	}

	public static class Builder {
		private Date date;
		private String meal, methodology, socialLinksFavorite;
		private Boolean isAfterSchoolActivity, isEveningActivity;

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setMeal(String meal) {
			this.meal = meal;
			return this;
		}

		public Builder setMethodology(String methodology) {
			this.methodology = methodology;
			return this;
		}

		public Builder setSocialLinksFavorite(String socialLinksFavorite) {
			this.socialLinksFavorite = socialLinksFavorite;
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

		public LunchEvent build() {
			return new LunchEvent(date, meal, methodology, socialLinksFavorite, isAfterSchoolActivity,
					isEveningActivity);
		}
	}
}
