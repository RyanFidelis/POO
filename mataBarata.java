// Autores: André Oliveira, Enrico Hidalgo, Ryan Fidelis;
// Atividade: 08;
// Curso de POO aplicado ao Robocode
package MataBarata;

import robocode.*;

public class MataBarata extends Robot {
    
    public void run() {
        setAdjustGunForRobotTurn(true);  
        while(true) {
            turnRadarRight(360);
        }
    }
    
    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getName().contains("CHARLES")) {
            double mira = getHeading() + e.getBearing() - getGunHeading();
            
            turnGunRight(mira);

            fire(3);

            turnRadarRight(mira);
        }
    }
}
