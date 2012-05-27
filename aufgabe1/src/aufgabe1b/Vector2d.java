/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe1b;

/**
 *
 * @author Sven
 */
public class Vector2d {
    public double x,y;
    private Vector2d(double x, double y){
        this.x = x;
        this.y = y;
    }
    public static Vector2d new_(double x, double y){
        return new Vector2d(x,y);
    }
    
    public void setXY(double x,double y){
        this.x = x;
        this.y = y;
    }
    static Vector2d setToVec(Vector2d vec){
        return new Vector2d(vec.x, vec.y);
    }
    public Vector2d add(Vector2d vec2d){
        return Vector2d.new_(x+vec2d.x, y+vec2d.y);
    }
    
    public Vector2d add_on(Vector2d vec2d){
        x+=vec2d.x;
        y+=vec2d.y;
        return this;
    }
    public Vector2d mulVec2d(Vector2d vec2d){
        return Vector2d.new_(x*vec2d.x,y*vec2d.y);
    }
    public Vector2d mulScalar(double scalar){
        return Vector2d.new_(x*scalar, y*scalar);
    }
    public void mulScalar_on(double scalar){
        setXY(x*scalar,y*scalar);
    }
    
    
    @Override
    public String toString(){
        return "x =: "+x+" y =: "+y;
    }
    
}
