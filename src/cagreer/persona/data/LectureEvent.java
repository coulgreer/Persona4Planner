package cagreer.persona.data;
import java.sql.Date;

public class LectureEvent {
	private Date date;
	private String answer;
	private String socialLinkModifier, socialQualityModifier;

	private LectureEvent(Date date, String answer, String socialLinkModifier, String socialQualityModifier) {
		this.date = date;
		this.answer = answer;
		this.socialLinkModifier = socialLinkModifier;
		this.socialQualityModifier = socialQualityModifier;
	}

	public Date getDate() {
		return date;
	}

	public String getAnswer() {
		return answer;
	}

	public String getSocialLinkModifier() {
		return socialLinkModifier;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public static class Builder {
		private Date date;
		private String answer;
		private String socialLinkModifier, socialQualityModifier;

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setAnswer(String answer) {
			this.answer = answer;
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

		public LectureEvent build() {
			return new LectureEvent(date, answer, socialLinkModifier, socialQualityModifier);
		}
	}
}
