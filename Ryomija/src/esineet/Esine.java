package esineet;

/**Rajapinta, jonka pohjalta luodaan pelissä käytettävät esineet.
 * 
 */
public interface Esine {
    
    public void kayta();
    public char getSymboli();
    @Override
    public String toString();
}
