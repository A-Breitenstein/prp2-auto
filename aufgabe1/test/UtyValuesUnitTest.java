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
    
    //Length-units
    @Test
    public void test_LengthInKm(){
        Length l1, l2;
        l1 = Values.lengthInM(10000);
        l2 = Values.lenghtInKm(10);
        
        assertEquals(l1, l2);
    }
    @Test
    public void test_lenghtInNm(){
        Length l1, l2;
        l1 = Values.lengthInM(1);
        l2 = Values.lenghtInNm(0.000539956803);
        
        assertEquals(l1,l2);
    }
    @Test
    public void test_lenghtInFt(){
        Length l1, l2;
        l1 = Values.lengthInM(1);
        l2 = Values.lenghtInFt(3.2808399);
        assertEquals(l1, l2);
    }
    
    //TimeDiff-units
    TimeDiff tD,tD2,tD3,tD4;
    @Test
    public void test_timeDiffInS(){
        tD = Values.timeDiffInS(1);
        tD2 = Values.timeDiffInS(1);
        assertEquals(tD, tD2);
    }
    @Test
    public void test_timeDiffInM(){
        tD = Values.timeDiffInS(60);
        tD2 = Values.timeDiffInM(1);
        assertEquals(tD, tD2);
    }
    @Test
    public void test_timeDiffInH(){
        tD = Values.timeDiffInS(3600);
        tD2 = Values.timeDiffInH(1);
        assertEquals(tD, tD2);
    }
    @Test
    public void test_timeDiffInMs(){
        tD = Values.timeDiffInS(1);
        tD2 = Values.timeDiffInMs(1000);
        tD4 = Values.timeDiffInS(3);
        tD3 = Values.timeDiffInMs(3000);
        assertEquals(tD, tD2);
        assertEquals(tD4, tD3);   
    }
    //Mass-units
    Mass m1,m2,m3,m4;
    @Test
    public void test_massInKg(){
		m1 = Values.massInKg(1);
		m2 = Values.massInKg(1);
		assertEquals(m1,m2);
    }
    @Test
    public void test_massInT(){
    }
    @Test
    public void test_massInG(){
    }
    @Test
    public void test_massInLbs(){
    }
    
    //Speed-units
    Speed s1,s2,s3,s4;
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
    
    @Test    
    public void test_speedInMph(){    
    }
    @Test
    public void test_speedInKn(){    
    }
    
    //Angle-units
    Angle a1,a2,a3,a4;
    public void test_angleInRad(){
    }
    public void test_angleInDeg(){
    }
    
    //Power-untis
    Power p1,p2,p3,p4;
    public void test_powerInW(){
    }
    public void test_powerInKW(){
    }
    public void test_powerInJs(){
    }
    
    //Force-unit
    Force f1,f2,f3,f4;
    public void test_forceInN(){
    }
    public void test_forceInKN(){
    }
    
    //Acc-unit
    Acc acc1,acc2,acc3,acc4;
    public void test_accInMss(){
        acc1 = Values.accInMss(5);
        acc2 = Values.accInMss(5);
        acc3 = Values.accInMss(10);
        
        assertEquals(acc1, acc2);
        assertEquals(acc3, acc1.add(acc2));
        assertEquals(acc2, acc3.sub(acc1));
        assertEquals(acc3, acc1.mul(2));
        
    }
}
