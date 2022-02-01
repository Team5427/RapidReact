package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.MoveTransportIntake;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class Transport extends SubsystemBase
{

    private MotorController transportMotor;  
       
    public Transport (MotorController transportMotor) 
    {
        this.transportMotor = transportMotor;
    }

    public void stop()
    {
        transportMotor.stopMotor();
    }

    public void moveTransport(double speed)
    {
        transportMotor.set(speed);
    }

}