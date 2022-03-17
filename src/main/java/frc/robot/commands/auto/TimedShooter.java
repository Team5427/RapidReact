package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TimedShooter extends CommandBase{
    private double time;
    private double setPoint;
    private Timer timer = new Timer();

    public TimedShooter(double time, double setPoint)
    {
        addRequirements(RobotContainer.getShooter());
        this.time = time;
        this.setPoint = setPoint;
    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();
        RobotContainer.getShooter().movePercent(setPoint);
    }

    @Override
    public void execute()
    {
        RobotContainer.getShooter().movePercent(setPoint);
    }

    @Override 
    public boolean isFinished(){
        return timer.get() >= time;
    }
    
    @Override
    public void end(boolean interrupted){
        RobotContainer.getShooter().stop();
        timer.stop();
        timer.reset();
    }

}
