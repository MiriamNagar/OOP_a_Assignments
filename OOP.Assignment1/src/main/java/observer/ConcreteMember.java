/**
 * This class implements the Member interface
 * it constitutes a Member that needs to be notified
 * @author Masuah Shani & Miriam Nagar
 * @version 1.0 29 Dec 2022
 */
package observer;

import java.util.ArrayList;

public class ConcreteMember implements Member{
    private ArrayList <UndoableStringBuilder> state;

    /**
     * constructs new list to contain states(every update)
     */
    public ConcreteMember()
    {
        state = new ArrayList<>();
    }

    /**
     * update the member and adds the update to the states list
     * @param usb new update
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        if(usb != null) {
            state.add(usb);
        }
    }

    /**
     * this method is used for testing the other methods in the Test class
     * @return last obj in states list
     */
    public String getLast()
    {
        if(state.size() != 0) {
            return state.get(state.size() - 1).getString();
        }
        return null;
    }

    /**
     * this method is used for testing the other methods in the Test class
     * @return second to last obj in states list
     */
    public String secondToLast()
    {
        if(state.size() > 1) {
            return state.get(state.size() - 2).getString();
        }
        return null;
    }

}
