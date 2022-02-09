package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase
{

    private MotorControllerGroup inner, outer;
    private Encoder innerEnc, outerEnc;

    public Elevator(MotorControllerGroup inner, MotorControllerGroup outer, Encoder innerEnc, Encoder outerEnc)
    {
        this.inner = inner;
        this.outer = outer;
        this.innerEnc = innerEnc;
        this.outerEnc = outerEnc;
    }

    public void setSpeed(double speed) 
    {
        inner.set(speed);
        outer.set(speed);
    }

    public void setInner(double speed)
    {
        inner.set(speed);
    }

    public void setouter(double speed)
    {
        outer.set(speed);
    }

    public MotorControllerGroup getElevatorInner() 
    {
        return inner;
    }

    public MotorControllerGroup getElevatorOuter() 
    {
        return outer;
    }

    public Encoder getElevatorEncInner()
    {
        return innerEnc;
    } 

    public Encoder getElevatorEncOuter()
    {
        return outerEnc;
    } 

    public void stop() 
    {
        inner.stopMotor();
        outer.stopMotor();
    }
    
}