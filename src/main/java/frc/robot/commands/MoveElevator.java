package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveElevator extends CommandBase
{
    private double speed;
    public static double leftLimit = 6259.75;
    public static double rightLimit = -6264.0;

    public MoveElevator(double speed)
    {
        addRequirements(RobotContainer.getElevator());
        this.speed = speed;
    }
    @Override
    public void initialize() 
    {
        RobotContainer.getElevator().setSpeed(speed);
    }

    @Override
    public void execute() 
    {
        //right: -5383.0
        //left: 5390.5
        RobotContainer.getElevator().setSpeed(speed);
    }

    @Override
    public boolean isFinished() 
    {
        return !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON);
        
    }

    @Override
    public void end(boolean interrupted) 
    {
        RobotContainer.getElevator().stop();
    }
}