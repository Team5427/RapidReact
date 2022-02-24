package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TimedTransport extends CommandBase{
    private double time = 0;
    private double shooterTime = 0;
    private Timer timer = new Timer();

    public TimedTransport(){

    }

    @Override
    public void initialize(){
        timer.start();
    }

    @Override
    public void execute(){
        if(timer.get() >= shooterTime){
            RobotContainer.getTransport().move(Constants.TRANSPORT_SPEED);
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
