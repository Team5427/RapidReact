package frc.robot.commands.auto;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class TargetVision extends CommandBase
{

  private DriveTrain driveTrain = RobotContainer.getDriveTrain();

  private boolean hasTarget;
  private double err;
  private int counter;
  private boolean isCW;
  private PhotonCamera cam;
  private PhotonTrackedTarget target;
  private double fastSpeed = .25;
  private double medSpeed = .15;
  private double slowSpeed = .15;
  private double smallAdjustSpeed = .05;
  /**
   * Creates a new MoveStraight.
   */

  //bias based on distance model in case it is needed
  public TargetVision(boolean isCW)
  {
    addRequirements(RobotContainer.getDriveTrain());
    this.isCW = isCW;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    System.out.println("Target Tracking Started");
    cam = new PhotonCamera("photoncam");
    counter = 0;
    hasTarget = false;
  }

  @Override
  public void execute()
  {
      

    hasTarget = (RobotContainer.getLimeLight().getEntry("tv").getDouble(0) == 0)?false:true;
    if(hasTarget){
        err = RobotContainer.getLimeLight().getEntry("tx").getDouble(0);
    }


    if(!hasTarget && isCW){
      driveTrain.moveRight(-fastSpeed);
      driveTrain.moveLeft(fastSpeed);   
    } else if (!hasTarget && !isCW) {
      driveTrain.moveRight(fastSpeed);
      driveTrain.moveLeft(-fastSpeed);
    } else {
      if(err >= 20){
        driveTrain.moveRight(-fastSpeed);
        driveTrain.moveLeft(fastSpeed);   
      } else if (!hasTarget && !isCW) {
        driveTrain.moveRight(fastSpeed);
        driveTrain.moveLeft(-fastSpeed);
      } else {
        if(err >= 20){
          driveTrain.moveRight(-fastSpeed);
          driveTrain.moveLeft(fastSpeed);
        } else if (err >= 12) {
          driveTrain.moveRight(-slowSpeed);
          driveTrain.moveLeft(slowSpeed);
        } else if (err >= 4) {
          driveTrain.moveRight(-slowSpeed);
          driveTrain.moveLeft(slowSpeed); 
        } else if(err >= 1.5){
          driveTrain.moveRight(-smallAdjustSpeed);
          driveTrain.moveLeft(smallAdjustSpeed);      
        }  else if(err > 1){
          driveTrain.moveRight(-smallAdjustSpeed / 2);
          driveTrain.moveLeft(smallAdjustSpeed / 2);

        } else if(err <= -20){
          driveTrain.moveRight(fastSpeed);
          driveTrain.moveLeft(-fastSpeed);
        } else if (err <= -12) {
          driveTrain.moveRight(slowSpeed);
          driveTrain.moveLeft(-slowSpeed);
        } else if (err <= -4) {
          driveTrain.moveRight(slowSpeed);
          driveTrain.moveLeft(-slowSpeed);    
        } else if(err <= -1.5){
          driveTrain.moveRight(smallAdjustSpeed);
          driveTrain.moveLeft(-smallAdjustSpeed);

        } else if(err < -1){
          driveTrain.moveRight(smallAdjustSpeed / 2);
          driveTrain.moveLeft(-smallAdjustSpeed / 2);

        } 
      }
    }
    System.out.println(err);
    
  }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    System.out.println("Target Tracking Finished");
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if(RobotContainer.getJoy().getRawButton(12)){
      return true;
    }
    if(err > -1 && err < 1 && hasTarget) 
    {
      counter++;
      if(counter > 6){
        return true;
      }
    }


  return false;
  }

}