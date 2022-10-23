package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TeleArmR extends SubsystemBase{

    private MotorController extendRightMotor;

    private Encoder rightEncoder;

    public TeleArmR(MotorController extendRightMotor, Encoder rightEncoder, DigitalInput rightLimit){
        this.extendRightMotor = extendRightMotor;

        this.rightEncoder = rightEncoder;

    }

    public void extendRight(double speed){
        extendRightMotor.set(speed);
    }

    public void stopExtend(){
        extendRightMotor.stopMotor();
    }

    public double getRightEncoder(){
        return rightEncoder.getDistance();
    }

    public boolean getRightEncoderLimit(){
        // return true if at limit
        // return getRightEncoder() < Constants.ARM_RIGHT_ENCODER_LIMIT;
        return false;
    }
}
