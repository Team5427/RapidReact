package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TimedShooter extends CommandBase{
    private double time = 0;
    private Timer timer = new Timer();

    public TimedShooter(){

    }

    @Override
    public void initialize(){
        timer.start();
        RobotContainer.getShooter().moveShooter(Constants.SHOOTER_TELEOP_SPEED);
    }

    @Override
    public void execute(){

        if(timer.get() >= time){
            RobotContainer.getShooter().stop();
        }
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
