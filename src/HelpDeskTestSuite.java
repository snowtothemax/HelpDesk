
/**
 * Extensively tests the HelpDesk class for errors.
 * 
 * @author Maxwell Johnson
 *
 */
public class HelpDeskTestSuite extends HelpDesk {

  /**
   * Runs all of the tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("testHelpDeskChildOf(): " + testHelpDeskChildOf());
    System.out.println("testHelpDeskParentOf(): " + testHelpDeskParentOf());
    System.out.println("testHelpDeskSwap(): " + testHelpDeskSwap());
    System.out.println("testHelpDeskPropagateDown(): " + testHelpDeskPropagateDown());
    System.out.println("testHelpDeskPropagateUp(): " + testHelpDeskPropagateUp());
    System.out.println("testHelpDeskCreateNewTicket(): " + testHelpDeskCreateNewTicket());
    System.out.println("testHelpDeskCheckNextTicket(): " + testHelpDeskCheckNextTicket());
    System.out.println("testHelpDeskCloseNextTicket(): " + testHelpDeskCloseNextTicket());
  }

  /**
   * constructor for this class that is unnecesary
   * 
   * @param cap
   */
  public HelpDeskTestSuite(int cap) {
    super(cap);
  }

  /**
   * Checks if the children methods work correctly for the HelpDesk class
   * 
   * @return true if it works correctly, false otherwise
   */
  public static boolean testHelpDeskChildOf() {
    // creates a bunch of ints from the children methods
    int left = leftChildOf(0);
    int right = rightChildOf(0);
    int l2 = leftChildOf(1);
    int r3 = rightChildOf(1);

    // checks if the output from the methods is the expected output and prints an error message and
    // returns false if so
    if (left != 1) {
      System.out.println("ERROR: The left child of index 0 was not correct.");
      return false;
    }
    if (right != 2) {
      System.out.println("ERROR: The right child of index 0 was not correct.");
      return false;
    }
    if (l2 != 3) {
      System.out.println("ERROR: The left child of index 1 was not correct.");
      return false;
    }
    if (r3 != 4) {
      System.out.println("ERROR: The right child of index 1 was not correct.");
      return false;
    }
    return true;
  }

  /**
   * checks if the parentOf method works correctly for the HelpDesk class
   * 
   * @return boolean true if it works correctly, false otherwise
   */
  public static boolean testHelpDeskParentOf() {
    // creates a new HelpDesk and adds a bunch of new SupportTickets to it
    HelpDesk desk = new HelpDesk(10);
    desk.createNewTicket("A");
    desk.createNewTicket("AA");
    desk.createNewTicket("AAA");
    desk.createNewTicket("AAAA");

    // creates two integers for parents of certain indeces
    int parent = parentOf(1);
    int parent2 = parentOf(4);

    // checks if the parents return the correct output and prints an error message if not
    if (parent != 0) {
      System.out.println("ERROR: The parent of index 1 was not correct");
      return false;
    }
    if (parent2 != 1) {
      System.out.println("ERROR: The parent of index 4 was not returned correctly.");
      return false;
    }
    return true;
  }

  /**
   * Checks if the swap() method works correctly for the HelpDesk class
   * 
   * @return boolean true if it works correctly, false otherwise
   */
  public static boolean testHelpDeskSwap() {
    // creates a new HelpDesk object and creates new SupportTickets then manually adds them to the
    // array
    HelpDesk desk = new HelpDesk(10);
    desk.array = new SupportTicket[10];
    SupportTicket t1 = new SupportTicket("AAAA");
    SupportTicket t2 = new SupportTicket("BBB");
    SupportTicket t3 = new SupportTicket("CCC");
    SupportTicket t4 = new SupportTicket("DDD");
    SupportTicket t5 = new SupportTicket("EEE");
    desk.array[0] = t1;
    desk.array[1] = t2;
    desk.array[2] = t3;
    desk.array[3] = t4;
    desk.array[4] = t5;
    desk.size = 5;

    // swaps the tickets at the two indeces
    desk.swap(0, 4);
    // checks if they were swapped correctly and prints an error message if they do not swap
    // correctly
    if (desk.array[0] != t5) {
      System.out.println("ERROR: The swap did not work as expected.");
      return false;
    }
    if (desk.array[4] != t1) {
      System.out.println("ERROR: The swap did not work as expected.");
      return false;
    }

    return true;
  }

  /**
   * Tests the propagateDown() method of the HelpDesk class
   * 
   * @return
   */
  public static boolean testHelpDeskPropagateDown() {
    // creates a new HelpDesk and SupportTickets and adds them to the array manually
    HelpDesk desk = new HelpDesk(10);
    desk.array = new SupportTicket[10];
    SupportTicket t1 = new SupportTicket("AAAA");
    SupportTicket t2 = new SupportTicket("BBB");
    SupportTicket t3 = new SupportTicket("CCC");
    SupportTicket t4 = new SupportTicket("DDD");
    SupportTicket t5 = new SupportTicket("EEE");
    desk.array[0] = t5;
    desk.array[1] = t4;
    desk.array[2] = t2;
    desk.array[3] = t3;
    desk.array[4] = t1;
    desk.size = 5;

    // propagatesDown for the given array
    desk.propagateDown(0);
    // checks if the content of the array at the bottom is equal to the the expected string
    if (!desk.array[4].toString().contentEquals(t5.toString())) {
      System.out.println("ERROR: The last index of the array was not correct after propogation.");
      return false;
    }
    // try block to catch exceptions
    try {
      // propagates down from an invalid index and will return true if it catches the exception
      desk.propagateDown(-1);
      System.out.println("ERROR: The exception expected to be thrown was not.");
      return false;
    } catch (IllegalStateException e) {

    }
    return true;
  }

  /**
   * Tests the propagateUp() method from the HelpDesk class
   * 
   * @return
   */
  public static boolean testHelpDeskPropagateUp() {
    // Creates a new HelpDesk object and creates a bunch of new SupportTickets
    HelpDesk desk = new HelpDesk(10);
    desk.array = new SupportTicket[10];
    SupportTicket t1 = new SupportTicket("AAAA");
    SupportTicket t2 = new SupportTicket("BBB");
    SupportTicket t3 = new SupportTicket("CCC");
    SupportTicket t4 = new SupportTicket("DDD");
    SupportTicket t5 = new SupportTicket("EEE");
    desk.array[0] = t5;
    desk.array[1] = t4;
    desk.array[2] = t2;
    desk.array[3] = t3;
    desk.array[4] = t1;
    desk.size = 5;

    // propagates up from the last index
    desk.propagateUp(4);
    // checks if the expected index is the top priority index in the Max heap
    if (!desk.checkNextTicket().contentEquals("AAAA")) {
      System.out.println(
          "ERROR: The expected message for the 0 index of the array was incorrect after propogation.");
      return false;
    }

    // clears the array
    desk.array = new SupportTicket[10];
    desk.size = 0;

    // checks if the exception thrown is thrown correctly
    try {
      desk.propagateUp(0);
      System.out.println("ERROR: The size is zero and an exception should have been thrown.");
      return false;
    } catch (IllegalStateException e) {

    }
    return true;
  }

  /**
   * checks if the createNewTicket() method works correctly for the HelpDesk class
   * 
   * @return
   */
  public static boolean testHelpDeskCreateNewTicket() {
    // creates a new HelpDesk object
    HelpDesk desk = new HelpDesk(3);

    // tests if the exception is thrown correctly for making a new SupportTicket with a null message
    try {
      desk.createNewTicket(null);
      System.out.println("ERROR: The NullPointerException was not caught as it should have been");
      return false;
    } catch (NullPointerException e) {

    }

    // creates a few new tickets and adds them to the array
    desk.createNewTicket("AAA");
    desk.createNewTicket("ZZZ");
    desk.createNewTicket("BBB");

    // checks if the next ticket is equal to the expected next ticket
    if (!desk.checkNextTicket().contentEquals("AAA")) {
      System.out.println("ERROR: The ticket at the top was not what it was expected to be.");
      return false;
    }

    // checks if the correct error is thrown for adding a new ticket to a full array
    try {
      desk.createNewTicket("PeanutButter");
      desk.createNewTicket("Poop");
      System.out.println(
          "ERROR: The IndexOutOfBoundsException was not thrown as it was expected to be for the array being full.");
      return false;
    } catch (IndexOutOfBoundsException e) {

    }
    return true;
  }

  /**
   * Tests if the checkNextTicket() works correctly for the HelpDesk class
   * 
   * @return true if it works correcfly, false otherwise
   */
  public static boolean testHelpDeskCheckNextTicket() {
    // creates a new helpDesk object and adds new tickets to the array
    HelpDesk desk = new HelpDesk(10);
    desk.createNewTicket("Blue");
    desk.createNewTicket("Green");
    desk.createNewTicket("Yellow");

    // checks if the method returns the expected message
    if (!desk.checkNextTicket().contentEquals("Yellow")) {
      System.out.println("ERROR: The expected string to be printed was incorrect.");
      return false;
    }

    // checks if the correct exception is thrown when checking the next ticket from an empty array
    desk.size = 0;
    try {
      desk.checkNextTicket();
      System.out.println("ERROR: The expected exception was not thrown.");
      return false;
    } catch (IllegalStateException e) {

    }
    return true;
  }

  /**
   * Tests if the closeNextTicket() method works for the HelpDesk class
   * 
   * @return
   */
  public static boolean testHelpDeskCloseNextTicket() {
    // creates a new HelpDesk object and adds new tickets to the array
    HelpDesk desk = new HelpDesk(4);
    desk.createNewTicket("AAA");
    desk.createNewTicket("BBBB");
    desk.createNewTicket("BBB");

    // checks if the returns string from the method equals the expected
    if (!desk.closeNextTicket().contentEquals("BBBB")) {
      System.out.println("ERROR: The ticket closed was not what it was expected to be.");
      return false;
    }

    // checks if the size was decremented correctly for the method
    if (desk.size != 2) {
      System.out.println("ERROR: The tickets size was not decremented correctly.");
      return false;
    }
    desk.closeNextTicket();
    desk.closeNextTicket();

    // checks if the method works correctly for an empty array
    try {
      desk.closeNextTicket();
      System.out.println("ERROR: The expected exception was not thrown.");
      return false;
    } catch (IllegalStateException e) {

    }
    return true;
  }

}
