package cagreer.persona.data;

import java.sql.Date;

public class Job {
	private Date startDate;
	private String name, socialQualityRequirement, socialQualityModifier, socialLink;
	private Integer visits;
	private Boolean isDuringRain, isAfterSchoolActivity, isEveningActivity;

	private Job(Date startDate, String name, String socialQualityRequirement, String socialQualityModifier,
			String socialLink, Integer visits, Boolean isDuringRain, Boolean isAfterSchoolActivity,
			Boolean isEveningActivity) {
		this.startDate = startDate;
		this.name = name;
		this.socialQualityRequirement = socialQualityRequirement;
		this.socialQualityModifier = socialQualityModifier;
		this.socialLink = socialLink;
		this.visits = visits;
		this.isDuringRain = isDuringRain;
		this.isAfterSchoolActivity = isAfterSchoolActivity;
		this.isEveningActivity = isEveningActivity;
	}

	public Date getStartDate() {
		return startDate;
	}

	public String getName() {
		return name;
	}

	public String getSocialQualityRequirement() {
		return socialQualityRequirement;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public String getSocialLink() {
		return socialLink;
	}

	public Integer getVisits() {
		return visits;
	}

	public Boolean isDuringRain() {
		return isDuringRain;
	}

	public Boolean isAfterSchoolActivity() {
		return isAfterSchoolActivity;
	}

	public Boolean isEveningActivity() {
		return isEveningActivity;
	}

	public static class Builder {
		private Date startDate;
		private String name, socialQualityRequirement, socialQualityModifier, socialLink;
		private Integer visits;
		private Boolean isDuringRain, isAfterSchoolActivity, isEveningActivity;

		public Builder setStartDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setSocialQualityRequirement(String socialQualityRequirement) {
			this.socialQualityRequirement = socialQualityRequirement;
			return this;
		}

		public Builder setSocialQualityModifier(String socialQualityModifier) {
			this.socialQualityModifier = socialQualityModifier;
			return this;
		}

		public Builder setSocialLink(String socialLink) {
			this.socialLink = socialLink;
			return this;
		}

		public Builder setVisits(int visits) {
			this.visits = visits;
			return this;
		}

		public Builder setDuringRain(Boolean isDuringRain) {
			this.isDuringRain = isDuringRain;
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

		public Job build() {
			return new Job(startDate, name, socialQualityRequirement, socialQualityModifier, socialLink, visits,
					isDuringRain, isAfterSchoolActivity, isEveningActivity);
		}
	}
}
