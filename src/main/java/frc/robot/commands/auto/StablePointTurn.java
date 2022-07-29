package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class StablePointTurn extends CommandBase{

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private double setPoint;
    private double slowSpeed;
    private double fastSpeed;
    private double slowDist;
    private double raw_angle;
    private double raw_angle_sign;
    private double curr_angle;
    private double err;
    private double adj_err;
    private double speed;
    private double counter;

    public StablePointTurn(double setPoint, double slowSpeed, double fastSpeed, double slowDist) {
        addRequirements(RobotContainer.getDriveTrain());
        this.setPoint = setPoint;
        this.slowSpeed = slowSpeed;
        this.fastSpeed = fastSpeed;
        this.slowDist = slowDist;
    }

    @Override
    public void initialize()
    {
        RobotContainer.getAHRS().reset();
        counter = 0;
    }
  
    @Override
    public void execute()
    {
        raw_angle = Math.abs(RobotContainer.getAHRS().getAngle()) % 360; //215 +- 10
        raw_angle_sign = Math.signum(RobotContainer.getAHRS().getAngle());
        curr_angle = (raw_angle_sign > 0) ? (raw_angle) : (360 - raw_angle); // works in the same range of motion as the setpoint (1 - 360 repeating), even backwards
        err = Math.abs(setPoint - curr_angle);
        adj_err = (err <= 180) ? (err) : (360 - err); // absolute distance from setpoint ; works over 0-line

        if (adj_err > slowDist) {
            speed = fastSpeed;
        } else if (adj_err <= slowDist) {
            speed = slowSpeed;
        }

        if(setPoint > 180 && raw_angle <= 3){
            speed *= -1;
        } else if (setPoint <= 180 && raw_angle >= 357) {
            speed *= 1;
        } else {
            speed *= Math.signum(setPoint - curr_angle); // accounts for overshoot
        }

        RobotContainer.getDriveTrain().moveRight(speed);
        RobotContainer.getDriveTrain().moveLeft(-speed);

    }
  
    @Override
    public void end(boolean interrupted)
    {
        driveTrain.stop();
    }
  
    @Override
    public boolean isFinished()
    {
        if (adj_err <= 10) {
            counter++;
            if (counter >= 7) {
                return true;
            }
        }
        return false;
    }
}