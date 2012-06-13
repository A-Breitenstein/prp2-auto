/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import Values.implementation.Values;
import Values.interfaces.BoundingBox;
import Values.interfaces.Power;
import Values.interfaces.Speed;
import Values.interfaces.StowageLocation;
import java.util.ArrayList;
import java.util.List;
import physobjects.interfaces.Container;
import physobjects.interfaces.ContainerShip;
import physobjects.interfaces.ContainerStowage;
import physobjects.interfaces.ContainerTruck;
import physobjects.interfaces.Engine;
import physobjects.interfaces.Pallet;
import physobjects.interfaces.ShipHull;
import physobjects.interfaces.Stowage;
import physobjects.interfaces.VanCarrier;

/**
 *
 * @author abg667
 */
public final class Physobjects {
    //PRINT SYMBOLS
    public static final String CONTAINERSYM = "SC";
    public static final String NULLCONTAINERSYM = "0C";
    public static final String NONCONTAINERSYM = "NC";
    public static final String NULLPALLETSYM = "0P";
    public static final String NONPALLETSYM = "NP";
    public static final String PALLETSYM = "SP";

    private Physobjects(){}
    // CONTAINER OBJECTS
    public static Container container(){
        return ContainerImpl.createContainer(); 
    }
    public static Container nullContainer(){
        return NullContainer.nullContainer();
    }
    public static Container nonContainer(){
        return NonContainer.nonContainer();
    }
    
    //PALLET OBJECTS
    public static Pallet pallet(){
        return PalletImpl.pallet();
    }
    public static Pallet nullPallet(){
        return NullPallet.nullPallet();
    }
    public static Pallet nonPallet(){
        return NonPallet.nonPallet();
    }
    //STOWAGE OBJECTS // erstmal mit public Konstrucktor weil generics ka :/
    public static Stowage<Pallet> palletStowage(){
        return new Bounded3DimStackImpl<Pallet>(1,9,3,initStapleNullPallet(1, 9,3));
    }
    public static Stowage<Container> containerStowage(int bays,int rows,int tiers){    
        return new Bounded3DimStackImpl<Container>(bays, rows, tiers,initStapleContainer(bays, rows, tiers));
    }
    public static ContainerStowage containerTerminal(int bays,int rows,int tiers){
        return TerminalStowage.createTerminalStowage(bays, rows, tiers);
    }
    public static Stowage<Pallet> nullPalletStowage(){
        return new Bounded3DimStackImpl<Pallet>(0,0,0,null);
    }
    public static Stowage<Container> nullContainerStowage(){
        return new Bounded3DimStackImpl<Container>(0,0,0,null);
    }
    
    private static List<Container> initStapleContainer(int bays,int rows,int tiers){
        List<Container> arrayContainer = new ArrayList<Container>();
        int i=0;
        int elem_count = bays*rows*tiers;
        while(i<elem_count){
            Container nc = Physobjects.nullContainer();
            arrayContainer.add(nc);
            i++;
        }
        return arrayContainer;        
    }
    
   private static List<Pallet> initStapleNullPallet(int bays,int rows,int tiers){
        List<Pallet> arrayPallet = new ArrayList<Pallet>();
        int i=0;
       int elem_count = bays*rows*tiers;
        while(i<elem_count){
            Pallet np = Physobjects.nullPallet();
            arrayPallet.add(np);
            i++;
        }
        
        return arrayPallet;
    }
    
    
    
    
    //VEHICLES
    public static ContainerTruck containerTruck(Engine engine,Speed maxSpeed){
       throw new UnsupportedOperationException("Not supported yet.");
    }  
    
    public static VanCarrier vanCarrier(Engine engine,Speed maxSpeed){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static ContainerShip containerShip(ShipHull shiphull, Engine engine,Speed maxSpeed,
                                              int bays,int rows, int tiers){
       throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public static Engine engine(BoundingBox bb,Power maxPower){
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public static ShipHull shiphull(BoundingBox bb){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void main(String[] args) {
        Container a = container();
        StowageLocation loc = Values.stowageLocation(0, 0, 3);
        a.load(0,0,pallet());
        a.printStack();
        a.load(0,0,pallet());
        a.load(0,0,pallet());
        if(!a.load(0,0,pallet()))
            System.out.println("pallet ging nicht in b0r0 rein");;
        a.load(0,7,pallet());
        a.load(0,5,pallet());
        a.printStack();
        System.out.println("Masse: vor get "+a.mass()+" kg");
        System.out.println(a.get(loc));
        System.out.println("Masse: nach get "+a.mass()+" kg");
        System.out.println("get muss noch gefixt werden! wenn dir die zeichen nicht gefallen guck in physobjects nach da sind sie defined");
        if(!(a.isEmpty()))
            System.out.println("Nö");
        if((a.isFull()))
            System.out.println("JA");
        
        ArrayList<Pallet> aP = new ArrayList<Pallet>();
        aP.add(pallet());
        aP.add(pallet());
        a.loadAll(aP);
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        aP.add(pallet());
        a.loadAll(aP);
            
        List<Container> b = new ArrayList<Container>();
        getContainer(0, 1, 2, b);
        b.get(0).printStack();
       
    }
    
    public static boolean getContainer(int bay,int row,int tier,List<Container> lc){
        lc.add(Physobjects.container());
        return false;
    }
}
