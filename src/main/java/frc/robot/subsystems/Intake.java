package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Intake extends SubsystemBase
{ 
    private CANSparkMax intake;  
    private RelativeEncoder enc;

    public Intake(CANSparkMax intake, RelativeEncoder enc) 
    {
         this.intake = intake;
         this.enc = enc;
    }

    public void moveIntake(double speed)
    {
        intake.set(speed);
    }

    public CANSparkMax getIntakeMotor()
    {
        return intake;
    }

    public RelativeEncoder getIntakeEnc()
    {
        return enc;
    }

    public void stop()
    {
       intake.stopMotor();
    }

    
}