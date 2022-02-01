package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase
{

    private CANSparkMax left, right;
    private RelativeEncoder leftEnc, rightEnc;

    public Elevator(CANSparkMax left, CANSparkMax right, RelativeEncoder leftEnc, RelativeEncoder rightEnc)
    {
        this.left = left;
        this.right = right;
        this.leftEnc = leftEnc;
        this.rightEnc = rightEnc;
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

    public CANSparkMax getElevatorLeft() 
    {
        return left;
    }

    public CANSparkMax getElevatorRight() 
    {
        return right;
    }

    public RelativeEncoder getElevatorEncLeft()
    {
        return leftEnc;
    } 

    public RelativeEncoder getElevatorEncRight()
    {
        return rightEnc;
    } 

    public void stop() 
    {
        left.stopMotor();
        right.stopMotor();
    }
    
}