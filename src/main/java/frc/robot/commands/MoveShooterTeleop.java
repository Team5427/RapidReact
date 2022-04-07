package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class MoveShooterTeleop extends CommandBase
{
    public static double setPointFinal;
    
    public MoveShooterTeleop()
    {
        addRequirements();
    }

    @Override
    public void initialize() {

        setPointFinal = SmartDashboard.getNumber("Change RPI", 4560);
        RobotContainer.getShooter().shooterInitRight();
    }

    @Override
    public void execute() {

        // RobotContainer.getShooter().moveShooter(5000);
        
        RobotContainer.getShooter().movePercent((RobotContainer.getJoy().getRawAxis(3) + 1)/2);

        // System.out.println("Shooter is running " + RobotContainer.getJoy().getRawButton(1));
    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.MANUAL_SHOOTER_BUTTON);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
    }
}