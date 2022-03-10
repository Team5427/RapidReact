package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AutoTiltDown extends CommandBase {
    private Timer timer = new Timer();
    private double ctimer;
    private  double speed;
    
    public AutoTiltDown(double ctimer, double speed) {
        addRequirements(RobotContainer.getTilt());
        this.ctimer = ctimer;
        this.speed = speed;

    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        RobotContainer.getTilt().setSpeed(speed);
    }
    @Override
    public boolean isFinished() {
        return (timer.get() >= ctimer);
        
    }
    @Override
    public void end(boolean interupted) {
        timer.reset();
        RobotContainer.getTilt().stop();

    }


    
}
