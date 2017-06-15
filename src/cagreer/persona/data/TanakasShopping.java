package cagreer.persona.data;

import java.sql.Date;

public class TanakasShopping {
	private Date date;
	private String offer, price;

	private TanakasShopping(Date date, String offer, String price) {
		this.date = date;
		this.offer = offer;
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public String getOffer() {
		return offer;
	}

	public String getPrice() {
		return price;
	}

	public static class Builder {
		private Date date;
		private String offer, price;

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setOffer(String offer) {
			this.offer = offer;
			return this;
		}

		public Builder setPrice(String price) {
			this.price = price;
			return this;
		}

		public TanakasShopping build() {
			return new TanakasShopping(date, offer, price);
		}
	}
}
