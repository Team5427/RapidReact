package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TelescopicArm extends SubsystemBase{

    private MotorController tiltMotor;
    private MotorController extendLeftMotor;
    private MotorController extendRightMotor;

    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private Encoder tiltEncoder;

    private DigitalInput tiltLimitSwitch;


    public TelescopicArm(MotorController tiltMotor, MotorController extendLeftMotor, MotorController extendRightMotor, Encoder leftEncoder, Encoder rightEncoder, Encoder tiltEncoder, DigitalInput tiltRightLimit, DigitalInput rightLimit, DigitalInput leftLimit){
        this.tiltMotor = tiltMotor;
        this.extendLeftMotor = extendLeftMotor;
        this.extendRightMotor = extendRightMotor;

        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;

        this.tiltEncoder = tiltEncoder;
        this.tiltLimitSwitch = tiltLimitSwitch;

    }

    public void tiltArm(double speed){
        tiltMotor.set(speed);
    }

    public void moveArm(double speed){
        extendRightMotor.set(speed);
        extendLeftMotor.set(speed);
    }

    public void stopExtend(){
        extendLeftMotor.stopMotor();
        extendRightMotor.stopMotor();
    }

    public void stopTilt(){
        tiltMotor.stopMotor();
    }

    public boolean getTiltLimit(){
        // return true if pressed
        // return !tiltLimitSwitch.get();
        return true;
    }

    public double getLeftEncoder(){
        return leftEncoder.getDistance();
    }

    public double getRightEncoder(){
        return rightEncoder.getDistance();
    }

    public double getTiltEncoder(){
        return tiltEncoder.getDistance();
    }

    public boolean getTiltEncoderLimit(){
        // return true if at limit
        // return getTiltEncoder() < Constants.ARM_TILT_ENCODER_LIMIT;
        return false;
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
