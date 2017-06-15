package cagreer.persona.data;

public class SocialLinkEvent {
	private String socialLink;
	private String rank1Answers, rank2Answers, rank3Answers, rank4Answers, rank5Answers, rank6Answers, rank7Answers,
			rank8Answers, rank9Answers, rank10Answers;

	private SocialLinkEvent(String socialLink, String rank1Answers, String rank2Answers, String rank3Answers,
			String rank4Answers, String rank5Answers, String rank6Answers, String rank7Answers, String rank8Answers,
			String rank9Answers, String rank10Answers) {
		this.socialLink = socialLink;
		this.rank1Answers = rank1Answers;
		this.rank2Answers = rank2Answers;
		this.rank3Answers = rank3Answers;
		this.rank4Answers = rank4Answers;
		this.rank5Answers = rank5Answers;
		this.rank6Answers = rank6Answers;
		this.rank7Answers = rank7Answers;
		this.rank8Answers = rank8Answers;
		this.rank9Answers = rank9Answers;
		this.rank10Answers = rank10Answers;
	}

	public String getSocialLink() {
		return socialLink;
	}

	public String getRank1Answers() {
		return rank1Answers;
	}

	public String getRank2Answers() {
		return rank2Answers;
	}

	public String getRank3Answers() {
		return rank3Answers;
	}

	public String getRank4Answers() {
		return rank4Answers;
	}

	public String getRank5Answers() {
		return rank5Answers;
	}

	public String getRank6Answers() {
		return rank6Answers;
	}

	public String getRank7Answers() {
		return rank7Answers;
	}

	public String getRank8Answers() {
		return rank8Answers;
	}

	public String getRank9Answers() {
		return rank9Answers;
	}

	public String getRank10Answers() {
		return rank10Answers;
	}

	public static class Builder {
		private String socialLink;
		private String rank1Answers, rank2Answers, rank3Answers, rank4Answers, rank5Answers, rank6Answers, rank7Answers,
				rank8Answers, rank9Answers, rank10Answers;

		public Builder setSocialLink(String socialLink) {
			this.socialLink = socialLink;
			return this;
		}

		public Builder setRank1Answers(String rank1Answers) {
			this.rank1Answers = rank1Answers;
			return this;
		}

		public Builder setRank2Answers(String rank2Answers) {
			this.rank2Answers = rank2Answers;
			return this;
		}

		public Builder setRank3Answers(String rank3Answers) {
			this.rank3Answers = rank3Answers;
			return this;
		}

		public Builder setRank4Answers(String rank4Answers) {
			this.rank4Answers = rank4Answers;
			return this;
		}

		public Builder setRank5Answers(String rank5Answers) {
			this.rank5Answers = rank5Answers;
			return this;
		}

		public Builder setRank6Answers(String rank6Answers) {
			this.rank6Answers = rank6Answers;
			return this;
		}

		public Builder setRank7Answers(String rank7Answers) {
			this.rank7Answers = rank7Answers;
			return this;
		}

		public Builder setRank8Answers(String rank8Answers) {
			this.rank8Answers = rank8Answers;
			return this;
		}

		public Builder setRank9Answers(String rank9Answers) {
			this.rank9Answers = rank9Answers;
			return this;
		}

		public Builder setRank10Answers(String rank10Answers) {
			this.rank10Answers = rank10Answers;
			return this;
		}

		public SocialLinkEvent build() {
			return new SocialLinkEvent(socialLink, rank1Answers, rank2Answers, rank3Answers, rank4Answers,
					rank5Answers, rank6Answers, rank7Answers, rank8Answers, rank9Answers, rank10Answers);
		}
	}
}
