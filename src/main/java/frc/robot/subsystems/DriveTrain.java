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

    public void tankDrive(double leftSpeed, double rightSpeed)
    {
        driveBase.tankDrive(leftSpeed, rightSpeed);
    }

    public void stop()
    {
        left.stopMotor();
        right.stopMotor();
    }

    public void takeJoystickInputs(Joystick joy)
    {
        driveBase.arcadeDrive(joy.getY(), -joy.getZ() * 0.65);
    }

    public DifferentialDrive getDriveBase()
    {
        return driveBase;
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