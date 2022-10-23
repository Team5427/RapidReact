package frc.robot.commands.complex;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class Target extends CommandBase
{

  private DriveTrain driveTrain = RobotContainer.getDriveTrain();

  private boolean hasTarget;
  private double err;
  private boolean isCW;
  private double fastSpeed = .3;
  private double medSpeed = .2;
  private double slowSpeed = .1;
  private double smallAdjustSpeed = .07;
  private boolean isAuto = false;
  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public Target(boolean isCW, boolean isAuto)
  {
    addRequirements(RobotContainer.getDriveTrain());
    this.isCW = isCW;
    this.isAuto = isAuto;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    hasTarget = false;
  }

  @Override
  public void execute()
  {
      

    hasTarget = RobotContainer.getLimeLight().targetVisible();
    if(hasTarget){
        err = RobotContainer.getLimeLight().targetX();
    }


    if(!hasTarget && isCW){
      driveTrain.moveRight(-fastSpeed * 2.5);
      driveTrain.moveLeft(fastSpeed * 2.5);   
    } else if (!hasTarget && !isCW) {
      driveTrain.moveRight(fastSpeed * 2.5);
      driveTrain.moveLeft(-fastSpeed * 2.5);
    } else {
      if(err >= 20){
        driveTrain.moveRight(-fastSpeed);
        driveTrain.moveLeft(fastSpeed);
      } else if (err >= 14) {
        driveTrain.moveRight(-medSpeed);
        driveTrain.moveLeft(medSpeed);
      } else if (err >= 7) {
        driveTrain.moveRight(-slowSpeed);
        driveTrain.moveLeft(slowSpeed); 
      } else if(err >= 5){
        driveTrain.moveRight(-smallAdjustSpeed);
        driveTrain.moveLeft(smallAdjustSpeed);      
      }  else if(err > 1){
        driveTrain.moveRight(-smallAdjustSpeed / 2);
        driveTrain.moveLeft(smallAdjustSpeed / 2);
      } else if(err <= -20){
        driveTrain.moveRight(fastSpeed);
        driveTrain.moveLeft(-fastSpeed);
      } else if (err <= -14) {
        driveTrain.moveRight(medSpeed);
        driveTrain.moveLeft(-medSpeed);
      } else if (err <= -7) {
        driveTrain.moveRight(slowSpeed);
        driveTrain.moveLeft(-slowSpeed);    
      } else if(err <= -5){
        driveTrain.moveRight(smallAdjustSpeed);
        driveTrain.moveLeft(-smallAdjustSpeed);
      } else if(err < -1){
        driveTrain.moveRight(smallAdjustSpeed / 1.5);
        driveTrain.moveLeft(-smallAdjustSpeed / 1.5);
      } 
    }
  }

  @Override
  public void end(boolean interrupted)
  {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if ((!RobotContainer.getJoy().getRawButton(1) && !isAuto)) {
      return true;
    }
  return false;
  }

}