package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class VisionTurnLeft extends CommandBase
{

  private DriveTrain driveTrain = RobotContainer.getDriveTrain();

  double bias = 0;

  public VisionTurnLeft(double bias)
  {
    addRequirements(RobotContainer.getDriveTrain(), RobotContainer.getShooter());
    this.bias = bias;


  }

  @Override
  public void initialize() {}

  @Override
  public void execute()
  {

    if(!Robot.target_hasTarget){
      driveTrain.getRight().set(0.5);
      driveTrain.getLeft().set(0.5);
    }
    else{
      if(Robot.target_yaw >= 20){
        driveTrain.getRight().set(-0.4);
        driveTrain.getLeft().set(-0.4);      }
      else if(Robot.target_yaw > 3){
        driveTrain.getRight().set(-0.2);
        driveTrain.getLeft().set(-0.2);      }
      else if(Robot.target_yaw <= -20){
        driveTrain.getRight().set(0.4);
        driveTrain.getLeft().set(0.4);      }
      else if(Robot.target_yaw < -3){
        driveTrain.getRight().set(0.2);
        driveTrain.getLeft().set(0.2);

      }
    }
  }

  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
  }

  @Override
  public boolean isFinished()
  {
    if(!Robot.target_hasTarget || (Robot.target_yaw >= 3 || Robot.target_yaw <= -3))
      return false;

    return true;
  }
}