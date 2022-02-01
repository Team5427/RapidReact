package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase
{

    private MotorController left, right;

    public Elevator(MotorController left, MotorController right)
    {
        this.left = left;
        this.right = right;
    }

    public void setSpeed(double speed) 
    {
        left.set(speed);
        right.set(speed);
    }

    public void setLeft(double speed)
    {
        left.set(speed);
    }

    public void setRight(double speed)
    {
        right.set(speed);
    }

    public void stop() 
    {
        left.stopMotor();
        right.stopMotor();
    }
    
}