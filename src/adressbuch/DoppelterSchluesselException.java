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
public class DoppelterSchluesselException extends UngueltigerSchluesselException {

    public DoppelterSchluesselException(String schluessel) {
        super(schluessel);
    }
    
    public String toString(){
        return "Den Schlüssel '" + gibSchluessel() + "' gibt es im Adressbuch bereits.";
    }
    
}
