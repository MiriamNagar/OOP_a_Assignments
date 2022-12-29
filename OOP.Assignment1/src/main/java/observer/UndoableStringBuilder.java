/**
 * This class enable the use of certain functions in stringbuilder with the option
 * to undo changes done to the current string
 * @author Masuah Shani & Miriam Nagar
 * @version 2.0 29 Dec 2022
 */
package observer;

import java.util.Stack;

public class UndoableStringBuilder {
    private StringBuilder  current;
    private Stack<StringBuilder> undo = new Stack<>();

    /**
     * Constructs a string builder with no characters in it and an initial capacity of 16 characters.
     * Creating new StringBuilder to execute the methods on
     */
    public UndoableStringBuilder(){
        current = new StringBuilder(); //in case str==null than stringbuilder class will handle it
    }

    /**
     * Constructs a string builder initialized to the contents of the specified string.
     * @param input the content put into the new StringBuilder
     */
    public UndoableStringBuilder(String input){
        current = new StringBuilder(input); //in case str==null than stringbuilder class will handle it
    }

    /**
     * Constructs a UndoableStringBuilder with same value in input
     * @param input the UndoableStringBuilder we copy
     */
    public UndoableStringBuilder(UndoableStringBuilder input) {
        current = new StringBuilder(input.current); //in case str==null than stringbuilder class will handle it
        this.undo = (Stack<StringBuilder>) input.undo.clone();
    }

    /**
     * @return the String value of the UndoableStringBuilder
     */
    public String getString(){
        return current.toString();
    }

    /**
     * Appends the specified string to this character sequence. Saves the string prior to the change in undo stack
     * @param str - the string added to the already existing string current;
     * @return this Object (with changes done to the param current)
     */
    public UndoableStringBuilder append(String str){
        undo.add(new StringBuilder(current));
        current.append(str);
        return this;
    }

    /**
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * Saves the string prior to the change in undo stack
     * @param start The beginning index, inclusive.
     * @param end The ending index, exclusive.
     * @return this Object (with changes done to the param current)
     * if unable to delete return this object without change
     */
    public UndoableStringBuilder delete(int start, int end){
        if(start < 0 || start > current.length() ||  start > end) return this;
        undo.add(new StringBuilder(current));
        current.delete(start, end);
        return this;
    }

    /**
     * Inserts the string into this character sequence.
     * Saves the string prior to the change in undo stack
     * The characters of the String argument are inserted, in order, into this sequence at the indicated offset,
     * moving up any characters originally above that position and increasing
     * the length of this sequence by the length of the argument.
     * If str is null, then the four characters "null" are inserted into this sequence.
     * The offset argument must be greater than or equal to 0, and less than or equal to the length of this sequence.
     * @param offset the offset.
     * @param str a String
     * @return this Object (with changes done to the param current)
     * if unable to insert return this object without change
     */
    public UndoableStringBuilder insert(int offset, String str){
        if(offset < 0 || offset > current.length()) return this;
        undo.add(new StringBuilder(current));
        current.insert(offset, str);
        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String. The substring begins at the specified start and
     * extends to the character at index end - 1 or to the end of the sequence if
     * no such character exists. First the characters in the substring are removed
     * and then the specified String is inserted at start. (This sequence will be
     * lengthened to accommodate the specified String if necessary).
     * Saves the string prior to the change in undo stack.
     * @param start The beginning index, inclusive.
     * @param end The ending index, exclusive.
     * @param str String that will replace previous contents.
     * @return this Object (with changes done to the param current)
     * if unable to replace return this object without change
     */ //if start is negative, greater than length(), or greater than end.
    public UndoableStringBuilder replace(int start,int end, String str){
        if(start < 0 || start > current.length() || start > end) return this;
        undo.add(new StringBuilder(current));
        current.replace(start, end, str);
        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the
     * sequence.
     * @return this Object (with changes done to the param current)
     */
    public UndoableStringBuilder reverse(){
        undo.add(new StringBuilder(current));
        current.reverse();
        return this;
    }

    /**
     * Erases the last change done to the String, reverting it to an older state.
     */
    public void undo(){
        if(!undo.empty())current = undo.pop();
    }

    /**
     * Returns a string representing the data in this sequence.
     * A new String object is allocated and initialized to contain the character sequence currently represented by this object.
     * This String is then returned. Subsequent changes to this sequence do not affect the contents of the String.
     * @return a string representation of this sequence of characters.
     */
    public String toString(){
        return "" + this.current;
    }

    /**
     * checks if two UndoableStringBuilder contain the same String
     * @param obj UndoableStringBuilder we compare to
     * @return boolean -if two UndoableStringBuilder have the same string
     */
    public boolean equals(UndoableStringBuilder obj) {
        String s1 = this.toString();
        String s2 = obj.toString();
        return s1.equals(s2);
    }
}
