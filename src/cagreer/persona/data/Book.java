package cagreer.persona.data;
import java.sql.Date;

public class Book {
	private Boolean isObtained, isAfterSchoolActivity, isEveningActivity;
	private Integer readChapters, totalChapters;
	private String title, socialQualityModifier;
	private Date releaseDate;

	private Book(Date releaseDate, String name, String socialQualityModifier, Integer readChapters, Integer totalChapters,
			Boolean isObtained, Boolean isAfterSchoolActivity, Boolean isEveningActivity) {
		this.releaseDate = releaseDate;
		this.title = name;
		this.socialQualityModifier = socialQualityModifier;
		this.readChapters = readChapters;
		this.totalChapters = totalChapters;
		this.isObtained = isObtained;
		this.isAfterSchoolActivity = isAfterSchoolActivity;
		this.isEveningActivity = isEveningActivity;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public Integer getReadChapters() {
		return readChapters;
	}

	public Integer getTotalChapters() {
		return totalChapters;
	}

	public Boolean isObtained() {
		return isObtained;
	}

	public Boolean isAfterSchoolActivity() {
		return isAfterSchoolActivity;
	}

	public Boolean isEveningActivity() {
		return isEveningActivity;
	}

	public static class Builder {
		private Boolean isObtained, isAfterSchoolActivity, isEveningActivity;
		private Integer readChapters, totalChapters;
		private String name, socialQualityModifier;
		private Date releaseDate;

		public Builder setReleaseDate(Date date) {
			this.releaseDate = date;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setSocialQualityModifier(String socialQualityModifier) {
			this.socialQualityModifier = socialQualityModifier;
			return this;
		}

		public Builder setReadChapters(Integer readChapters) {
			this.readChapters = readChapters;
			return this;
		}

		public Builder setTotalChapters(Integer totalChapters) {
			this.totalChapters = totalChapters;
			return this;
		}

		public Builder setObtained(Boolean isObtained) {
			this.isObtained = isObtained;
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

		public Book build() {
			return new Book(releaseDate, name, socialQualityModifier, readChapters, totalChapters, isObtained,
					isAfterSchoolActivity, isEveningActivity);
		}
	}
}
