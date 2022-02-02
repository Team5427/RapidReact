package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Shooter extends SubsystemBase
{
    private CANSparkMax shooterMotorLeft;
    private CANSparkMax shooterMotorRight;
    private RelativeEncoder lEnc;
    private RelativeEncoder rEnc;
    private SparkMaxPIDController controllerPID;

    private double kP_Left, kI_Left, kD_Left, kIz_Left, kFF_Left, kMaxOutput_Left, kMinOutput_Left;
    
    public Shooter(CANSparkMax shooterMotorLeft, CANSparkMax shooterMotorRight, RelativeEncoder lEnc, RelativeEncoder rEnc, SparkMaxPIDController controllerPID)
    {
        this.shooterMotorLeft = shooterMotorLeft;
        this.shooterMotorRight = shooterMotorRight;
        this.lEnc = lEnc;
        this.rEnc = rEnc;
        this.controllerPID = controllerPID;
    }

    public CANSparkMax getShooterMotorLeft()
    {
        return shooterMotorLeft;
    }

    public CANSparkMax getShooterMotorRight()
    {
        return shooterMotorRight;
    }

    public void moveShooter(double speed)
    {
        shooterMotorRight.set(-speed);
        shooterMotorLeft.set(speed);
    }

    public void moveShooterPID()
    {
        controllerPID.setReference(Robot.setPointShooter, CANSparkMax.ControlType.kVelocity);
        shooterMotorRight.set(shooterMotorLeft.get());
    }

    public void stop()
    {
        shooterMotorRight.stopMotor();
        shooterMotorLeft.stopMotor();
    }

    public RelativeEncoder getShooterLeftEnc()
    {
        return lEnc;
    }

    public RelativeEncoder getShooterRightEnc()
    {
        return rEnc;
    }

    public void shooterInit()
    {
        kP_Left = 0.0002; 
        kI_Left = 0.0000007;
        kD_Left = 0; 
        kIz_Left = 0; 
        kFF_Left = 0.000015; 
        kMaxOutput_Left = 1; 
        kMinOutput_Left = -1;    
        // set PID coefficients
        controllerPID.setP(kP_Left);
        controllerPID.setI(kI_Left);
        controllerPID.setD(kD_Left);
        controllerPID.setIZone(kIz_Left);
        controllerPID.setFF(kFF_Left);
        controllerPID.setOutputRange(kMinOutput_Left, kMaxOutput_Left);
    }

}