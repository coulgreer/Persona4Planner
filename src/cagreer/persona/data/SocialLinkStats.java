package cagreer.persona.data;

public class SocialLinkStats {
	private String arcanaNumber, socialLink, requiredSocialQuality, requiredSocialLinkRanks;
	private Boolean isAfterSchoolAvailable, isEveningAvailable;
	private Integer remainingFlags, currentRank, currentPoints;

	private SocialLinkStats(String arcanaNumber, String socialLink, String requiredSocialQuality,
			String requiredSocialLinkRanks, Boolean isAfterSchoolAvailable, Boolean isEveningAvailable,
			Integer remainingFlags, Integer currentRank, Integer currentPoints) {
		this.arcanaNumber = arcanaNumber;
		this.socialLink = socialLink;
		this.requiredSocialQuality = requiredSocialQuality;
		this.requiredSocialLinkRanks = requiredSocialLinkRanks;
		this.isAfterSchoolAvailable = isAfterSchoolAvailable;
		this.isEveningAvailable = isEveningAvailable;
		this.remainingFlags = remainingFlags;
		this.currentRank = currentRank;
		this.currentPoints = currentPoints;
	}

	public String getArcanaNumber() {
		return arcanaNumber;
	}

	public String getSocialLink() {
		return socialLink;
	}

	public String getRequiredSocialQuality() {
		return requiredSocialQuality;
	}

	public String getRequiredSocialLinkRanks() {
		return requiredSocialLinkRanks;
	}

	public Boolean isAfterSchoolAvailable() {
		return isAfterSchoolAvailable;
	}

	public Boolean isEveningAvailable() {
		return isEveningAvailable;
	}

	public Integer getRemainingFlags() {
		return remainingFlags;
	}

	public Integer getCurrentRank() {
		return currentRank;
	}

	public Integer getCurrentPoints() {
		return currentPoints;
	}

	public static class Builder {
		private String arcanaNumber, socialLink, requiredSocialQuality, requiredSocialLinkRanks;
		private Boolean isAfterSchoolAvailable, isEveningAvailable;
		private Integer remainingFlags, currentRank, currentPoints;

		public Builder setArcanaNumber(String arcanaNumber) {
			this.arcanaNumber = arcanaNumber;
			return this;
		}

		public Builder setSocialLink(String socialLink) {
			this.socialLink = socialLink;
			return this;
		}

		public Builder setRequiredSocialQuality(String requiredSocialQuality) {
			this.requiredSocialQuality = requiredSocialQuality;
			return this;
		}

		public Builder setRequiredSocialLinkRanks(String requiredSocialLinkRanks) {
			this.requiredSocialLinkRanks = requiredSocialLinkRanks;
			return this;
		}

		public Builder setAfterSchoolAvailable(Boolean isAfterSchoolAvailable) {
			this.isAfterSchoolAvailable = isAfterSchoolAvailable;
			return this;
		}

		public Builder setEveningAvailable(Boolean isEveningAvailable) {
			this.isEveningAvailable = isEveningAvailable;
			return this;
		}

		public Builder setRemainingFlags(Integer remainingFlags) {
			this.remainingFlags = remainingFlags;
			return this;
		}

		public Builder setCurrentRank(Integer currentRank) {
			this.currentRank = currentRank;
			return this;
		}

		public Builder setCurrentPoints(Integer currentPoints) {
			this.currentPoints = currentPoints;
			return this;
		}

		public SocialLinkStats build() {
			return new SocialLinkStats(arcanaNumber, socialLink, requiredSocialQuality, requiredSocialLinkRanks,
					isAfterSchoolAvailable, isEveningAvailable, remainingFlags, currentRank, currentPoints);
		}
	}
}
