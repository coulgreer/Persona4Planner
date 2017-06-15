package cagreer.persona.data;

import java.sql.Date;

public class PhoneEvent {
	private Date date;
	private String initiatingSocialLink, involvedSocialLink, book, keyResponses, status;

	private PhoneEvent(Date date, String initiatingSocialLink, String involvedSocialLink, String book,
			String keyResponses, String status) {
		this.date = date;
		this.initiatingSocialLink = initiatingSocialLink;
		this.involvedSocialLink = involvedSocialLink;
		this.book = book;
		this.keyResponses = keyResponses;
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public String getInitiatingSocialLink() {
		return initiatingSocialLink;
	}

	public String getInvolvedSocialLink() {
		return involvedSocialLink;
	}

	public String getBook() {
		return book;
	}

	public String getKeyResponse() {
		return keyResponses;
	}

	public String getStatus() {
		return status;
	}

	public static class Builder {
		private Date date;
		private String initiatingSocialLink, involvedSocialLink, book, keyResponses, status;

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setInitiatingSocialLink(String initiatingSocialLink) {
			this.initiatingSocialLink = initiatingSocialLink;
			return this;
		}

		public Builder setInvolvedSocialLink(String involvedSocialLink) {
			this.involvedSocialLink = involvedSocialLink;
			return this;
		}

		public Builder setBook(String book) {
			this.book = book;
			return this;
		}

		public Builder setKeyResponses(String keyResponses) {
			this.keyResponses = keyResponses;
			return this;
		}

		public Builder setStatus(String status) {
			this.status = status;
			return this;
		}

		public PhoneEvent build() {
			return new PhoneEvent(date, initiatingSocialLink, involvedSocialLink, book, keyResponses, status);
		}
	}
}
