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
    public void test_SpeedInKmh(){
        //Speed speed = Values.speedInKmh(30);
        System.out.println("hallo test");
        double mps = 30;
        assertEquals(Values.speedInKmh(mps), Values.speedInKmh(mps));
    }
}
