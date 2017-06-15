package cagreer.persona.data;

import java.sql.Date;

public class StoryEvent {
	private Date date;
	private String name, keyResponses, socialLinkAutoLevel, socialLinkModifier, socialQualityModifier;

	private StoryEvent(Date date, String name, String keyResponses, String socialLinkAutoLevel,
			String socialLinkModifier, String socialQualityModifier) {
		this.date = date;
		this.name = name;
		this.keyResponses = keyResponses;
		this.socialLinkAutoLevel = socialLinkAutoLevel;
		this.socialLinkModifier = socialLinkModifier;
		this.socialQualityModifier = socialQualityModifier;
	}

	public Date getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

	public String getKeyResponses() {
		return keyResponses;
	}

	public String getSocialLinkAutoLevel() {
		return socialLinkAutoLevel;
	}

	public String getSocialLinkModifier() {
		return socialLinkModifier;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public static class Builder {
		private Date date;
		private String name, keyResponses, socialLinkAutoLevel, socialLinkModifier, socialQualityModifier;

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setKeyResponses(String keyResponses) {
			this.keyResponses = keyResponses;
			return this;
		}

		public Builder setSocialLinkAutoLevel(String socialLinkAutoLevel) {
			this.socialLinkAutoLevel = socialLinkAutoLevel;
			return this;
		}

		public Builder setSocialLinkModifier(String socialLinkModifier) {
			this.socialLinkModifier = socialLinkModifier;
			return this;
		}

		public Builder setSocialQualityModifier(String socialQualityModifier) {
			this.socialQualityModifier = socialQualityModifier;
			return this;
		}

		public StoryEvent build() {
			return new StoryEvent(date, name, keyResponses, socialLinkAutoLevel, socialLinkModifier,
					socialQualityModifier);
		}
	}
}
