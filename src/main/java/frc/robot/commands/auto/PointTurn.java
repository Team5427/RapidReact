package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class PointTurn extends CommandBase{

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private double setPoint;
    private double slowSpeed;
    private double fastSpeed;
    private double speed;
    private boolean reset = false;

    public PointTurn(double setPoint, double slowSpeed, double fastSpeed) {
        addRequirements(RobotContainer.getDriveTrain());
        this.setPoint = setPoint;
        this.slowSpeed = slowSpeed;
        this.fastSpeed = fastSpeed;
    }

    @Override
    public void initialize()
    {
        RobotContainer.getAHRS().reset();
        reset = true;
    }
  
    @Override
    public void execute()
    {
        double angle = Math.abs((RobotContainer.getAHRS().getAngle() < 0)? 360 - Math.abs(RobotContainer.getAHRS().getAngle() % 360): Math.abs(RobotContainer.getAHRS().getAngle() % 360));

        if(Math.abs(angle - setPoint) < 20) {
            speed = fastSpeed; //.15
        } 
        else{
            speed = slowSpeed; //.3
        }

        if(setPoint > 180){
            speed *= -1;
        }
        
            driveTrain.drivePointTurnLeft(speed);
    }
  
  
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted)
    {
        driveTrain.stop();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        double angle = Math.abs((RobotContainer.getAHRS().getAngle() < 0)? 360 - Math.abs(RobotContainer.getAHRS().getAngle() % 360): Math.abs(RobotContainer.getAHRS().getAngle() % 360));
        if(reset && ((setPoint > 180) && angle <= setPoint && angle != 0) || ((setPoint < 180) && angle >= setPoint)){
            reset = false;
            return true;
        }

        return false;
    }
}