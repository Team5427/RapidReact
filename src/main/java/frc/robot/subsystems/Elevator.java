package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase
{

    private MotorControllerGroup inner, outer, tele;
    private Encoder innerEnc, outerEnc, teleEnc;
    private DigitalInput outerLmt1, outerLmt2, innerLmt1, innerLmt2, teleLmt1, teleLmt2;

    public Elevator(
        MotorControllerGroup inner, 
        MotorControllerGroup outer, 
        MotorControllerGroup tele, 
        Encoder innerEnc, 
        Encoder outerEnc, 
        Encoder teleEnc, 
        DigitalInput outerLmt1, 
        DigitalInput outerLmt2, 
        DigitalInput innerLmt1, 
        DigitalInput innerLmt2, 
        DigitalInput teleLmt1, 
        DigitalInput teleLmt2
    )
    {
        this.inner = inner;
        this.outer = outer;
        this.innerEnc = innerEnc;
        this.outerEnc = outerEnc;
        this.tele = tele;
        this.teleEnc = teleEnc;
        this.outerLmt1 = outerLmt1;
        this.outerLmt2 = outerLmt2;
        this.innerLmt1 = innerLmt1;
        this.innerLmt2 = innerLmt2;
        this.teleLmt1 = teleLmt1;
        this.teleLmt2 = teleLmt2;
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

    public void setOuter(double speed)
    {
        outer.set(speed);
    }

    public void setTele(double speed)
    {
        tele.set(speed);
    }

    public MotorControllerGroup getElevatorInner() 
    {
        return inner;
    }

    public MotorControllerGroup getElevatorOuter() 
    {
        return outer;
    }

    public MotorControllerGroup getTele()
    {
        return tele;
    }

    public Encoder getElevatorEncInner()
    {
        return innerEnc;
    } 

    public Encoder getElevatorEncOuter()
    {
        return outerEnc;
    } 

    public Encoder getTeleEnc()
    {
        return teleEnc;
    }

    public DigitalInput getElevatorLmtInner1()
    {
        return innerLmt1;
    }

    public DigitalInput getElevatorLmtInner2()
    {
        return innerLmt2;
    }

    public DigitalInput getElevatorLmtOuter1()
    {
        return outerLmt1;
    }

    public DigitalInput getElevatorLmtOuter2()
    {
        return outerLmt2;
    }

    public DigitalInput getElevatorLmtTele1()
    {
        return teleLmt1;
    }

    public DigitalInput getElevatorLmtTele2()
    {
        return teleLmt2;
    }


    public void stop() 
    {
        inner.stopMotor();
        outer.stopMotor();
        tele.stopMotor();
    }
    
}