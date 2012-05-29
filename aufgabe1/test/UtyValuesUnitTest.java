/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Values.*;

/**
 *
 * @author Sven
 */
public class UtyValuesUnitTest {
    
    public UtyValuesUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void test_LengthInKm(){
        Length l1, l2;
        l1 = Values.lengthInM(10000);
        l2 = Values.lenghtInKm(10);
        
        assertEquals(l1, l2);
    }
        @Test
    public void test_SpeedInKmh(){
        //Speed speed = Values.speedInKmh(30);
        System.out.println("hallo test");
        Speed spo1,spo2,spo3,spo4,spo5;
        double mps = 30;
        spo1 = Values.speedInKmh(mps);
        spo2 = Values.speedInKmh(40);
       
        assertEquals(Values.speedInKmh(mps), Values.speedInKmh(mps));
        assertEquals(Values.speedInKmh(mps+mps),spo1.add(spo1) );
        assertEquals(Values.speedInKmh(mps-mps),spo1.sub(spo1));
        assertEquals(Values.speedInKmh(mps*2),spo1.mul(2d));
        assertEquals(Values.speedInKmh(mps/2),spo1.div(2d));
        
        assertTrue(spo2.compareTo(spo1) == 1);
        assertTrue(spo2.compareTo(spo2) == 0);
        assertTrue(spo1.compareTo(spo2) == -1);
        
        assertTrue(!spo1.isZero());
        assertEquals(Values.speedInMpS(30), Values.speedInKmh(108));
        
    }
}
