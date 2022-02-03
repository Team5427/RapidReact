package frc.robot.commands.auto;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveForwardPID extends CommandBase{
    public double distance;

    public void MoveForwardPID(double distance){
        addRequirements(RobotContainer.getDriveTrain());
        this.distance = distance;
    }

    public void execute(){
        RobotContainer.getDriveTrain().getPIDController().setReference(value, CANSparkMax.ControlType.kPosition)
    }
   
}
