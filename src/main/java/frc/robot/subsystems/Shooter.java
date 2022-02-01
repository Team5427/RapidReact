package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Shooter extends SubsystemBase
{
    private MotorController shooterMotorTop;
    private MotorController shooterMotorBottom;
    
    public Shooter(MotorController shooterMotorTop, MotorController shooterMotorBottom)
    {
        this.shooterMotorTop = shooterMotorTop;
        this.shooterMotorBottom = shooterMotorBottom;
    }

    public MotorController getShooterMotorTop()
    {
        return shooterMotorTop;
    }

    public MotorController getShooterMotorBottom()
    {
        return shooterMotorBottom;
    }

    public void moveShooter(double speed)
    {
        shooterMotorBottom.set(-speed);
        shooterMotorTop.set(speed);
    }

    public void stop()
    {
        shooterMotorBottom.set(0);
        shooterMotorTop.set(0);
    }

}