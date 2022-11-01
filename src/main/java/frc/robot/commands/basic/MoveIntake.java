package frc.robot.commands.basic;
 
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.auton.TimedTransport;
 
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
        CommandScheduler.getInstance().schedule(new TimedTransport(0.2, 0.4));
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
