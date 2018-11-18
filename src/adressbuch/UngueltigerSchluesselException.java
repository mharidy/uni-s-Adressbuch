/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adressbuch;

/**
 *
 * @author beuth
 */
public class UngueltigerSchluesselException extends Exception {

    private String schluessel;

    public UngueltigerSchluesselException(String schluessel) {
        this.schluessel = schluessel;
    }

    public String gibSchluessel() {
        return schluessel;
    }

    public String toString() {
        return "Der Schlüssel '"
                + schluessel + "' ist ungültig.";
    }
}
