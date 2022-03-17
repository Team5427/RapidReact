package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;

public class Shooter extends SubsystemBase
{
    private CANSparkMax shooterMotorRight;
    private CANSparkMax shooterMotorLeft;
    private RelativeEncoder shooterRightEnc;
    private RelativeEncoder shooterLeftEnc;

    private SparkMaxPIDController pid_Right_ss;
    public double kP_Right, kI_Right, kD_Right, kIz_Right, kFF_Right, kMaxOutput_Right, kMinOutput_Right, maxRPM_Right;
    
    public Shooter(CANSparkMax shooterMotorRight, CANSparkMax shooterMotorLeft, RelativeEncoder Right, RelativeEncoder Left, SparkMaxPIDController pid_Right, SparkMaxPIDController pid_btm)
    {
        this.shooterMotorRight = shooterMotorRight;
        this.shooterMotorLeft = shooterMotorLeft;
        shooterRightEnc = Right;
        shooterLeftEnc = Left;
        pid_Right_ss = pid_Right;
    }

    public CANSparkMax getShooterMotorRight()
    {
        return shooterMotorRight;
    }

    public CANSparkMax getShooterMotorLeft()
    {
        return shooterMotorLeft;
    }

    public RelativeEncoder getRightEnc () 
    {
        return shooterRightEnc;
    }

    public RelativeEncoder getLeftEnc () 
    {
        return shooterLeftEnc;
    }

    public void shooterInitRight() {
    
        kP_Right = 0.00010; 
        kI_Right = 0.0000007;
        kD_Right = 0; 
        kIz_Right = 0; 
        kFF_Right = 0.000015; 
        kMaxOutput_Right = 1; 
        kMinOutput_Right = -1;
        maxRPM_Right = 5700;

        pid_Right_ss.setP(kP_Right);
        pid_Right_ss.setI(kI_Right);
        pid_Right_ss.setD(kD_Right);
        pid_Right_ss.setIZone(kIz_Right);
        pid_Right_ss.setFF(kFF_Right);
        pid_Right_ss.setOutputRange(kMinOutput_Right, kMaxOutput_Right);

    }

    public void moveShooter(double tsetpoint)
    {
        pid_Right_ss.setReference(tsetpoint, CANSparkMax.ControlType.kVelocity);
        shooterMotorLeft.set(shooterMotorRight.get());
    }

    public void movePercent(double speed){
        shooterMotorRight.set(speed);
        shooterMotorLeft.set(speed);
    }

    public void stop()
    {
        shooterMotorLeft.set(0);
        shooterMotorRight.set(0);
    }

    public double getLidarSpeed(){
        //replace with lidar values
        return .5;
    }
}