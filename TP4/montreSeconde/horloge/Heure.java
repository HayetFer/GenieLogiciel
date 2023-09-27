package montreSeconde.horloge;

import java.time.LocalTime;

public class Heure {
	private int heure;
        private int minute;
        private int seconde;
	
	public Heure(LocalTime heureJava) {
		heure = heureJava.getHour();
		minute = heureJava.getMinute();
		seconde = heureJava.getSecond();
	}
    public Heure(int heure, int minute, int seconde) {
		super();
		this.heure = heure;
		this.minute = minute;
		this.seconde = seconde;
	}
	
	public int getHeure() {
		return heure;
	}
	public void setHeure(int heure) {
		this.heure = heure;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
        public int getSeconde() {
		return seconde;
	}
	public void setSeconde(int seconde) {
		this.seconde = seconde;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + heure;
		result = prime * result + minute;
		result = prime * result + seconde;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Heure other = (Heure) obj;
		if (heure != other.heure)
			return false;
		if (minute != other.minute)
			return false;
		if (seconde != other.seconde)
		        return false;
		return true;
	}
	
	
}
