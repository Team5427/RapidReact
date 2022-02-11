package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class IntakeVision extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    double bias = 0;
    double err;
    double setSpeedLeft;
    double setSpeedRight;

    boolean isOnRight;
    boolean locked;

    public IntakeVision(double bias, boolean isOnRight)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.bias = bias;
      this.isOnRight = isOnRight;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        
        err = Robot.ball_yaw;
        
        if(!Robot.ball_hasTarget && isOnRight){
            driveTrain.getRight().set(0.5);
            driveTrain.getLeft().set(0.5);
        } else if(!Robot.ball_hasTarget && !isOnRight) {
            driveTrain.getRight().set(-0.5);
            driveTrain.getLeft().set(-0.5);
        } else if(Robot.ball_hasTarget) {
            if (err < -2) {
                setSpeedLeft = -0.3;
                setSpeedRight = 0.4;
            } if (err > 2) {
                setSpeedLeft = -0.4;
                setSpeedRight = 0.3;
            }
            
            driveTrain.getLeft().set(setSpeedLeft);
            driveTrain.getRight().set(setSpeedRight);
        }
    }

    @Override
    public boolean isFinished() {
        if (Robot.ball_pitch <= -14 && (Robot.ball_yaw >= -2 || Robot.ball_yaw <= 2)) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
