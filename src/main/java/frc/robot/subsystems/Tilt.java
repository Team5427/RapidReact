package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tilt extends SubsystemBase {
    private MotorController tiltMotor;

    public Tilt(MotorController tiltMotor) {
        this.tiltMotor = tiltMotor;

    }
    
    public void setSpeed(double speed) {
        tiltMotor.set(speed);
    }
    public void stop(){
        tiltMotor.stopMotor();
    }
    
    
    
}
