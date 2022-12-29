import observer.ConcreteMember;
import org.junit.jupiter.api.Test;

import observer.GroupAdmin;
import observer.UndoableStringBuilder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GroupAdminAndConcreteMemberTest {

    @Test
    public void testRegister(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();
        GroupAdmin ga = new GroupAdmin(states);

        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();
        ConcreteMember cm3 = null;

        states.add(new UndoableStringBuilder("start"));

        ga.register(cm1);
        assertEquals(true, ga.inMemberList(cm1));
        assertEquals(false, ga.inMemberList(cm2));
        assertEquals(false, ga.inMemberList(cm3));
    }

    @Test
    public void testUnregister(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();
        GroupAdmin ga = new GroupAdmin(states);

        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();

        states.add(new UndoableStringBuilder("start"));

        ga.register(cm1);
        ga.register(cm2);

        ga.unregister(cm1);
        assertEquals(false, ga.inMemberList(cm1));
        assertEquals(true, ga.inMemberList(cm2));
    }

    //tests for GroupAdmin
    //notifyObservers method is used in all other methods therefore it's already tested in the other tests
    @Test
    public void GATestAppend(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();
        GroupAdmin ga = new GroupAdmin(states);

        ga.append("start");
        assertEquals("start", ga.returnLast());

        ga.append(" go");
        ga.append("");
        assertNotEquals(ga.returnLast(), ga.returnSecondToLast());
    }

    @Test
    public void GATestDelete(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();

        GroupAdmin ga = new GroupAdmin(states);

        states.add(new UndoableStringBuilder("don't start"));

        ga.delete(0, 6);
        assertEquals("start", ga.returnLast());

        ga.append(" now");
        ga.delete(8,11);
        assertNotEquals(ga.returnLast(), ga.returnSecondToLast());
    }

    @Test
    public void GATestInsert(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();

        GroupAdmin ga = new GroupAdmin(states);

        ga.append("ready");
        ga.insert(0,"not ");
        assertEquals("not ready", ga.returnLast());

        ga.insert(0,"");
        assertNotEquals(ga.returnLast(), ga.returnSecondToLast());
    }

    @Test
    public void GATestUndo(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();

        GroupAdmin ga = new GroupAdmin(states);

        ga.append("ready start");;

        ga.delete(0,6);
        assertEquals("start", ga.returnLast());
        ga.undo();
        ga.undo();
        assertEquals("", ga.returnLast());
        ga.undo();
        assertNotEquals(ga.returnLast(), ga.returnSecondToLast());
    }

    /***/
    //test for ConcreteMember
    //the 'update' method in class ConcreteMember is used in the notifyObservers method
    @Test
    public void testAppend(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();
        GroupAdmin ga = new GroupAdmin(states);
        states.add(new UndoableStringBuilder("start"));

        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();

        ga.register(cm1);
        ga.register(cm2);
        ga.append(" ready");
        assertEquals("start ready", cm1.getLast());
        assertEquals("start ready", cm2.getLast());

        ga.append(" go");
        ga.append("");
        assertNotEquals(cm1.getLast(), cm1.secondToLast());
    }

    @Test
    public void testDelete(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();

        GroupAdmin ga = new GroupAdmin(states);

        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();

        states.add(new UndoableStringBuilder("don't start"));

        ga.register(cm1);
        ga.register(cm2);
        ga.delete(0, 6);
        assertEquals("start", cm1.getLast());
        ga.unregister(cm2);
        assertEquals("start", cm2.getLast());

        ga.append(" now");
        ga.delete(8,9);
        assertNotEquals(cm1.getLast(), cm1.secondToLast());
    }

    @Test
    public void testInsert(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();

        GroupAdmin ga = new GroupAdmin(states);

        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();

        ga.register(cm1);
        ga.register(cm2);
        ga.append("ready");
        ga.unregister(cm2);
        ga.insert(0,"not ");
        assertEquals("not ready", cm1.getLast());
        assertEquals("ready", cm2.getLast());

        ga.insert(0,"");
        assertNotEquals(cm1.getLast(), cm1.secondToLast());
    }

    @Test
    public void testUndo(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();

        GroupAdmin ga = new GroupAdmin(states);

        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();

        states.add(new UndoableStringBuilder("ready start"));

        ga.register(cm1);
        ga.register(cm2);
        ga.delete(0,6);
        assertEquals("start", cm1.getLast());
        assertEquals("start", cm2.getLast());
        ga.unregister(cm1);
        ga.undo();
        assertEquals("start", cm1.getLast());
        assertEquals("ready start", cm2.getLast());
    }

}
