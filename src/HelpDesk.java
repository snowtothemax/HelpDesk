//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: A HelpDesk that contains an array of SupportTicket objects organized into a MaxHeap
// Files: SupportTicket.java, HelpDeskInterface.java, HelpDeskTestSuite.java
// Course: CS 300, Spring, 2019
//
// Author: Maxwell Johnson
// Email: mkjohnson9@wisc.edu
// Lecturer's Name: (name of your lecturer)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: NONE
// Online Sources: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * A HelpDesk of SupportTicket array. Organized in a Max Heap
 * 
 * @author Maxwell Johnson
 *
 */
public class HelpDesk implements HelpDeskInterface {
  // Instance fields
  protected SupportTicket[] array; // zero-indexed max-heap
  protected int size;

  // constructor
  /**
   * Constructs a HelpDesk object and initializes it with an array size
   * 
   * @param cap
   */
  public HelpDesk(int cap) {
    array = new SupportTicket[cap];
    size = 0;
  }

  @Override
  /**
   * Creates and adds a new SupportTicket to this HelpDesk.
   * 
   * @param message names the client and describes their need for support.
   * @throws NullPointerException      when the String message argument is null.
   * @throws IndexOutOfBoundsException when called on HelpDesk with a full array
   */
  public void createNewTicket(String message) {
    // checks if the message is null and throws an exception if so
    if (message == null) {
      throw (new NullPointerException("ERROR: Input message cannot be null."));
    }
    // checks if the array is full and throws an exception if so
    if (size == array.length) {
      throw (new IndexOutOfBoundsException("ERROR: The HelpDesk is full."));
    }
    // sets the root equal to the new ticket if the size is 0
    if (size == 0) {
      array[0] = new SupportTicket(message);
      size++;
      return;
    }

    // adds a new ticket to the heap and propogates up from that index to where it is added
    size++;
    SupportTicket ticket = new SupportTicket(message);
    array[size - 1] = ticket;
    propagateUp(size - 1);
  }

  @Override
  /**
   * Returns the message within this HelpDesk that has the highest priority. This method does not
   * change the state of this HelpDesk.
   * 
   * @return the message within the highest priority SupportTicket.
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  public String checkNextTicket() {
    if (size == 0) {
      throw (new IllegalStateException("ERROR: There are no SupportTickets."));
    }
    return array[0].toString();
  }

  @Override
  /**
   * Returns and removes the message within this HelpDesk that has the highest priority.
   * 
   * @return the message within the highest priority SupportTicket (prior to its removal).
   * @throws IllegalStateException when called on a HelpDesk with zero SupportTickets.
   */
  public String closeNextTicket() {
    // checks if the size is 0 and throws an exception if so
    if (size == 0) {
      throw (new IllegalStateException("ERROR: There are no SupportTickets."));
    }

    // sets the ticket at the bottom of the array equal to the 0th index and propgates down from
    // there, inherently removing the root
    String ret = array[0].toString();
    array[0] = array[size - 1];
    propagateDown(0);
    size--;
    return ret;
  }

  /**
   * Given an index into the heap array, this method returns that index's parent index.
   * 
   * @param index
   * @return the index of the parent
   */
  protected static int parentOf(int index) {
    if (index == 2) {
      return 0;
    }
    return (index - 1) / 2;
  }

  /**
   * Given an index into the heap array, this method returns that index's left child index.
   * 
   * @param index
   * @return the index of the left child
   */
  protected static int leftChildOf(int index) {
    return 2 * index + 1;
  }

  /**
   * Given an index into the heap array, this method returns that index's right child index.
   * 
   * @param index
   * @return the index of the right child
   */
  protected static int rightChildOf(int index) {
    return 2 * index + 2;
  }

  /**
   * Given two indexes into the heap array, this method swaps the SupportTickets at those indexes.
   * 
   * @param indexA
   * @param indexB
   */
  protected void swap(int indexA, int indexB) {
    SupportTicket holder = array[indexA];
    array[indexA] = array[indexB];
    array[indexB] = holder;
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and the heap's root.
   * 
   * @param index
   */
  protected void propagateUp(int index) {
    propUpHelper(index);
  }

  /**
   * Given an index into the heap array, this method recursively swaps any SupportTickets necessary
   * to enforce the heap's order property between this index and it's children.
   * 
   * @param index
   */
  protected void propagateDown(int index) {
    propDownHelper(index);
  }


  // helpers
  /**
   * Propagates up from the current input index
   * 
   * @param index
   */
  private void propUpHelper(int index) {
    // checks if the array is empty and throws an exception if so
    if (size == 0) {
      throw (new IllegalStateException("ERROR: You cannot propagate up in this state."));
    }
    // if the index input is less than 0, nothing happens
    if (index < 1) {
      return;
    }
    // gets the parent of the current index, and checks if the string at the current index is larger
    // than the parent
    int parent = parentOf(index);
    if (array[index].compareTo(array[parent]) > 0) {
      swap(index, parent);// swaps the current index with the parent
    }
    propUpHelper(parent);// recursively calls propUpHelper on the parent
  }

  /**
   * Propagates down from the index given
   * 
   * @param index
   */
  private void propDownHelper(int index) {
    // checks if the given index is inbounds and throws an exception if not
    if (index < 0 || index > size) {
      throw (new IllegalStateException("ERROR: That index does not exist."));
    }

    // creates integers for indeces of a maximum value and for the left and right childs
    int maxVal = index;
    int leftChild = leftChildOf(index);
    int rightChild = rightChildOf(index);

    // compares the messages of the maxVal and the children in the HelpDesk array and sets the
    // maxVal equal to the largest string
    if (leftChild < size && array[maxVal].toString().compareTo(array[leftChild].toString()) <= 0) {
      maxVal = leftChild;
    }
    if (rightChild < size
        && array[maxVal].toString().compareTo(array[rightChild].toString()) <= 0) {
      maxVal = rightChild;
    }

    // if the maxVal isnt the index input, it swaps the maxval with the current index and then
    // recursively calls the method on the maxval
    if (maxVal != index) {
      swap(maxVal, index);
      propDownHelper(maxVal);
    }
  }

}
