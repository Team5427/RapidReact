package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class RampDrive extends CommandBase {

    private Timer timer = new Timer();
    private double ctimer;
    private double finalSpeed;
    private double speed;
    
    public RampDrive(double ctimer, double speed, double finalspeed)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.ctimer = ctimer;
      this.finalSpeed = finalSpeed;
    }

    @Override
    public void initialize() {
        System.out.println("Started Moving Forward at " + (int)speed * 100 + "% for " + ctimer + "s");
        timer.reset();
        timer.start();
        speed = .1;
    }

    @Override
    public void execute() {
        if(speed < finalSpeed){
            speed *= 1.5;
        }
        RobotContainer.getDriveTrain().moveLeft(-speed);
        RobotContainer.getDriveTrain().moveRight(-speed);
    }

    @Override
    public boolean isFinished() {
        if (timer.get() >= ctimer) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Finished Moving Forward");
        RobotContainer.getDriveTrain().stop();
    }
    
}
