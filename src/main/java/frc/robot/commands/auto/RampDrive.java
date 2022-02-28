package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class RampDrive extends CommandBase {

    private Timer timer = new Timer();
    private double ctimer;
    private double finalSpeed;
    private double startingSpeed;
    private double speed;
    private double multiplier;
    private boolean isAccel;

    
    public RampDrive(double ctimer, double startingSpeed, double multiplier, double finalSpeed, boolean isAccel)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.ctimer = ctimer;
      this.startingSpeed = startingSpeed;
      this.finalSpeed = finalSpeed;
      this.multiplier = multiplier;
      this.isAccel = isAccel;
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
        if(speed <= finalSpeed && isAccel){
            speed *= multiplier;
        } else if (speed >= finalSpeed && !isAccel) {
            speed *= 1/multiplier;
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
