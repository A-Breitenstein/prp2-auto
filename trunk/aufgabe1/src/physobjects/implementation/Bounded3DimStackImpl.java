/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package physobjects.implementation;

import java.util.List;
import physobjects.interfaces.Container;
import physobjects.interfaces.Pallet;
import Values.implementation.Values;
import Values.interfaces.BoundingBox;
import physobjects.interfaces.Body;
import Values.interfaces.Mass;
import physobjects.interfaces.Stowage;
import physobjects.interfaces.Bounded3DimStack;
import Values.interfaces.StowageLocation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import physobjects.interfaces.WithForm;
import static Values.implementation.Values.*;

/**
 *
 * @author abg667
 */// 
final class Bounded3DimStackImpl<E> implements Stowage<E> {
    public ArrayList<ArrayList<ArrayList<E>>> staples;
    private Mass emptyMass = Values.ZERO_MASS;
    private Mass maxMass = Values.ZERO_MASS;
    private BoundingBox BB = Values.ZERO_BB; 
    private final int bays,rows,tiers;

    
   
    //CONSTRUCTOR
    public Bounded3DimStackImpl(int bays,int rows,int tiers, List<E> elem){
       this.bays = bays;
       this.rows = rows;
       this.tiers = tiers;
       int y =0;
        staples = new ArrayList<ArrayList<ArrayList<E>>>();
        for(int i = 0;i < bays;i++){
            staples.add(new ArrayList<ArrayList<E>>());
            for (int j = 0; j < rows; j++) {
                staples.get(i).add(new ArrayList<E>());
                for (int k = 0; k < tiers; k++) {
                    E elemE = elem.get(y);
                    staples.get(i).get(j).add(elemE);
                    y++;
                    
                }
            }
        }
    }

    public static Bounded3DimStackImpl createStowage(int bays,int rows,int tiers){
        
        return new Bounded3DimStackImpl(bays,rows,tiers,null);
    }
    
    public Bounded3DimStackImpl initStapleContainer(){
        ArrayList<Container> arrayContainer = new ArrayList<Container>();
        int i=0;
        while(i<bays*rows*tiers){
            Container nc = Physobjects.nullContainer();
            arrayContainer.add(nc);
        }
        return new Bounded3DimStackImpl(bays,rows,tiers,arrayContainer);        
    }
    
    public Bounded3DimStackImpl initStapleNullPallet(){
        ArrayList<Pallet> arrayPallet = new ArrayList<Pallet>();
        int i=0;
        while(i<bays*rows*tiers){
            Pallet np = Physobjects.nullPallet();
            arrayPallet.add(np);
        }
        
        return new Bounded3DimStackImpl(bays,rows,tiers,arrayPallet);
    }
    
    @Override
    public boolean load(int bayNo, int rowNo, E elem) {
        boolean loadable = false;
            boolean laufe = true;
            int tier = 0;
            for(int i = 0; (i< tiers) &&(laufe);i++){
                if( ((WithForm)staples.get(bayNo).get(rowNo).get(i)).isFree()){
                    tier = i;
                    laufe = false;
                    loadable = true;
                }
            }
            if(loadable){
            staples.get(bayNo).get(rowNo).add(tier,elem);
            }
        
        
        return loadable;
    }

    @Override
    public boolean load(E elem) {
        boolean laufe = true,loaded = false;
        
        for(int i = 0; (i< bays) &&(laufe);i++){
            for (int j = 0; (j < rows) &&(laufe); j++) {
                    if(!(tierIsEmpty(i, j) || tierIsFull(i, j))){
                        loaded = load(i,j,elem);
                    }
            }
            
        }
        return loaded;
 
    }

    @Override
    public boolean isEmpty() {
        
        boolean laufe = true;
        for(int i = 0; (i< staples.size()) &&(laufe);i++){
            for (int j = 0; (j < staples.get(i).size()) &&(laufe); j++) {
                   laufe = tierIsEmpty(i, j);   
            }
            
        }
        return laufe;
    }

    @Override
    public boolean isFull() {
        
        boolean laufe = true;
        for(int i = 0; (i< staples.size()) &&(laufe);i++){
            for (int j = 0; (j < staples.get(i).size()) &&(laufe); j++) {
                 laufe = tierIsFull(i, j);   
            }
            
        }
        return laufe;
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        boolean laufe = true; 
        for (int k = 0;(k < staples.get(bay).get(row).size()) &&(laufe); k++) {
                    laufe = ((WithForm)staples.get(bay).get(row).get(k)).isFree();
           }
        
 
        return laufe;
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        boolean laufe = true; 
        for (int k = 0;(k < staples.get(bay).get(row).size()) &&(laufe); k++) {
                    laufe = !((WithForm) staples.get(bay).get(row).get(k)).isFree();
           }
        
 
        return laufe;
    }

    @Override
    public boolean contains(Object elem) {
        boolean laufe = true;
        for(int i = 0; (i< staples.size()) &&(laufe);i++){
            for (int j = 0; (j < staples.get(i).size()) &&(laufe); j++) {
                  for (int k = 0;(k < staples.get(i).get(j).size()) &&(laufe); k++) {
                    laufe = !staples.get(i).get(j).get(k).equals(elem);
                        staples.get(i).get(j).get(k).equals(elem);
                    }
            }
            
        }
        return !laufe;
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        throw new UnsupportedOperationException("..");
//        
//        boolean laufe = true;
//        
//        
//        List<E> list = new ArrayList<E>(coll);
//        for (int c = 0; (c < coll.size()) &&(laufe); c++) {
//           
//            
//        }
//            for(int i = 0; (i< staples.size()) &&(laufe);i++){
//                for (int j = 0; (j < staples.get(i).size()) &&(laufe); j++) {
//                      for (int k = 0;(k < staples.get(i).get(j).size()) &&(laufe); k++) {
//                        laufe = !staples.get(i).get(j).get(k).equals(elem);
//                        }
//                }
//
//            }
//        
//        
//        return !laufe;
//        
        
    }

    @Override
    public E get(StowageLocation stowLoc) {
        
        int bay, row,tier;
        
        bay  = stowLoc.bay();
        row  = stowLoc.row();      
        tier = stowLoc.tier();
        if((bay <= bays) && (row <= rows) && (tier <= tiers)){
            
            if(((WithForm) staples.get(stowLoc.bay()).get(stowLoc.row()).get(stowLoc.tier()+1)).isFree()) 
            return staples.get(stowLoc.bay()).get(stowLoc.row()).get(stowLoc.tier());
        }
        return null;
    }
    

    @Override
    public Set<E> getAll() {
        Set<E> set = new HashSet<E>();
        for (ArrayList<ArrayList<E>> arrayList : staples) {
            for (ArrayList<E> arrayList1 : arrayList) {
                for (E e : arrayList1) {
                    if(((WithForm)e).isOccupied()) set.add(e);
                    
                }
            }
        }
        return set;
    }

    @Override
    public StowageLocation locationOf(E elem) {
        boolean laufe = true;
        StowageLocation loc = ZERO_STOWAGELOC;
        for(int i = 0; (i< staples.size()) &&(laufe);i++){
            for (int j = 0; (j < staples.get(i).size()) &&(laufe); j++) {
                  for (int k = 0;(k < staples.get(i).get(j).size()) &&(laufe); k++) {
                    laufe = !staples.get(i).get(j).get(k).equals(elem);
                        loc = stowageLocation(i, j, k);
                    }
            }
            
        }
        return loc;
    }
    
    
    
    public static void main(String[] args) {
    }

    @Override
    public Mass mass() {
      Mass accu = ZERO_MASS;
      for (int i = 0; i < staples.size(); i++) {
        for (int j = 0; j < staples.get(i).size(); j++) {
            for (int k = 0; k < staples.get(i).get(j).size(); k++) {
              accu = accu.add(((Body) staples.get(i).get(j).get(k)).mass());
            }  
        }   
      }
      return accu;
    }

    @Override
    public BoundingBox getBoundingBox() {
        return BB;
    }

    @Override
    public Mass emptyMass() {
      return emptyMass;
    }

    @Override
    public Mass maxMass() {
        return maxMass;
    }
//TODO: ?
    @Override
    public boolean loadAll(Collection<E> coll) {
        boolean loaded = false;
        if(coll.size() <= freeSpace()){
            for (E e : coll) {
                load(e);
            }
            
        loaded = true;
        }
        String output;
        if(loaded){
            output = coll.size()+" Palleten geladen!";
        }else{
            output = "Keine Palleten geladen, nicht genug Platz vorhanden!";    
        }
        
        System.out.println(output);
        return loaded;
    }

    @Override
    public boolean load(int bayNo, int rowNo, int tierNo, E elem) {
        boolean loaded = false;
        if( (0 >= bayNo) && (bayNo < bays) &&
           (0 >= rowNo) && (rowNo < rows) &&
           (0 >= tierNo) && (tierNo < tiers) ){
            if(((WithForm)staples.get(bayNo).get(rowNo).get(tierNo)).isFree()){
            staples.get(bayNo).get(rowNo).add(tierNo,elem);
            loaded = true;
            }
        
       }else{
        loaded = false;
        }
        return loaded;
    }

    @Override
    public void printStack() {
        String tmpstr = "";
        for (int i = 0; i < bays; i++) {
            tmpstr = tmpstr + "-- B:"+i+"-- { \n";
            for (int j = 0; j < rows; j++) {
                tmpstr = tmpstr + "R:"+j+" [";
                for (int k = 0; k < tiers;k++) {
                   tmpstr = tmpstr +" "+((WithForm)staples.get(i).get(j).get(k)).print()+" "; 
                    
                }
                tmpstr = tmpstr +"],";
                if((j+1)%3==0) tmpstr = tmpstr + "\n";
            }
           tmpstr = tmpstr +" } \n"; 
        }
        System.out.println(tmpstr);
    }
    
    public int freeSpace(){
        int space = 0;

        for (int i = 0; i < bays; i++) {

            for (int j = 0; j < rows; j++) {

                for (int k = 0; k < tiers;k++) {
                    if(((WithForm)staples.get(i).get(j).get(k)).isFree())
                        space++;
                   }
            }
        }
        return space;
    }

}


