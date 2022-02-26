package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tilt extends SubsystemBase {
    private MotorController tilt;
    private DigitalInput tilt_limit;
    public Tilt(MotorController tilt, DigitalInput tilt_limit) {
        this.tilt = tilt;
        this.tilt_limit = tilt_limit;

    }
    public boolean getLimit(){
        return tilt_limit.get();
    }
    public void setSpeed(double speed) {
        tilt.set(speed);
    }
    public void stop(){
        tilt.stopMotor();
    }
    
    
    
}
