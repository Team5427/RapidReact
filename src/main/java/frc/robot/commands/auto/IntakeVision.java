package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class IntakeVision extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    double bias = 0;
    double err;
    double setSpeedLeft = -0.2;
    double setSpeedRight = 0.2;
    boolean locked;
    /**
     * Creates a new MoveStraight.
     */
  
    //bias based on distance model in case it is needed
    public IntakeVision(double bias)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.bias = bias;
    }

    @Override
    public void initialize() {
        //locked = false;
    }

    @Override
    public void execute() {
        err = Robot.ball_yaw;
        
        
        if(!Robot.ball_hasTarget){
            driveTrain.getRight().set(-0.2);
            driveTrain.getLeft().set(-0.2);
        } else {

            //locked = true;
            if (err < -3) {
                setSpeedLeft = -0.3;
                setSpeedRight = 0.4;
            } if (err > 3) {
                setSpeedRight = 0.3;
                setSpeedLeft = -0.4;
            }
            
            driveTrain.getLeft().set(setSpeedLeft);
            driveTrain.getRight().set(setSpeedRight);
        }
    }

    @Override
    public boolean isFinished() {
        if (Robot.ball_pitch <= -12 && (Robot.ball_yaw >= -3 || Robot.ball_yaw <= 3)) {
            return true;
        }
        //return !(RobotContainer.getJoy().getRawButton(1));
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stop();
    }
    
}
