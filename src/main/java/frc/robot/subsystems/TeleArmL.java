package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TeleArmL extends SubsystemBase{

    private MotorController extendLeftMotor;

    private Encoder leftEncoder;

    public TeleArmL(MotorController extendLeftMotor, Encoder leftEncoder, DigitalInput leftLimit){
        this.extendLeftMotor = extendLeftMotor;

        this.leftEncoder = leftEncoder;

    }

    public void moveArm(double speed){
        extendLeftMotor.set(speed);
    }

    public void extendLeft(double speed){
        extendLeftMotor.set(speed);
    }

    public void stopExtend(){
        extendLeftMotor.stopMotor();
    }

    public double getLeftEncoder(){
        return leftEncoder.getDistance();
    }


    public boolean getLeftEncoderLimit(){
        // return true if at limit
        // return getLeftEncoder() < Constants.ARM_LEFT_ENCODER_LIMIT;
        return false;
    }
}
