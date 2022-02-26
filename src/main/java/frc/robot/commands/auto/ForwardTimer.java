package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class ForwardTimer extends CommandBase {

    private Timer timer = new Timer();
    private double ctimer;
    private double speed;
    
    public ForwardTimer(double ctimer, double speed)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.ctimer = ctimer;
      this.speed = speed;
    }

    @Override
    public void initialize() {
        System.out.println("Started Moving Forward at " + (int)speed * 100 + "% for " + ctimer + "s");
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
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
