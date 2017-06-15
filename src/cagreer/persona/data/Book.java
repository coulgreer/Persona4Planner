package cagreer.persona.data;
import java.sql.Date;

public class Book {
	private Boolean isObtained, isAfterSchoolActivity, isEveningActivity;
	private Integer readSections, totalSections;
	private String name, socialQualityModifier;
	private Date releaseDate;

	private Book(Date releaseDate, String name, String socialQualityModifier, Integer readSections, Integer totalSections,
			Boolean isObtained, Boolean isAfterSchoolActivity, Boolean isEveningActivity) {
		this.releaseDate = releaseDate;
		this.name = name;
		this.socialQualityModifier = socialQualityModifier;
		this.readSections = readSections;
		this.totalSections = totalSections;
		this.isObtained = isObtained;
		this.isAfterSchoolActivity = isAfterSchoolActivity;
		this.isEveningActivity = isEveningActivity;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getName() {
		return name;
	}

	public String getSocialQualityModifier() {
		return socialQualityModifier;
	}

	public Integer getReadSections() {
		return readSections;
	}

	public Integer getTotalSections() {
		return totalSections;
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
		private Integer readSections, totalSections;
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

		public Builder setReadSections(Integer readSections) {
			this.readSections = readSections;
			return this;
		}

		public Builder setTotalSections(Integer totalSections) {
			this.totalSections = totalSections;
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
			return new Book(releaseDate, name, socialQualityModifier, readSections, totalSections, isObtained,
					isAfterSchoolActivity, isEveningActivity);
		}
	}
}
