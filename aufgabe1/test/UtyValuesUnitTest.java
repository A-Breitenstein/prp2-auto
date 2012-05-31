
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Values.*;
import java.util.*;

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
        
        assertEquals(l1.m(),l2.m(),0.01);
    }
    @Test
    public void test_lenghtInFt(){
        Length l1, l2;
        l1 = Values.lengthInM(1);
        l2 = Values.lenghtInFt(3.2808399);
        assertEquals(l1.m(), l2.m(),0.01);
    }
    
    //TimeDiff-units
    TimeDiff tD1,tD2,tD3,tD4;
    @Test
    public void test_timeDiffInS(){
        tD1 = Values.timeDiffInS(1);
        tD2 = Values.timeDiffInS(1);
        assertEquals(tD1, tD2);
    }
    @Test
    public void test_timeDiffInM(){
        tD1 = Values.timeDiffInS(60);
        tD2 = Values.timeDiffInM(1);
        assertEquals(tD1, tD2);
    }
    @Test
    public void test_timeDiffInH(){
        tD1 = Values.timeDiffInS(3600);
        tD2 = Values.timeDiffInH(1);
        assertEquals(tD1, tD2);
    }
    @Test
    public void test_timeDiffInMs(){
        tD1 = Values.timeDiffInS(1);
        tD2 = Values.timeDiffInMs(1000);
        tD4 = Values.timeDiffInS(3);
        tD3 = Values.timeDiffInMs(3000);
        assertEquals(tD1, tD2);
        assertEquals(tD4, tD3);   
    }
    //Mass-units
    Mass m1,m2,m3,m4,m5,m6,m7;
    @Test
    public void test_massInKg(){
	m1 = Values.massInKg(1);
	m2 = Values.massInKg(1);
        m3 = Values.massInKg(500);
        m4 = Values.massInKg(2500);
        m5 = Values.massInKg(501);
        m6 = Values.massInKg(2000);
        m7 = Values.ZERO_MASS;
	assertEquals(m1,m2);
        assertEquals(m4, m3.mul(5));
        assertEquals(m3, m4.div(5));
        assertEquals(m5, m1.add(m3));
        assertEquals(m6, m4.sub(m3));
        assertTrue(m7.isZero());
        assertTrue("500,00".equals(m3.toString()));
        assertEquals("500,00", m3.toString());
    }
    @Test
    public void test_massInT(){
        m1 = Values.massInKg(1000);
	m2 = Values.massInT(1);
        m3 = Values.massInT(0.5);
        m4 = Values.massInT(2.5);
        m5 = Values.massInT(1.5);
        m6 = Values.massInT(2);
        m7 = Values.ZERO_MASS;
        assertEquals(m4, m3.mul(5));
        assertEquals(m3, m4.div(5));
        assertEquals(m5, m1.add(m3));
        assertEquals(m6, m4.sub(m3));
        assertTrue(m7.isZero());
        assertTrue("2500,00".equals(m4.toString()));
	assertEquals(m1,m2);
    }
    @Test
    public void test_massInG(){
        m1 = Values.massInKg(1);
	m2 = Values.massInG(1000);
        m3 = Values.massInG(500);
        m4 = Values.massInG(2500);
        m5 = Values.massInG(1500);
        m6 = Values.massInG(2000);
        assertEquals(m4, m3.mul(5));
        assertEquals(m3, m4.div(5));
        assertEquals(m5, m1.add(m3));
        assertEquals(m6, m4.sub(m3));
        assertTrue(!m6.isZero());
        assertTrue("0,50".equals(m3.toString()));
	assertEquals(m1,m2);
    }
    @Test
    public void test_massInLbs(){
        m1 = Values.massInKg(1);
	m2 = Values.massInLbs(2.20462262);
        m3 = Values.massInLbs(1102.31131);
        m4 = Values.massInLbs(5511.55655);
        m5 = Values.massInLbs(1104.51593262);
        m6 = Values.massInLbs(4409.24524);
        m7 = Values.ZERO_MASS;
        assertEquals(m4, m3.mul(5));
        assertEquals(m3.kg(), m4.div(5).kg(),0.01);
        assertEquals(m5.kg(), m1.add(m3).kg(),4d);
        assertEquals(m6.kg(), m4.sub(m3).kg(),0.01);
        assertTrue(m7.isZero());
        assertTrue("500,00".equals(m3.toString()));
	assertEquals(m1,m2);
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
    Angle a1,a2,a3,a4,a5,a6,a7;
    @Test
    public void test_angleInRad(){
        tD1 = Values.timeDiffInS(40);
        a1 = Values.angleInRad(1);
        a2 = Values.angleInRad(1);
        a3 = Values.angleInRad(30);
        a4 = Values.angleInRad(60);
        a5 = Values.angleInRad(90);
        a6 = Values.angleInRad(120);
        a7 = Values.ZERO_ANGLE;
        assertEquals(a1, a2);
        assertEquals(a5, a3.add(a4));
        assertEquals(a4, a6.sub(a3).sub(a3));
        assertEquals(a4, a3.mul(2));
        assertEquals(a3, a6.div(4));
        assertEquals(Values.angleInRad(1200), a3.mul(tD1));
        assertTrue("60,00".equals(a4.toString()));
        assertTrue(a7.isZero());
    }
    @Test
    public void test_angleInDeg(){
        tD1 = Values.timeDiffInS(40);
        a1 = Values.angleInDeg(1);
        a2 = Values.angleInDeg(1);
        a3 = Values.angleInDeg(30);
        a4 = Values.angleInDeg(60);
        a5 = Values.angleInDeg(90);
        a6 = Values.angleInDeg(120);
        a7 = Values.ZERO_ANGLE;
        assertEquals(a1, a2);
        assertEquals(a5, a3.add(a4));
        assertEquals(a4.deg(), a6.sub(a3).sub(a3).deg(),0.01);
        assertEquals(a4, a3.mul(2));
        assertEquals(a3, a6.div(4));
        assertEquals(Values.angleInDeg(1200).deg(), a3.mul(tD1).deg(),0.01);
        assertTrue(!a3.isZero());
        assertTrue(a1.isZero()!=a7.isZero());
    }
    
    //Power-untis
    Power p1,p2,p3,p4;
    @Test
    public void test_powerInW(){
        p1 = Values.powerInW(500);
        p2 = Values.powerInW(500);
        p3 = Values.powerInW(1000);
        
        assertEquals(p1, p2);
        assertEquals(p3, p2.add(p1));
        assertEquals(p1, p3.sub(p2));
        assertEquals(p3, p2.mul(2));
        assertEquals(p1, p3.div(2));
        assertEquals(Values.forceInN(50),p1.div(Values.speedInMpS(10)));
        
        assertTrue(p1.compareTo(p2) == 0);
        assertTrue(p1.compareTo(p3) == -1);
        assertTrue(p3.compareTo(p1) == 1);
        
        assertTrue(p1.equals(p2));
        
        assertTrue(!p1.isZero());
        assertTrue("500,00".equals(p1.toString()));
        assertTrue(500.0 == p1.w());
        assertTrue(0.5 == p1.kw());
        
        
        
    }
    @Test
    public void test_powerInKW(){
        p1 = Values.powerInKW(0.5);
        p2 = Values.powerInW(500);
        
        assertEquals(p1,p2);
    }
    @Test
    public void test_powerInJs(){
    }
    
    //Force-unit
    Force f1,f2,f3,f4;
    @Test
    public void test_forceInN(){
        f1 = Values.forceInN(500);
        f2 = Values.forceInN(500);
        f3 = Values.forceInN(1000);
        
        assertEquals(f1, f2);
        assertEquals(f3, f2.add(f1));
        assertEquals(f1, f3.sub(f2));
        assertEquals(f3, f2.mul(2));
        assertEquals(f1, f3.div(2));
        assertEquals(Values.powerInW(2500), f2.mul(Values.speedInMpS(5)));
        assertEquals(Values.accInMss(5),f1.div(Values.massInKg(100)));
        assertEquals(Values.massInKg(100),f1.div(Values.accInMss(5)));
        
        assertTrue(f1.compareTo(f2) == 0);
        assertTrue(f1.compareTo(f3) == -1);
        assertTrue(f3.compareTo(f1) == 1);
        
        assertTrue(f1.equals(f2));
        
        assertTrue(!f1.isZero());
        assertTrue("500,00".equals(f1.toString()));
        assertTrue(500.0 == f1.n() );
        assertTrue(0.5 == f1.kn());
    }
    @Test
    public void test_forceInKN(){
        f1 = Values.forceInKN(0.5);
        f2 = Values.forceInN(500);
        
        assertEquals(f1,f2);
        
    }
    
    //Acc-unit
    Acc acc1,acc2,acc3,acc4;
    @Test
    public void test_accInMss(){
        acc1 = Values.accInMss(5);
        acc2 = Values.accInMss(5);
        acc3 = Values.accInMss(10);
        
        assertEquals(acc1, acc2);
        assertEquals(acc3, acc1.add(acc2));
        assertEquals(acc2, acc3.sub(acc1));
        assertEquals(acc3, acc1.mul(2));
        assertEquals(acc1,acc3.div(2));
        
        assertTrue(acc1.compareTo(acc2) == 0);
        assertTrue(acc1.compareTo(acc3) == -1);
        assertTrue(acc3.compareTo(acc1) == 1);
        
        assertTrue(acc1.equals(acc1));
        
        assertTrue(!acc1.isZero());
        assertTrue("5,00".equals(acc1.toString()));
        assertTrue(5.0 == acc1.mss() );
        

        
        
    }
}
