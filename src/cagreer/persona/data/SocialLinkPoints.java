package cagreer.persona.data;

public class SocialLinkPoints {
	private String socialLink;
	private Integer rank1Points, rank2Points, rank3Points, rank4Points, rank5Points, rank6Points, rank7Points,
			rank8Points, rank9Points, rank10Points;

	private SocialLinkPoints(String socialLink, Integer rank1Points, Integer rank2Points, Integer rank3Points,
			Integer rank4Points, Integer rank5Points, Integer rank6Points, Integer rank7Points, Integer rank8Points,
			Integer rank9Points, Integer rank10Points) {
		this.socialLink = socialLink;
		this.rank1Points = rank1Points;
		this.rank2Points = rank2Points;
		this.rank3Points = rank3Points;
		this.rank4Points = rank4Points;
		this.rank5Points = rank5Points;
		this.rank6Points = rank6Points;
		this.rank7Points = rank7Points;
		this.rank8Points = rank8Points;
		this.rank9Points = rank9Points;
		this.rank10Points = rank10Points;
	}

	public String getSocialLink() {
		return socialLink;
	}

	public Integer getRank1Points() {
		return rank1Points;
	}

	public Integer getRank2Points() {
		return rank2Points;
	}

	public Integer getRank3Points() {
		return rank3Points;
	}

	public Integer getRank4Points() {
		return rank4Points;
	}

	public Integer getRank5Points() {
		return rank5Points;
	}

	public Integer getRank6Points() {
		return rank6Points;
	}

	public Integer getRank7Points() {
		return rank7Points;
	}

	public Integer getRank8Points() {
		return rank8Points;
	}

	public Integer getRank9Points() {
		return rank9Points;
	}

	public Integer getRank10Points() {
		return rank10Points;
	}

	public static class Builder {
		private String socialLink;
		private Integer rank1Points, rank2Points, rank3Points, rank4Points, rank5Points, rank6Points, rank7Points,
				rank8Points, rank9Points, rank10Points;

		public Builder setSocialLink(String socialLink) {
			this.socialLink = socialLink;
			return this;
		}

		public Builder setRank1Points(Integer rank1Points) {
			this.rank1Points = rank1Points;
			return this;
		}

		public Builder setRank2Points(Integer rank2Points) {
			this.rank2Points = rank2Points;
			return this;
		}

		public Builder setRank3Points(Integer rank3Points) {
			this.rank3Points = rank3Points;
			return this;
		}

		public Builder setRank4Points(Integer rank4Points) {
			this.rank4Points = rank4Points;
			return this;
		}

		public Builder setRank5Points(Integer rank5Points) {
			this.rank5Points = rank5Points;
			return this;
		}

		public Builder setRank6Points(Integer rank6Points) {
			this.rank6Points = rank6Points;
			return this;
		}

		public Builder setRank7Points(Integer rank7Points) {
			this.rank7Points = rank7Points;
			return this;
		}

		public Builder setRank8Points(Integer rank8Points) {
			this.rank8Points = rank8Points;
			return this;
		}

		public Builder setRank9Points(Integer rank9Points) {
			this.rank9Points = rank9Points;
			return this;
		}

		public Builder setRank10Points(Integer rank10Points) {
			this.rank10Points = rank10Points;
			return this;
		}

		public SocialLinkPoints build() {
			return new SocialLinkPoints(socialLink, rank1Points, rank2Points, rank3Points, rank4Points, rank5Points,
					rank6Points, rank7Points, rank8Points, rank9Points, rank10Points);
		}
	}
}
