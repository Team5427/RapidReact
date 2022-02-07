package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class ShootVision extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    double bias = 0;

    public ShootVision(double bias)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.bias = bias;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {

        if (!Robot.target_hasTarget) {
            driveTrain.getLeft().set(0.5);
            driveTrain.getRight().set(0.5);
        } else if (Robot.target_yaw <= -13) {
            driveTrain.getLeft().set(0.5);
            driveTrain.getRight().set(0.5);
        } else if (Robot.target_yaw >= 13) {
            driveTrain.getLeft().set(-0.5);
            driveTrain.getRight().set(-0.5);
        } else if (Robot.target_yaw > -13 && Robot.target_yaw <= -5) {
            driveTrain.getLeft().set(0.2);
            driveTrain.getRight().set(0.2);
        } else if (Robot.target_yaw < 13 && Robot.target_yaw >= 5) {
            driveTrain.getLeft().set(-0.2);
            driveTrain.getRight().set(-0.2); 
        } else if (Robot.target_yaw > -5 && Robot.target_yaw <= -2) {
            driveTrain.getLeft().set(0.15);
            driveTrain.getRight().set(0.15);
        } else if (Robot.target_yaw < 5 && Robot.target_yaw >= 2) {
            driveTrain.getLeft().set(-0.15);
            driveTrain.getRight().set(-0.15);
        }
    }

    @Override
    public boolean isFinished() {
        
        if (driveTrain.getDriveEncBL().getVelocity() >= -5 && driveTrain.getDriveEncBL().getVelocity() >= -5) {
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
