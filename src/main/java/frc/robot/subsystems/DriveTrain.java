package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class DriveTrain extends SubsystemBase {
    private MotorControllerGroup left;
    private MotorControllerGroup right;
    private DifferentialDrive drive;
    public static double arcadeSpeed = 0;
    public static double arcadeSpeedHigh = Constants.ArcadeHighSpeed;
    public static double rightSpeed, leftSpeed = 0;
    public static double rightSpeedHigh, leftSpeedHigh = Constants.AUTON_SPEED;
    public  DriveTrain(MotorControllerGroup left, MotorControllerGroup right, DifferentialDrive drive){
        this.left = left;
        this.right = right;
        this.drive = drive;
    }
    public MotorControllerGroup getLeft(){
        return left;
    }
    public MotorControllerGroup getRight(){
        return right;
    }
    public DifferentialDrive getDrive(){
        return drive;
    }
    public void tankDrive(){
        left.set(leftSpeed);
       right.set(rightSpeed);
    }
    public void rampLeft(double speed)
    {
        int multiplier = (speed < 0)? -1: 1;
        left.set((leftSpeed) * multiplier);
 
        if(leftSpeed >= Math.abs(speed))
        {
           
        }
        else
        {
            leftSpeed += 0.0035;
        }
    }
 
    public void rampRight(double speed)
    {
        int multiplier = (speed < 0)? -1: 1;
        left.set((rightSpeed) * multiplier);
 
        if(rightSpeed >= Math.abs(speed))
        {
           
        }
        else
        {
            rightSpeed += 0.0035;
        }
    }
 
    public void rampDownLeft(double speed)
    {
        int multiplier = (speed < 0)? -1: 1;
        left.set((leftSpeedHigh) * multiplier);
 
        if(leftSpeedHigh == 0)
        {
           
        }
        else
        {
            leftSpeedHigh -= 0.05;
        }
    }
 
    public void rampDownRight(double speed)
    {
        int multiplier = (speed < 0)? -1: 1;
        right.set((rightSpeedHigh) * multiplier);
 
        if(rightSpeedHigh == 0)
        {
           
        }
        else
        {
            rightSpeedHigh -= 0.05;
        }
    }
 
    public void rampArcade(double speed, double rotation)
    {
        int multiplier = (speed < 0)? -1: 1;
        drive.arcadeDrive(arcadeSpeed * multiplier, rotation);
 
        if(arcadeSpeed >= Math.abs(speed))
        {
           
        }
        else
        {
            arcadeSpeed += 0.0035;
        }
    }
 
    public void rampDownArcade(double speed, double rotation)
    {
        int multiplier = (speed < 0)? -1: 1;
        drive.arcadeDrive(arcadeSpeedHigh * multiplier, rotation);
 
        if(arcadeSpeedHigh <= 0)
        {
           
        }
        else
        {
            arcadeSpeedHigh -= 0.004;
        }
   
}
public void stop()
    {
        left.stopMotor();
        right.stopMotor();
    }
 
    public void takeJoystickInputs(Joystick joy)
    {
        drive.arcadeDrive(joy.getY(), -joy.getZ() * 0.65);
    }
}

