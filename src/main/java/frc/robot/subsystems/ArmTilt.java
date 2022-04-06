package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmTilt extends SubsystemBase{

    private Solenoid armTiltLeft;
    private Solenoid armTiltRight;

    public ArmTilt(Solenoid armTiltLeft, Solenoid armTiltRight){

        this.armTiltLeft = armTiltLeft;
        this.armTiltRight = armTiltRight;

    }

    public void toggleArmTilt(){
        armTiltLeft.toggle();
        armTiltRight.toggle();
    }

}
