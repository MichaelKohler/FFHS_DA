package ch.ffhs.da.uebung4;

public class Datum {

	private int tag = 0;
	private int monat = 0;
	private int jahr = 0;
	
	public Datum(int aTag, int aMonat, int aJahr) {
		this.tag = aTag;
		this.monat = aMonat;
		this.jahr = aJahr;
	}
	
	public int getTag() {
		return this.tag;
	}
	
	public int getMonat() {
		return this.monat;
	}
	
	public int getJahr() {
		return this.jahr;
	}
	
	/*
	 * bestimmt Datum des Folgetages
	 */
	public Datum next() {
		int tag = this.tag;
		int monat = this.monat;
		int jahr = this.jahr;
		
		if (tag + 1 <= 30) {
			return new Datum(tag + 1, monat, jahr);
		}
		else {
			if (monat + 1 <= 12) {
				return new Datum(1, monat + 1, jahr);
			}
			else {
				return new Datum(1, 1, jahr + 1);
			}
		}
	}
	
	/*
	 * bestimmt Datum des vorhergehenden Tages
	 */
    public Datum previous() {
    	int tag = this.tag;
		int monat = this.monat;
		int jahr = this.jahr;
		
		if (tag - 1 >= 1) {
			return new Datum(tag - 1, monat, jahr);
		}
		else {
			if (monat - 1 >= 1) {
				return new Datum(30, monat - 1, jahr);
			}
			else {
				return new Datum(30, 12, jahr - 1);
			}
		}
	}
    
    /*
     * addiert eine Anzahl Tage zu vorgegebenem Datum
     */
    public Datum plus(int aTage) {
    	Datum date = new Datum(this.tag, this.monat, this.jahr);
    	int counter = aTage;
    	while (counter > 0) {
    		date = date.next();
    		counter--;
    	}
    	return date;
	}
    
    /*
     * ermittelt Differenz in Tagen zwischen zwei Datumsangaben
     */
    public int between(Datum aDatum) {
    	Datum date = new Datum(this.tag, this.monat, this.jahr);
    	int counter = 0;
    	while (!date.equal(aDatum)) {
    		if (date.is_before(aDatum)) {
    			date = date.next();
    		}
    		else {
    			date = date.previous();
    		}
    		counter++;
    	}
    	return counter;
	}
    
    /*
     * Prädikat zum Vergleich zweier Datumsangaben
     */
    public boolean is_before(Datum aDatum) {
    	boolean isBefore = true;
    	
    	if (this.jahr > aDatum.jahr) {
    		isBefore = false;
    	}
    	else if (this.jahr == aDatum.jahr) {
    		if (this.monat > aDatum.monat) {
    			isBefore = false;
    		}
    		else if (this.monat == aDatum.monat){
    			if (this.tag > aDatum.tag) {
    				isBefore = false;
    			}
    			else if (this.tag == aDatum.tag) {
    				isBefore = false;
    			}
    		}
    	}
    	
    	return isBefore;
	}
    
    /*
     * Prädikat zur Feststellung der Gleichheit zweier Datumsangaben
     */
    public boolean equal(Datum aDatum) {
    	boolean isEqual = this.tag == aDatum.tag && this.monat == aDatum.monat && this.jahr == aDatum.jahr;
    	return isEqual;
	}

}