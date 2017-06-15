package cagreer.persona.data;

import java.sql.Date;

public class Exam {
	private Date startDate, endDate;
	private String exam, requiredSocialQualityLevelForTop10, socialLinkRewardsForTop10, requiredSocialQualityLevelForAce,
			socialLinkRewardsForAce;

	private Exam(Date startDate, Date endDate, String exam, String neededSocialQualityLevelForTop10,
			String top10SocialLinkRewards, String neededSocialQualityLevelForTop, String topSocialLinkRewards) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.exam = exam;
		this.requiredSocialQualityLevelForTop10 = neededSocialQualityLevelForTop10;
		this.socialLinkRewardsForTop10 = top10SocialLinkRewards;
		this.requiredSocialQualityLevelForAce = neededSocialQualityLevelForTop;
		this.socialLinkRewardsForAce = topSocialLinkRewards;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getExam() {
		return exam;
	}

	public String getRequiredSocialQualityLevelForTop10() {
		return requiredSocialQualityLevelForTop10;
	}

	public String getSocialLinkRewardsForTop10() {
		return socialLinkRewardsForTop10;
	}

	public String getRequiredSocialQualityLevelForAce() {
		return requiredSocialQualityLevelForAce;
	}

	public String getSocialLinkRewardsForAce() {
		return socialLinkRewardsForAce;
	}

	public static class Builder {
		private Date startDate, endDate;
		private String exam, requiredSocialQualityLevelForTop10, socialLinkRewardsForTop10, requiredSocialQualityLevelForAce,
				socialLinkRewardsForAce;

		public Builder setStartDate(Date startDate) {
			this.startDate = startDate;
			return this;
		}

		public Builder setEndDate(Date endDate) {
			this.endDate = endDate;
			return this;
		}

		public Builder setExam(String exam) {
			this.exam = exam;
			return this;
		}

		public Builder setRequiredSocialQualityLevelForTop10(String requiredSocialQualityLevelForTop10) {
			this.requiredSocialQualityLevelForTop10 = requiredSocialQualityLevelForTop10;
			return this;
		}

		public Builder setSocialLinkRewardsForTop10(String socialLinkRewardsForTop10) {
			this.socialLinkRewardsForTop10 = socialLinkRewardsForTop10;
			return this;
		}

		public Builder setRequiredSocialQualityLevelForAce(String requiredSocialQualityLevelForAce) {
			this.requiredSocialQualityLevelForAce = requiredSocialQualityLevelForAce;
			return this;
		}

		public Builder setSocialLinkRewardsForAce(String socialLinkRewardsForAce) {
			this.socialLinkRewardsForAce = socialLinkRewardsForAce;
			return this;
		}

		public Exam build() {
			return new Exam(startDate, endDate, exam, requiredSocialQualityLevelForTop10, socialLinkRewardsForTop10,
					requiredSocialQualityLevelForAce, socialLinkRewardsForAce);
		}
	}
}
