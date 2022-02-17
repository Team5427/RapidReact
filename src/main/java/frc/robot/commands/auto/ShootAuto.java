package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class ShootAuto extends CommandBase {

    private Shooter shooter;
    private final Timer timer = new Timer();
    private double time;

    public ShootAuto(double time)
    {
      addRequirements(RobotContainer.getShooter());
      this.time = time;
    }

    @Override
    public void initialize() {
        shooter = RobotContainer.getShooter();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        shooter.moveShooterAutonPID();
    }

    @Override
    public boolean isFinished() {
        if (timer.get() >= time) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop();
        timer.reset();
        shooter.stop();
    }
    
}
