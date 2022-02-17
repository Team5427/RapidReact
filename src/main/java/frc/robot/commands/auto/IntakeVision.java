package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class IntakeVision extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    private double err;
    private double setSpeedLeft;
    private double setSpeedRight;

    private double vSpeedSlow;
    private double vSpeedFast;
    private double vTurnSpeed;

    private boolean isOnRight;

    public IntakeVision(double vSpeedSlow, double vSpeedFast, double vTurnSpeed, boolean isOnRight)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.isOnRight = isOnRight;
      this.vSpeedSlow = vSpeedSlow;
      this.vSpeedFast = vSpeedFast;
      this.vTurnSpeed = vTurnSpeed;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        
        err = Robot.ball_yaw;
        
        if(!Robot.ball_hasTarget && isOnRight){
            driveTrain.getRight().set(vTurnSpeed);
            driveTrain.getLeft().set(vTurnSpeed);
        } else if(!Robot.ball_hasTarget && !isOnRight) {
            driveTrain.getRight().set(-vTurnSpeed);
            driveTrain.getLeft().set(-vTurnSpeed);
        } else if(Robot.ball_hasTarget) {
            if (err < -2) {
                setSpeedLeft = -vSpeedSlow;
                setSpeedRight = vSpeedFast;
            } if (err > 2) {
                setSpeedLeft = -vSpeedFast;
                setSpeedRight = vSpeedSlow;
            }
            
            driveTrain.getLeft().set(setSpeedLeft);
            driveTrain.getRight().set(setSpeedRight);
        }
    }

    @Override
    public boolean isFinished() {
        if (Robot.ball_pitch <= -5 && (Robot.ball_yaw >= -2 || Robot.ball_yaw <= 2)) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
