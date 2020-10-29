/**
 * SupportTicket object that contatins a string of the message.
 * 
 * @author Maxwell Johnson
 *
 */
public class SupportTicket implements Comparable<SupportTicket> {
  // instance fields
  private String ticket;

  // constructor
  public SupportTicket(String ticket) {
    if (ticket == null) {
      throw (new NullPointerException("ERROR: You cannot pass a null string as the message."));
    }
    this.ticket = ticket;
  }

  // methods
  @Override
  /**
   * Compares two objects, and returns a number greater than 0 if the object is greater than the
   * other, and equal to zero if both are equal, and less than 0 if it is less than the other object
   * 
   * @param arg0 a SupportTicket object to be compared to
   */
  public int compareTo(SupportTicket arg0) {
    int leng1 = ticket.length();// first size to compare
    int leng2 = arg0.toString().length();// second size to compare

    if (leng1 != leng2) {// checks if there lengths arent equal and then subtracts their size
      return leng1 - leng2;
    } else {// otherwise compares their alphabetical order
      return -1 * ticket.compareToIgnoreCase(arg0.toString());
    }
  }

  /**
   * Returns the message from the SupportTicket object
   */
  public String toString() {
    return ticket;
  }

}
