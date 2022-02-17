package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class ShootVision extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    private double err;
    private boolean autoTurnIsRight;
    private double speed1;
    private double speed2;
    private double speed3;

    public ShootVision(double speed1, double speed2, double speed3, boolean autoTurnIsRight)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.autoTurnIsRight = autoTurnIsRight;
      this.speed1 = speed1;
      this.speed2 = speed2;
      this.speed3 = speed3;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        err = Robot.target_yaw;

        if (Robot.target_hasTarget) {
            if (err <= -13) {
                driveTrain.getLeft().set(speed1);
                driveTrain.getRight().set(speed1);
            } else if (err >= 13) {
                driveTrain.getLeft().set((speed1));
                driveTrain.getRight().set((speed1));
            } else if (err > -13 && err <= -5) {
                driveTrain.getLeft().set(speed2);
                driveTrain.getRight().set(speed2);
            } else if (err < 13 && err >= 5) {
                driveTrain.getLeft().set(-speed2);
                driveTrain.getRight().set(-speed2); 
            } else if (err > -5 && err <= -2) {
                driveTrain.getLeft().set(speed3);
                driveTrain.getRight().set(speed3);
            } else if (err < 5 && err >= 2) {
                driveTrain.getLeft().set(-speed3);
                driveTrain.getRight().set(-speed3);
            }
        } else if (!Robot.target_hasTarget && autoTurnIsRight) {
            driveTrain.getLeft().set(speed1);
            driveTrain.getRight().set(speed1);
        } else if (!Robot.target_hasTarget && !autoTurnIsRight) {
            driveTrain.getLeft().set((speed1));
            driveTrain.getRight().set((speed1));
        }
    }

    @Override
    public boolean isFinished() {
        if (driveTrain.getDriveEncBL().getVelocity() >= -50 && driveTrain.getDriveEncBL().getVelocity() >= -50 && err <= 2 && err >= 2) {
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
