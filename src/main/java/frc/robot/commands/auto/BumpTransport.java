package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class BumpTransport extends CommandBase{
    private double time;
    private Timer timer = new Timer();
    private double speed;

    public BumpTransport(double time, double speed)
    {
        addRequirements(RobotContainer.getTransport());
        this.time = time;
        this.speed = speed;

    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();
    }

    @Override
    public void execute(){
        RobotContainer.getTransport().move(speed);
    }

    @Override 
    public boolean isFinished(){
        return timer.get() >= time;
    }
    
    @Override
    public void end(boolean interrupted){
        RobotContainer.getTransport().stop();
        System.out.println("x");
        timer.stop();
        timer.reset();
    }

}
