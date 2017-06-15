package cagreer.persona.data;
import java.sql.Date;

public class DailyAvailability {
	private Date date;
	private String magician, chariot, priestess, emperor, lovers, fortune, strength, sun, moon, hangedMan, death,
			temperance, hermit, empress, hierophant, justice, devil, tower;

	private DailyAvailability(Date date, String magician, String chariot, String priestess, String emperor,
			String lovers, String fortune, String strength, String sun, String moon, String hangedMan, String death,
			String temperance, String hermit, String empress, String hierophant, String justice, String devil,
			String tower) {
		this.date = date;
		this.magician = magician;
		this.chariot = chariot;
		this.priestess = priestess;
		this.emperor = emperor;
		this.lovers = lovers;
		this.fortune = fortune;
		this.strength = strength;
		this.sun = sun;
		this.moon = moon;
		this.hangedMan = hangedMan;
		this.death = death;
		this.temperance = temperance;
		this.hermit = hermit;
		this.empress = empress;
		this.hierophant = hierophant;
		this.justice = justice;
		this.devil = devil;
		this.tower = tower;
	}

	public Date getDate() {
		return date;
	}

	public String getMagician() {
		return magician;
	}

	public String getChariot() {
		return chariot;
	}

	public String getPriestess() {
		return priestess;
	}

	public String getEmperor() {
		return emperor;
	}

	public String getLovers() {
		return lovers;
	}

	public String getFortune() {
		return fortune;
	}

	public String getStrength() {
		return strength;
	}

	public String getSun() {
		return sun;
	}

	public String getMoon() {
		return moon;
	}

	public String getHangedMan() {
		return hangedMan;
	}

	public String getDeath() {
		return death;
	}

	public String getTemperance() {
		return temperance;
	}

	public String getHermit() {
		return hermit;
	}

	public String getEmpress() {
		return empress;
	}

	public String getHierophant() {
		return hierophant;
	}

	public String getJustice() {
		return justice;
	}

	public String getDevil() {
		return devil;
	}

	public String getTower() {
		return tower;
	}

	public static class Builder {
		private Date date;
		private String magician, chariot, priestess, emperor, lovers, fortune, strength, sun, moon, hangedMan, death,
				temperance, hermit, empress, hierophant, justice, devil, tower;

		public Builder setDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder setMagicianAvailability(String magician) {
			this.magician = magician;
			return this;
		}

		public Builder setChariotAvailability(String chariot) {
			this.chariot = chariot;
			return this;
		}

		public Builder setPriestessAvailability(String priestess) {
			this.priestess = priestess;
			return this;
		}

		public Builder setEmperorAvailability(String emperor) {
			this.emperor = emperor;
			return this;
		}

		public Builder setLoversAvailability(String lovers) {
			this.lovers = lovers;
			return this;
		}

		public Builder setFortuneAvailability(String fortune) {
			this.fortune = fortune;
			return this;
		}

		public Builder setStrengthAvailability(String strength) {
			this.strength = strength;
			return this;
		}

		public Builder setSunAvailability(String sun) {
			this.sun = sun;
			return this;
		}

		public Builder setMoonAvailability(String moon) {
			this.moon = moon;
			return this;
		}

		public Builder setHangedManAvailability(String hangedMan) {
			this.hangedMan = hangedMan;
			return this;
		}

		public Builder setDeathAvailability(String death) {
			this.death = death;
			return this;
		}

		public Builder setTemperanceAvailability(String temperance) {
			this.temperance = temperance;
			return this;
		}

		public Builder setHermitAvailability(String hermit) {
			this.hermit = hermit;
			return this;
		}

		public Builder setEmpressAvailability(String empress) {
			this.empress = empress;
			return this;
		}

		public Builder setHierophantAvailability(String hierophant) {
			this.hierophant = hierophant;
			return this;
		}

		public Builder setJusticeAvailability(String justice) {
			this.justice = justice;
			return this;
		}

		public Builder setDevilAvailability(String devil) {
			this.devil = devil;
			return this;
		}

		public Builder setTowerAvailability(String tower) {
			this.tower = tower;
			return this;
		}

		public DailyAvailability build() {
			return new DailyAvailability(date, magician, chariot, priestess, emperor, lovers, fortune, strength, sun,
					moon, hangedMan, death, temperance, hermit, empress, hierophant, justice, devil, tower);
		}
	}
}