package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TelescopicArmRight extends SubsystemBase{

    private MotorController extendLeftMotor;
    private MotorController extendRightMotor;

    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private Solenoid armTiltLeft;
    private Solenoid armTiltRight;

    public TelescopicArmRight(MotorController extendLeftMotor, MotorController extendRightMotor, Encoder leftEncoder, Encoder rightEncoder, DigitalInput rightLimit, DigitalInput leftLimit, Solenoid armTiltLeft, Solenoid armTiltRight){
        this.extendLeftMotor = extendLeftMotor;
        this.extendRightMotor = extendRightMotor;

        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;

        this.armTiltLeft = armTiltLeft;
        this.armTiltRight = armTiltRight;

    }

    public void moveArm(double speed){
        extendRightMotor.set(speed);
        extendLeftMotor.set(speed);
    }

    public void extendLeft(double speed){
        extendLeftMotor.set(speed);
    }

    public void extendRight(double speed){
        extendRightMotor.set(speed);
    }

    public void stopExtend(){
        extendLeftMotor.stopMotor();
        extendRightMotor.stopMotor();
    }

    public void toggleArmTilt(){
        armTiltLeft.toggle();
        armTiltRight.toggle();
    }

    public double getLeftEncoder(){
        return leftEncoder.getDistance();
    }

    public double getRightEncoder(){
        return rightEncoder.getDistance();
    }


    public boolean getLeftEncoderLimit(){
        // return true if at limit
        // return getLeftEncoder() < Constants.ARM_LEFT_ENCODER_LIMIT;
        return false;
    }

    public boolean getRightEncoderLimit(){
        // return true if at limit
        // return getRightEncoder() < Constants.ARM_RIGHT_ENCODER_LIMIT;
        return false;
    }
}
