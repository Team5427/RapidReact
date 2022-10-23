package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tilt extends SubsystemBase {
    private Solenoid leftPiston;
    private Solenoid rightPiston;
    public Tilt(Solenoid leftPiston, Solenoid rightPiston) {
        this.leftPiston = leftPiston;
        this.rightPiston = rightPiston;
    }
    
    
    public void toggleTilt(){
        leftPiston.toggle();
        rightPiston.toggle();
    }

    public void toggleLeft(){
        leftPiston.toggle();
    }
    public void toggleRight(){
        rightPiston.toggle();
    }
    public void setTilt(boolean on){
        leftPiston.set(on);
        rightPiston.set(on);
    }
    
    
    
}
