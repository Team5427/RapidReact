package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class PointTurn extends CommandBase{

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();
    private double setPoint;
    private boolean isAuto;
    private boolean reset = false;
    private double slowSpeed;
    private double fastSpeed;
    private double slowDist;

    public PointTurn(double setPoint, boolean isAuto, double slowSpeed, double fastSpeed, double slowDist) {
        addRequirements(RobotContainer.getDriveTrain());
        this.setPoint = setPoint;
        this.isAuto = isAuto;
        this.slowSpeed = slowSpeed;
        this.fastSpeed = fastSpeed;
        this.slowDist = slowDist;
    }

    @Override
    public void initialize()
    {

        RobotContainer.getAHRS().reset();
        reset = true;
        System.out.println("Turn Started");
    }
  
    @Override
    public void execute()
    {
        double angle = Math.abs((RobotContainer.getAHRS().getAngle() < 0)? 360 - Math.abs(RobotContainer.getAHRS().getAngle() % 360): Math.abs(RobotContainer.getAHRS().getAngle() % 360));
        double speed;

        if(Math.abs(angle - setPoint) < slowDist) {
            speed = slowSpeed;
        } 
        else{
            speed = fastSpeed;
        }

        if(setPoint > 180){
            speed *= -1;
        }



        RobotContainer.getDriveTrain().moveRight(speed);

        RobotContainer.getDriveTrain().moveLeft(speed);

    }
  
  
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted)
    {
        System.out.println("Turn Finished");
        driveTrain.stop();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        double angle = Math.abs((RobotContainer.getAHRS().getAngle() < 0)? 360 - Math.abs(RobotContainer.getAHRS().getAngle() % 360): Math.abs(RobotContainer.getAHRS().getAngle() % 360));
        if(reset && (((setPoint > 180) && angle <= setPoint && angle != 0) || ((setPoint < 180) && angle >= setPoint)) && Math.abs(setPoint - angle) < 5){
            reset = false;
            return true;
        }

        return false;
    }
}