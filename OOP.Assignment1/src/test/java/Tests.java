import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    // stub method to check external dependencies compatibility
    @Test
    public void testGroupAdminAndConcreteMember(){
        ArrayList<UndoableStringBuilder> states = new ArrayList<>();
        GroupAdmin ga = new GroupAdmin(states);

        ConcreteMember cm1 = new ConcreteMember();
        ConcreteMember cm2 = new ConcreteMember();
        ga.register(cm1);
        ga.register(cm2);


        //Shallow and deep size footprint of ConcreteMember,return textual representation of the footprint
        System.out.println(JvmUtilities.objectFootprint(cm1));
        //Shallow and deep size footprint of GroupAdmin,return textual representation of the footprint
        System.out.println(JvmUtilities.objectFootprint(ga));

        //computes the total (deep) size of ConcreteMember
        System.out.println(JvmUtilities.objectTotalSize(cm1)+"\n");
        //computes the total (deep) size of GroupAdmin
        System.out.println(JvmUtilities.objectTotalSize(ga)+"\n");

        ga.append("start");
        ga.delete(0,1);
        //Shallow and deep size footprint of ConcreteMember after adding values, return textual representation of the footprint
        System.out.println(JvmUtilities.objectFootprint(cm1));
        //Shallow and deep size footprint of GroupAdmin after adding values, return textual representation of the footprint
        System.out.println(JvmUtilities.objectFootprint(ga));

        //computes the total (deep) size of ConcreteMember after adding values
        System.out.println(JvmUtilities.objectTotalSize(cm1)+"\n");
        //computes the total (deep) size of GroupAdmin after adding values
        System.out.println(JvmUtilities.objectTotalSize(ga)+"\n");

        //JVM's process id, total memory allocated at the beginning of the
        //program as well as the available number of cores
        System.out.println(JvmUtilities.jvmInfo());

    }

}
