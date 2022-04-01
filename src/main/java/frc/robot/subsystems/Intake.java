package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Intake extends SubsystemBase
{
    private MotorController intakeMotor;
      
    public Intake(MotorController intakeMotor)
    {
        this.intakeMotor = intakeMotor;
    }

    public void moveIntake(double speed)
    {
        intakeMotor.set(speed);
    }

    public void stopIntake()
    {
        intakeMotor.stopMotor();
    }
    
}


