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
        return new Bounded3DimStackImpl<Pallet>(1,9,3);
    }
    public static Stowage<Container> containerStowage(int bays,int rows,int tiers){
        return new Bounded3DimStackImpl<Container>(bays, rows, tiers);
    }
    public static ContainerStowage containerTerminal(int bays,int rows,int tiers){
        return TerminalStowage.createTerminalStowage(bays, rows, tiers);
    }
    public static Stowage<Pallet> nullPalletStowage(){
        return new Bounded3DimStackImpl<Pallet>(0,0,0);
    }
    public static Stowage<Container> nullContainerStowage(){
        return new Bounded3DimStackImpl<Container>(0,0,0);
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
        a.load(0,0,pallet());
        a.load(0,0,pallet());
        a.load(0,0,pallet());
        System.out.println(a.get(loc));
        System.out.println("Masse:"+a.mass()+" kg");
        if(!(a.isEmpty()))
            System.out.println("Nö");
        if((a.isFull()))
            System.out.println("JA");
    }
}
