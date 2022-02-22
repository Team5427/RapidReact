package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TelescopicArm extends SubsystemBase{
    private MotorController tiltMotor;
    private MotorController extendLeftMotor;
    private MotorController extendRightMotor;
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private Encoder tiltEncoder;
    private DigitalInput tiltLeftLimit;
    private DigitalInput tiltRightLimit;
    private DigitalInput rightLimit;
    private DigitalInput leftLimit;

    public TelescopicArm(MotorController tiltMotor, MotorController extendLeftMotor, MotorController extendRightMotor, Encoder leftEncoder, Encoder rightEncoder, Encoder tiltEncoder, DigitalInput tiltLeftLimit, DigitalInput tiltRightLimit, DigitalInput rightLimit, DigitalInput leftLimit){
        this.tiltMotor = tiltMotor;
        this.extendLeftMotor = extendLeftMotor;
        this.extendRightMotor = extendRightMotor;
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.tiltEncoder = tiltEncoder;
        this.tiltLeftLimit = tiltLeftLimit;
        this.tiltRightLimit = tiltRightLimit;
        this.leftLimit = leftLimit;

    }

    public void tilt_Arm(double speed){
        tiltMotor.set(speed);
    }

    public void move_Arm(double speed){
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

    public boolean getLeftLimit(){
        return !leftLimit.get();
    }

    public boolean getRightLimit(){
        return !rightLimit.get();
    }

    public boolean getRightTiltLimit(){
        return !tiltRightLimit.get();
    }

    public boolean getLeftTiltLimit(){
        return !tiltLeftLimit.get();
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
}
