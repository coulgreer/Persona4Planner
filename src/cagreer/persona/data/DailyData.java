package cagreer.persona.data;
import java.sql.Date;

public class DailyData {
	private Date date;
	private Boolean isSchoolDay;
	private String day, morningWeather, eveningWeather, afterSchoolFreeTime, eveningFreeTime;

	private DailyData(Date date, Boolean isSchoolDay, String day, String morningWeather, String eveningWeather,
			String afterSchoolFreeTime, String eveningFreeTime) {
		this.date = date;
		this.isSchoolDay = isSchoolDay;
		this.day = day;
		this.morningWeather = morningWeather;
		this.eveningWeather = eveningWeather;
		this.afterSchoolFreeTime = afterSchoolFreeTime;
		this.eveningFreeTime = eveningFreeTime;
	}

	public Date getDate() {
		return date;
	}

	public String getDay() {
		return day;
	}

	public String getMorningWeather() {
		return morningWeather;
	}

	public String getEveningWeather() {
		return eveningWeather;
	}

	public String getAfterSchoolFreeTime() {
		return afterSchoolFreeTime;
	}

	public String getEveningFreeTime() {
		return eveningFreeTime;
	}

	public boolean isSchoolDay() {
		return isSchoolDay;
	}

	public static class Builder {
		private Date date;
		private boolean isSchoolDay;
		private String day, morningWeather, eveningWeather, afterSchoolFreeTime, eveningFreeTime;

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setDay(String day) {
			this.day = day;
			return this;
		}

		public Builder setMorningWeather(String morningWeather) {
			this.morningWeather = morningWeather;
			return this;
		}

		public Builder setEveningWeather(String eveningWeather) {
			this.eveningWeather = eveningWeather;
			return this;
		}

		public Builder setAfterSchoolFreeTime(String afterSchoolFreeTime) {
			this.afterSchoolFreeTime = afterSchoolFreeTime;
			return this;
		}

		public Builder setEveningFreeTime(String eveningFreeTime) {
			this.eveningFreeTime = eveningFreeTime;
			return this;
		}

		public Builder setSchoolDay(Boolean isSchoolDay) {
			this.isSchoolDay = isSchoolDay;
			return this;
		}

		public DailyData build() {
			return new DailyData(date, isSchoolDay, day, morningWeather, eveningWeather, afterSchoolFreeTime,
					eveningFreeTime);
		}
	}
}
