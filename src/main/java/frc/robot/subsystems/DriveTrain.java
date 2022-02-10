package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase
{
    private MotorControllerGroup left, right;

    private DifferentialDrive driveBase;

    private RelativeEncoder enc_fl;
    private RelativeEncoder enc_bl;
    private RelativeEncoder enc_br;
    private RelativeEncoder enc_fr;

    private SparkMaxPIDController PID_left;
    private SparkMaxPIDController PID_right;

    private double kP_Left, kI_Left, kD_Left, kIz_Left, kFF_Left, kMaxOutput_Left, kMinOutput_Left;
    private double kP_Right, kI_Right, kD_Right, kIz_Right, kFF_Right, kMaxOutput_Right, kMinOutput_Right;
    private double tankSpeedLeft, tankSpeedRight;

    public DriveTrain(MotorControllerGroup left, MotorControllerGroup right, DifferentialDrive driveBase, RelativeEncoder enc_fl, RelativeEncoder enc_bl, RelativeEncoder enc_fr, RelativeEncoder enc_br, SparkMaxPIDController PID_left, SparkMaxPIDController PID_right)
    {
        this.left = left;
        this.right = right;
        this.driveBase = driveBase;
        this.enc_fl = enc_fl;
        this.enc_bl = enc_bl;
        this.enc_fr = enc_fr;
        this.enc_br = enc_br;
    }

    public void DriveTrainInit()
    {
        driveLeftInit();
        driveRightInit();
    }

    public MotorControllerGroup getLeft()
    {
        return left;
    }

    public MotorControllerGroup getRight()
    {
        return right;
    }

    public RelativeEncoder getDriveEncFL() 
    {
        return enc_fl;
    }

    public RelativeEncoder getDriveEncBL() 
    {
        return enc_bl;
    }

    public RelativeEncoder getDriveEncFR() 
    {
        return enc_fr;
    }

    public RelativeEncoder getDriveEncBR() 
    {
        return enc_br;
    }

    /**
    * Easiest way to utilize the drivetrain. Inversing done in advance. 
    * <p>
    * EX: tankDrive(1, 1);
    * <p>
    * ↑ Will move the robot forward at max speed.
    * <p>
    * @param leftSpeed : The speed to run the left side of the drivetrain. (-1 ... 1)
    * @param rightSpeed : The speed to run the right side of the drivetrain. (-1 ... 1)
    * @return void
    */

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        tankSpeedLeft = Math.pow(leftSpeed, 3);
        tankSpeedRight = Math.pow(rightSpeed, 3);
        left.set(-tankSpeedLeft);
        right.set(tankSpeedRight);
    }

    public void stop()
    {
        left.stopMotor();
        right.stopMotor();
    }

    public void takeJoystickInputs(Joystick joy)
    {
        driveBase.arcadeDrive(joy.getY(), -joy.getZ());
    }

    public DifferentialDrive getDriveBase()
    {
        return driveBase;
    }

    public void driveGoForward(double speed) 
    {
        left.set(-speed);
        right.set(speed);
    }

    public void driveGoBack(double speed)
    {
        left.set(speed);
        right.set(-speed);
    }
    
    public void drivePointTurnLeft(double speed)
    {
        left.set(speed);
        right.set(speed);
    }

    public void drivePointTurnRight(double speed)
    {
        left.set(-speed);
        right.set(-speed);
    }

    public void driveTurnLeftPivotLeft(double speed)
    {
        left.set(0);
        right.set(speed);
    }

    public void driveTurnLeftPivotRight(double speed)
    {
        left.set(-speed);
        right.set(0);
    }

    public void driveTurnRightPivotLeft(double speed)
    {
        left.set(0);
        right.set(-speed);
    }

    public void driveTurnRightPivotRight(double speed)
    {
        left.set(speed);
        right.set(0);
    }

    public void driveLeftInit()
    {
        kP_Left = 0.0002; 
        kI_Left = 0.0000007;
        kD_Left = 0; 
        kIz_Left = 0; 
        kFF_Left = 0.000015; 
        kMaxOutput_Left = 1; 
        kMinOutput_Left = -1;
    
        // set PID coefficients
        PID_left.setP(kP_Left);
        PID_left.setI(kI_Left);
        PID_left.setD(kD_Left);
        PID_left.setIZone(kIz_Left);
        PID_left.setFF(kFF_Left);
        PID_left.setOutputRange(kMinOutput_Left, kMaxOutput_Left);
    }

    public void driveRightInit()
    {
        kP_Right = 0.0002; 
        kI_Right = 0.0000007;
        kD_Right = 0; 
        kIz_Right = 0; 
        kFF_Right = 0.000015; 
        kMaxOutput_Right = 1; 
        kMinOutput_Right = -1;
    
        // set PID coefficients
        PID_right.setP(kP_Right);
        PID_right.setI(kI_Right);
        PID_right.setD(kD_Right);
        PID_right.setIZone(kIz_Right);
        PID_right.setFF(kFF_Right);
        PID_right.setOutputRange(kMinOutput_Right, kMaxOutput_Right);
    }
    
}