package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tilt extends SubsystemBase {
    private MotorController tilt;
    private DigitalInput tilt_limit;
    public Tilt(MotorController tilt, DigitalInput tilt_limit) {
        this.tilt = tilt;
        this.tilt_limit = tilt_limit;

    }
    public MotorController getTilt() {
        return tilt;
    }
    public DigitalInput getLimit(){
        return tilt_limit;
    }
    public void setSpeed(double speed) {
        tilt.set(speed);
    }
    public void stop(){
        tilt.stopMotor();
    }
    
    
}
