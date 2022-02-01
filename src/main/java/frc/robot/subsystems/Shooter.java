package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase
{
    private CANSparkMax shooterMotorLeft;
    private CANSparkMax shooterMotorRight;
    private RelativeEncoder lEnc;
    private RelativeEncoder rEnc;
    
    public Shooter(CANSparkMax shooterMotorLeft, CANSparkMax shooterMotorRight, RelativeEncoder lEnc, RelativeEncoder rEnc)
    {
        this.shooterMotorLeft = shooterMotorLeft;
        this.shooterMotorRight = shooterMotorRight;
        this.lEnc = lEnc;
        this.rEnc = rEnc;
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

    public void stop()
    {
        shooterMotorRight.stopMotor();
        shooterMotorLeft.stopMotor();
    }

    public RelativeEncoder getShooterLeftEnc()
    {
        return lEnc;
    }

    public RelativeEncoder getShooterRightENc()
    {
        return rEnc;
    }

}