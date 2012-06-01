/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe1b;

import java.util.HashMap;

/**
 *
 * @author Bartel/Breitenstein
 */
public class ControlConfiguration{
    
    public ControlConfiguration() {}
       
    public void initKeyboardConfig(HashMap sharedKeys, HashMap playerOne, HashMap playerTwo, HashMap optionalConfig) {
        
        //::shared Keys
	sharedKeys.put("continue",79);
	sharedKeys.put("pause",90);
        sharedKeys.put("Eis",49);
        sharedKeys.put("Schnee", 50);
        sharedKeys.put("Nässe", 51);
        sharedKeys.put("Kein", 52);
        sharedKeys.put("ABSON", 53);
        sharedKeys.put("ABSOFF", 54);
        sharedKeys.put("ASRON", 55);
        sharedKeys.put("ASROFF", 56);
	
	//::playerOne Belegung
	playerOne.put("increaseLevel",38);
	playerOne.put("decreaseLevel",40);
	playerOne.put("turnRight",39);
	playerOne.put("turnLeft",37);
	playerOne.put("reset",82);
	playerOne.put("increaseBrakeLevel",77);
	playerOne.put("decreaseBrakeLevel",78);
	
	//::playerTwo Belegung
	playerTwo.put("increaseLevel",87);
	playerTwo.put("decreaseLevel",83);
	playerTwo.put("turnRight",68);
	playerTwo.put("turnLeft",65);
	playerTwo.put("reset",82);
	playerTwo.put("increaseBrakeLevel",70);
	playerTwo.put("decreaseBrakeLevel",71);
	
	//::optionalConfig Belegung
	optionalConfig.put("increaseLevel",0);
	optionalConfig.put("decreaseLevel",0);
	optionalConfig.put("turnRight",0);
	optionalConfig.put("turnLeft",0);
	optionalConfig.put("reset",0);
	optionalConfig.put("continue",0);
	optionalConfig.put("pause",0);
	optionalConfig.put("increaseBrakeLevel",0);
	optionalConfig.put("decreaseBrakeLevel",0);        
    }
}

