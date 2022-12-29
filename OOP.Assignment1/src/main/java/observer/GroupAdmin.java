/**
 * This class notify members for every change done to the states list
 * @author Masuah Shani & Miriam Nagar
 * @version 1.0 29 Dec 2022
 */
package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender{

    private ArrayList<UndoableStringBuilder> states; //contains al states
    private ArrayList<Member> members; //contains members to notify

    /**
     * Constructs new list for members and new list for states
     * puts initial empty state in stack
     */
    public GroupAdmin()
    {
        states= new ArrayList<>();
        members = new ArrayList<>();
        UndoableStringBuilder u = new UndoableStringBuilder("");
        states.add(u);
    }

    /**
     * Constructs new list for members and receives list for states
     * if the list received is empty puts initial empty state in stack
     * @param s - UndoableStringBuilder list of states
     */
    public GroupAdmin(ArrayList<UndoableStringBuilder> s)
    {
        states= s;
        members = new ArrayList<>();
        if(s.size() == 0) {
            UndoableStringBuilder u = new UndoableStringBuilder("");
            states.add(u);
        }
    }

    /**
     * adds Member obj into the members list if not already in list and is not null
     * @param obj Member to be added to the states list
     */
    @Override
    public void register(Member obj) {
        if(obj != null && !members.contains(obj)) {
            members.add(obj);
        }
    }

    /**
     * remove Member obj from the members list if it's not null and is in members list
     * @param obj Member to remove from states list
     */
    @Override
    public void unregister(Member obj) {
        if(obj != null && members.contains(obj)) {
            members.remove(obj);
        }
    }

    /**
     * this method is used for testing the other methods in the Test class
     * method that returns true if Member m is in the members list
     * @param m the Member we check if it's in the members list
     * @return if member is in members list or not
     */
    public boolean inMemberList(Member m){
        return members.contains(m);
    }

    /**
     * this method is used for testing the other methods in the Test class
     * method that returns the string of the last obj in the states list
     * @return String of the last obj in states
     */
    public String returnLast(){
        if(states.size() != 0){
            return states.get(states.size()-1).getString();
        }
        return null;
    }

    /**
     * this method is used for testing the other methods in the Test class
     * method that returns the string of the second to last obj in the states list
     * @return String of the second to last obj in states
     */
    public String returnSecondToLast(){
        if(states.size() > 1){
            return states.get(states.size()-2).getString();
        }
        return null;
    }

    /**
     * takes the last state in states list and insert in it chars
     * adds the new state to the states list and update all members of the new state
     * @param offset - the offset
     * @param obj a String to insert to current String
     */
    @Override
    public void insert(int offset, String obj) {
        UndoableStringBuilder u1 = new UndoableStringBuilder(states.get(states.size()-1));
        UndoableStringBuilder u2 = new UndoableStringBuilder(u1);
        u1.insert(offset, obj);
        if(u2.equals(u1)) return;
        states.add(u1);
        notifyObservers(u1);
    }

    /**
     * takes the last state in states list and append it
     * adds the new state to the states list and update all members of the new state
     * @param obj - the string added to the already existing string current;
     */
    @Override
    public void append(String obj) {
        UndoableStringBuilder u1 = new UndoableStringBuilder(states.get(states.size()-1));
        UndoableStringBuilder u2 = new UndoableStringBuilder(u1);
        u1.append(obj);
        if(u2.equals(u1)) return;
        states.add(u1);
        notifyObservers(u1);
    }

    /**
     * takes the last state in states list and delete a part of it
     * adds the new state to the states list and update all members of the new state
     * @param start The beginning index, inclusive.
     * @param end The ending index, exclusive.
     */
    @Override
    public void delete(int start, int end) {
        UndoableStringBuilder u1 = new UndoableStringBuilder(states.get(states.size()-1));
        UndoableStringBuilder u2 = new UndoableStringBuilder(u1);
        u1.delete(start, end);
        if(u2.equals(u1)) return;
        states.add(u1);
        notifyObservers(u1);
    }

    /**
     * reverting the last state to an older state and adding it to list.
     * adds the new state to the states list and update all members of the new state
     */
    @Override
    public void undo() {
        UndoableStringBuilder u1 = new UndoableStringBuilder(states.get(states.size()-1));
        UndoableStringBuilder u2 = new UndoableStringBuilder(u1);
        u1.undo();
        if(u1.equals(u2)) return;
        states.add(u1);
        notifyObservers(u1);
    }

    /**
     * updates all Registered members of new changes made to the state list
     * @param u the new state
     */
    public void notifyObservers(UndoableStringBuilder u)
    {
        for (Member m : members)
            m.update(u);
    }

}
