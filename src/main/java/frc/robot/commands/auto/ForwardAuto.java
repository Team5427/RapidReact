package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class ForwardAuto extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    private final Timer timer = new Timer();
    private double time;
    private double speed;

    public ForwardAuto(double time, double speed)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.time = time;
      this.speed = speed;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        driveTrain.driveGoForward(speed);
    }

    @Override
    public boolean isFinished() {
        if (timer.get() >= time) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
