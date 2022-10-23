package frc.robot.commands.basic;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
 
public class MoveIntake extends CommandBase
{
    private double speed;
 
    public MoveIntake(double speed)
    {
        addRequirements(RobotContainer.getIntake());
        this.speed = speed;
    }
 
    @Override
    public void initialize()
    {
        RobotContainer.getIntake().moveIntake(speed);
    }
 
    @Override
    public void execute()
    {
        RobotContainer.getIntake().moveIntake(speed);
    }
 
    @Override
    public boolean isFinished(){
        return !RobotContainer.getJoy().getRawButton(Constants.INTAKE_IN_BUTTON) && !RobotContainer.getJoy().getRawButton(4);
    }
 
    @Override
    public void end(boolean interrupted)
    {

        RobotContainer.getIntake().stopIntake();
    }
}