package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase
{ 
    private MotorController intake;  
    private Encoder enc;

    public Intake(MotorController intake, Encoder enc) 
    {
         this.intake = intake;
         this.enc = enc;
    }

    public void moveIntake(double speed)
    {
        intake.set(speed);
    }

    public MotorController getIntakeMotor()
    {
        return intake;
    }

    public Encoder getIntakeEnc()
    {
        return enc;
    }

    public void stop()
    {
       intake.stopMotor();
    }

    
}