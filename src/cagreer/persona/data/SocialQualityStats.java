package cagreer.persona.data;

public class SocialQualityStats {
	private String socialQuality;
	private Integer level1Points, level2Points, level3Points, level4Points, level5Points, currentPoints, currentLevel;

	private SocialQualityStats(String socialQuality, Integer level1Points, Integer level2Points, Integer level3Points,
			Integer level4Points, Integer level5Points, Integer currentPoints, Integer currentLevel) {
		this.socialQuality = socialQuality;
		this.level1Points = level1Points;
		this.level2Points = level2Points;
		this.level3Points = level3Points;
		this.level4Points = level4Points;
		this.level5Points = level5Points;
		this.currentPoints = currentPoints;
		this.currentLevel = currentLevel;
	}

	public String getSocialQuality() {
		return socialQuality;
	}

	public Integer getLevel1Points() {
		return level1Points;
	}

	public Integer getLevel2Points() {
		return level2Points;
	}

	public Integer getLevel3Points() {
		return level3Points;
	}

	public Integer getLevel4Points() {
		return level4Points;
	}

	public Integer getLevel5Points() {
		return level5Points;
	}

	public Integer getCurrentPoints() {
		return currentPoints;
	}

	public Integer getCurrentLevel() {
		return currentLevel;
	}

	public static class Builder {
		private String socialQuality;
		private Integer level1Points, level2Points, level3Points, level4Points, level5Points, currentPoints,
				currentLevel;

		public Builder setSocialQuality(String socialQuality) {
			this.socialQuality = socialQuality;
			return this;
		}

		public Builder setLevel1Points(Integer level1Points) {
			this.level1Points = level1Points;
			return this;
		}

		public Builder setLevel2Points(Integer level2Points) {
			this.level2Points = level2Points;
			return this;
		}

		public Builder setLevel3Points(Integer level3Points) {
			this.level3Points = level3Points;
			return this;
		}

		public Builder setLevel4Points(Integer level4Points) {
			this.level4Points = level4Points;
			return this;
		}

		public Builder setLevel5Points(Integer level5Points) {
			this.level5Points = level5Points;
			return this;
		}

		public Builder setCurrentPoints(Integer currentPoints) {
			this.currentPoints = currentPoints;
			return this;
		}

		public Builder setCurrentLevel(Integer currentLevel) {
			this.currentLevel = currentLevel;
			return this;
		}

		public SocialQualityStats build() {
			return new SocialQualityStats(socialQuality, level1Points, level2Points, level3Points, level4Points,
					level5Points, currentPoints, currentLevel);
		}
	}
}
