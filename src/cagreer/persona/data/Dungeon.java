package cagreer.persona.data;

public class Dungeon {
	private String name, keyResponse, socialQualityModifier, autoSocialLinkLevel, socialLinkModifier,
			optionalBossReward;
	private boolean isStoryBossDefeated, isOptionalBossDefeated;

	private Dungeon(String name, String keyResponse, String socialQualityModifier, String autoSocialLinkLevel,
			String socialLinkModifier, String optionalBossReward, Boolean isStoryBossDefeated,
			Boolean isOptionalBossDefeated) {
		this.name = name;
		this.keyResponse = keyResponse;
		this.socialQualityModifier = socialQualityModifier;
		this.autoSocialLinkLevel = autoSocialLinkLevel;
		this.socialLinkModifier = socialLinkModifier;
		this.optionalBossReward = optionalBossReward;
		this.isStoryBossDefeated = isStoryBossDefeated;
		this.isOptionalBossDefeated = isOptionalBossDefeated;
	}

	public String getName() {
		return name;
	}

	public String getKeyResponse() {
		return keyResponse;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public String getAutoSocialLinkLevel() {
		return autoSocialLinkLevel;
	}

	public String getSocialLinkModifier() {
		return socialLinkModifier;
	}

	public String getOptionalBossReward() {
		return optionalBossReward;
	}

	public Boolean isStoryBossDefeated() {
		return isStoryBossDefeated;
	}

	public Boolean isOptionalBossDefeated() {
		return isOptionalBossDefeated;
	}

	public static class Builder {
		private String name, keyResponse, socialQualityModifier, autoSocialLinkLevel, socialLinkModifier,
				optionalBossReward;
		private Boolean isStoryBossDefeated, isOptionalBossDefeated;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setKeyResponse(String keyResponse) {
			this.keyResponse = keyResponse;
			return this;
		}

		public Builder setSocialQualityModifier(String socialQualityModifier) {
			this.socialQualityModifier = socialQualityModifier;
			return this;
		}

		public Builder setAutoSocialLinkLevel(String autoSocialLinkLevel) {
			this.autoSocialLinkLevel = autoSocialLinkLevel;
			return this;
		}

		public Builder setSocialLinkModifier(String socialLinkModifier) {
			this.socialLinkModifier = socialLinkModifier;
			return this;
		}

		public Builder setOptionalBossReward(String optionalBossReward) {
			this.optionalBossReward = optionalBossReward;
			return this;
		}

		public Builder setStoryBossDefeated(Boolean isStoryBossDefeated) {
			this.isStoryBossDefeated = isStoryBossDefeated;
			return this;
		}

		public Builder setOptionalBossDefeated(Boolean isOptionalBossDefeated) {
			this.isOptionalBossDefeated = isOptionalBossDefeated;
			return this;
		}

		public Dungeon build() {
			return new Dungeon(name, keyResponse, socialQualityModifier, autoSocialLinkLevel, socialLinkModifier,
					optionalBossReward, isStoryBossDefeated, isOptionalBossDefeated);
		}
	}
}
