package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class RampDrive extends CommandBase {

    private Timer timer = new Timer();
    private double ctimer;
    private double maxSpeed;
    private double startingSpeed;
    private double speed;
    private double multiplier;

    
    public RampDrive(double ctimer, double startingSpeed, double multiplier, double maxspeed)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.ctimer = ctimer;
      this.startingSpeed = startingSpeed;
      this.maxSpeed = maxSpeed;
      this.multiplier = multiplier;
    }

    @Override
    public void initialize() {
        System.out.println("Started Moving Forward at " + (int)startingSpeed * 100 + "% for " + ctimer + "s");
        timer.reset();
        timer.start();
        speed = startingSpeed;
    }

    @Override
    public void execute() {
        if(speed < maxSpeed){
            speed *= multiplier;
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
