package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase
{

    private MotorController inner1, inner2, outer1, outer2, tele1, tele2;
    private MotorControllerGroup inner, outer, tele;
    private Encoder innerEnc1, innerEnc2, outerEnc1, outerEnc2, teleEnc1, teleEnc2;
    private DigitalInput outerLmt1, outerLmt2, innerLmt1, innerLmt2, teleLmt1, teleLmt2;

    public Climber(
        MotorController inner1, 
        MotorController inner2, 
        MotorController outer1, 
        MotorController outer2, 
        MotorController tele1, 
        MotorController tele2, 
        MotorControllerGroup inner, 
        MotorControllerGroup outer, 
        MotorControllerGroup tele, 
        Encoder innerEnc1, 
        Encoder innerEnc2, 
        Encoder outerEnc1, 
        Encoder outerEnc2, 
        Encoder teleEnc1, 
        Encoder teleEnc2, 
        DigitalInput outerLmt1, 
        DigitalInput outerLmt2, 
        DigitalInput innerLmt1, 
        DigitalInput innerLmt2, 
        DigitalInput teleLmt1, 
        DigitalInput teleLmt2
    )
    {
        this.inner1 = inner1;
        this.inner2 = inner2;
        this.outer1 = outer1;
        this.outer2 = outer2;
        this.tele1 = tele1;
        this.tele2 = tele2;
        this.inner = inner;
        this.outer = outer;
        this.tele = tele;
        this.innerEnc1 = innerEnc1;
        this.innerEnc2 = innerEnc2;
        this.outerEnc1 = outerEnc1;
        this.outerEnc2 = outerEnc2;
        this.teleEnc1 = teleEnc1;
        this.teleEnc2 = teleEnc2;
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

    public void setInner1(double speed)
    {
        inner1.set(speed);
    }

    public void setInner2(double speed)
    {
        inner2.set(speed);
    }

    public void setOuter1(double speed)
    {
        outer1.set(speed);
    }

    public void setOuter2(double speed)
    {
        outer2.set(speed);
    }

    public void setTele1(double speed)
    {
        tele1.set(speed);
    }

    public void setTele2(double speed)
    {
        tele2.set(speed);
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

    public MotorControllerGroup getClimberInner() 
    {
        return inner;
    }

    public MotorControllerGroup getClimberOuter() 
    {
        return outer;
    }

    public MotorControllerGroup getTele()
    {
        return tele;
    }

    public Encoder getClimberEncInner1()
    {
        return innerEnc1;
    } 

    public Encoder getClimberEncInner2()
    {
        return innerEnc2;
    } 

    public Encoder getClimberEncOuter1()
    {
        return outerEnc1;
    } 

    public Encoder getClimberEncOuter2()
    {
        return outerEnc2;
    } 

    public Encoder getTeleEnc1()
    {
        return teleEnc1;
    }

    public Encoder getTeleEnc2()
    {
        return teleEnc2;
    }

    public DigitalInput getClimberLmtInner1()
    {
        return innerLmt1;
    }

    public DigitalInput getClimberLmtInner2()
    {
        return innerLmt2;
    }

    public DigitalInput getClimberLmtOuter1()
    {
        return outerLmt1;
    }

    public DigitalInput getClimberLmtOuter2()
    {
        return outerLmt2;
    }

    public DigitalInput getClimberLmtTele1()
    {
        return teleLmt1;
    }

    public DigitalInput getClimberLmtTele2()
    {
        return teleLmt2;
    }


    public void stop() 
    {
        inner.stopMotor();
        outer.stopMotor();
        tele.stopMotor();
    }

    public void stopInner()
    {
        inner.stopMotor();
    }
    
    public void stopOuter()
    {
        outer.stopMotor();
    }
    
    public void stopTele()
    {
        tele.stopMotor();
    }

    public void stopInner1()
    {
        inner1.stopMotor();
    }

    public void stopInner2()
    {
        inner2.stopMotor();
    }

    public void stopOuter1()
    {
        outer1.stopMotor();
    }

    public void stopOuter2()
    {
        outer2.stopMotor();
    }

    public void stopTele1()
    {
        tele1.stopMotor();
    }

    public void stopTele2()
    {
        tele2.stopMotor();
    }
    
}