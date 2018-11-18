package adressbuch;

import java.io.Serializable;

/**
 * Die Kontaktdaten fuer einen Eintrag in einem Adressbuch: Name, Adresse,
 * Email.
 *
 * @author David J. Barnes und Michael Koelling.
 */
public class Kontakt implements Comparable<Kontakt> {

    private String name;
    private String telefon;
    private String email;

    /**
     * Lege Kontaktdaten an. Bei allen Angaben werden umgebende Leerzeichen
     * entfernt. Entweder der Name oder die Telefonnummer darf nicht leer sein.
     *
     * @param name der Name.
     * @param telefon die Telefonnummer.
     * @param email die Email-Adresse.
     */
    public Kontakt(String name, String telefon, String email) {
        // Leere Strings verwenden, wenn einer der Parameter null ist.
        if (name == null) {
            name = "";
        }
        if (telefon == null) {
            telefon = "";
        }
        if (email == null) {
            email = "";
        }
        this.name = name.trim();
        this.telefon = telefon.trim();
        this.email = email.trim();
        if(this.name.equals("") && this.telefon.equals("")) 
            throw new IllegalStateException("Name und Telefonnummer dürfen nicht beide leer sein.");
    }

    String gibKontaktString() {
        return name + ":" + telefon + ":" + email;
    }
    
    /**
     * @return den Namen.
     */
    public String getName() {
        return name;
    }

    public void setName(String neu) {
	if(telefon.equals("") && neu.trim().equals("")) throw new IllegalStateException("Name und Telefonnummer dürfen nicht beide leer sein.");

        this.name = neu.trim();
    }

    /**
     * @return die Telefonnummer.
     */
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String neu) {
	if(name.equals("") && neu.trim().equals("")) throw new IllegalStateException("Name und Telefonnummer dürfen nicht beide leer sein.");
        this.telefon = neu.trim();
    }

    /**
     * @return die Adresse.
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String neu) {
        this.email = neu;
    }

    /**
     * Teste dieses und jenes Objekt auf Datengleichheit.
     *
     * @param jenes Das Objekt, das mit diesem verglichen werden soll.
     * @return true wenn das Parameterobjekt ein Kontakt ist und sich die
     * Datenfelder paarweise gleichen.
     */
    public boolean equals(Object jenes) {
        if (this == jenes) {
            return true;
        }
        if (jenes == null) {
            return false;
        }
        if (!(jenes instanceof Kontakt)) {
            return false;
        }
        Kontakt jenerKontakt = (Kontakt) jenes;
        return compareTo(jenerKontakt) == 0;
    }

    /**
     * @return einen mehrzeiligen String mit Name, Telefon und Email.
     */
    public String toString() {
        //return name + "\n" + telefon + "\n" + email;
        return name + " -- " + telefon + " -- " +email;
    }

    /**
     * Vergleiche diesen Kontakt mit einem anderen, damit sortiert werden kann.
     * Kontakte werden nach Name, Telefonnummer und Adresse sortiert.
     *
     * @param jenerKontakt der Kontakt, mit dem verglichen werden soll.
     * @return einen negativen Wert, wenn dieser Kontakt vor dem Parameter
     * liegt, Null, wenn sie gleich sind, und einen positiven Wert, wenn dieser
     * Kontakt nach dem Parameter folgt.
     */
    public int compareTo(Kontakt jenerKontakt) {
        int vergleich = name.compareTo(jenerKontakt.getName());
        if (vergleich == 0) {
            vergleich = telefon.compareTo(jenerKontakt.getTelefon());
        }
        if (vergleich == 0) {
            vergleich = email.compareTo(jenerKontakt.getEmail());
        }
        return vergleich;
    }

    /**
     * Berechne einen Hashcode nach den Regeln des Buches "Effektiv Java
     * programmieren" von Joshua Bloch.
     *
     * @return einen Hashcode fuer diesen Kontakt.
     */
    public int hashCode() {
        int code = 17;
        code = 37 * code + name.hashCode();
        code = 37 * code + telefon.hashCode();
        code = 37 * code + email.hashCode();
        return code;
    }

}
