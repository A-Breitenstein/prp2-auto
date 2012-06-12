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
        List<Pallet> tmp = new ArrayList<Pallet>();
        for(int i = 0;i<=3*9;i++){
            tmp.add(nullPallet());
        }
        return new Bounded3DimStackImpl<Pallet>(1,9,3,tmp);
    }
    public static Stowage<Container> containerStowage(int bays,int rows,int tiers){
        List<Container> tmp = new ArrayList<Container>();
        for(int i = 0;i<=(bays*rows*tiers);i++){
            tmp.add(nullContainer());
        }
            
        return new Bounded3DimStackImpl<Container>(bays, rows, tiers,tmp);
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
    }
}
