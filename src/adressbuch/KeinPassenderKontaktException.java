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
public class KeinPassenderKontaktException extends UngueltigerSchluesselException {

    public KeinPassenderKontaktException(String schluessel) {
        super(schluessel);
    }

    public String toString(){
        return "Zu dem Schlüssel '" + gibSchluessel() + "' gibt es keinen Kontakt.";
    }

}
