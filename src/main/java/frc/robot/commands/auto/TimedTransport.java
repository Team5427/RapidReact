package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TimedTransport extends CommandBase{
    private double time;
    private double shooterTime = 1;
    private Timer timer = new Timer();

    public TimedTransport(double time)
    {
        addRequirements(RobotContainer.getTransport());
        this.time = time;
        shooterTime = 1;

    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();
    }

    @Override
    public void execute(){
        if(timer.get() >= shooterTime){
            RobotContainer.getTransport().move(.4);
        }
        if(timer.get() >= time + shooterTime){
            RobotContainer.getTransport().stop();
        }
    }

    @Override 
    public boolean isFinished(){
        return timer.get() >= time + shooterTime;
    }
    
    @Override
    public void end(boolean interrupted){
        RobotContainer.getTransport().stop();
        timer.stop();
        timer.reset();
    }

}
