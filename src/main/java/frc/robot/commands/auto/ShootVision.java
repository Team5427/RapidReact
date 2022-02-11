package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class ShootVision extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    double bias = 0;
    private double err;
    boolean autoTurnIsRight;

    public ShootVision(double bias, boolean autoTurnIsRight)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.bias = bias;
      this.autoTurnIsRight = autoTurnIsRight;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        err = Robot.target_yaw;

        if (Robot.target_hasTarget) {
            if (err <= -13) {
                driveTrain.getLeft().set(0.5);
                driveTrain.getRight().set(0.5);
            } else if (err >= 13) {
                driveTrain.getLeft().set(-0.5);
                driveTrain.getRight().set(-0.5);
            } else if (err > -13 && err <= -5) {
                driveTrain.getLeft().set(0.2);
                driveTrain.getRight().set(0.2);
            } else if (err < 13 && err >= 5) {
                driveTrain.getLeft().set(-0.2);
                driveTrain.getRight().set(-0.2); 
            } else if (err > -5 && err <= -2) {
                driveTrain.getLeft().set(0.15);
                driveTrain.getRight().set(0.15);
            } else if (err < 5 && err >= 2) {
                driveTrain.getLeft().set(-0.15);
                driveTrain.getRight().set(-0.15);
            }
        } else if (!Robot.target_hasTarget && autoTurnIsRight) {
            driveTrain.getLeft().set(0.5);
            driveTrain.getRight().set(0.5);
        } else if (!Robot.target_hasTarget && !autoTurnIsRight) {
            driveTrain.getLeft().set(-0.5);
            driveTrain.getRight().set(-0.5);
        }
    }

    @Override
    public boolean isFinished() {
        if (driveTrain.getDriveEncBL().getVelocity() >= -5 && driveTrain.getDriveEncBL().getVelocity() >= -5 && err <= 2 && err >= 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
