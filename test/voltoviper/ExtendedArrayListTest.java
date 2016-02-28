package voltoviper;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * @author Christoph Nebendahl
 */
public class ExtendedArrayListTest {

    @Test
    public void ExtendedListTest(){
        TestList list = new TestList();
        String s1 = new String("Testing");
        Integer i1 = new Integer(25);
        Boolean b1 = new Boolean(true);
        Object[] object = {s1,i1,b1};
        list.add(s1,i1,b1);

        String s2 = new String("Testing 2");
        Integer i2 = new Integer(500);
        Boolean b2 = new Boolean(false);
        Object[] object1 = {s2,i2,b2};
        list.add(s2,i2,b2);

        assertEquals("Objects are not equal", object[0], list.get(0)[0]);
        assertEquals("Objects are not equal", object[1], list.get(0)[1]);
        assertEquals("Objects are not equal", object[2], list.get(0)[2]);

        assertEquals("Objects are not equal", object1[0], list.get(1)[0]);
        assertEquals("Objects are not equal", object1[1], list.get(1)[1]);
        assertEquals("Objects are not equal", object1[2], list.get(1)[2]);
    }

    @Test (expected = IntegrityException.class)
    public void RemoveTest()throws IntegrityException{
        TestList list = new TestList();
        String s1 = new String("Testing");
        Integer i1 = new Integer(25);
        Boolean b1 = new Boolean(true);
        Object[] object = {s1,i1,b1};
        list.add(s1,i1,b1);

        String s2 = new String("Testing 2");
        Integer i2 = new Integer(500);
        Boolean b2 = new Boolean(false);
        Object[] object1 = {s2,i2,b2};
        list.add(s2,i2,b2);

        Method method = null;
        ArrayList<String> returnlist = null;
        try {
            method = ExtendedArrayList.class.getDeclaredMethod("getDataList1");
            method.setAccessible(true);
            returnlist = (ArrayList<String>) method.invoke(list);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



        returnlist.remove(1);
        list.check();
    }
}
