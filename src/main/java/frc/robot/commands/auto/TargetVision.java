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
  private double fastSpeed = .3;
  private double medSpeed = .2;
  private double slowSpeed = .15;
  private double smallAdjustSpeed = .1;
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

    if(cam.getLatestResult().hasTargets()){
      hasTarget = cam.getLatestResult().hasTargets();
      target = cam.getLatestResult().getBestTarget();
      err = target.getYaw();
    }

    if(!hasTarget && isCW){
      driveTrain.moveRight(-0.5);
      driveTrain.moveLeft(0.5);   
      System.out.println("Not on cam CW");  
    } else if (!hasTarget && !isCW) {
      driveTrain.moveRight(0.5);
      driveTrain.moveLeft(-0.5);
      System.out.println("Not on cam !CW");
    } else {
      System.out.println("on cam.");
      if(err >= 20){
        driveTrain.moveRight(-fastSpeed);
        driveTrain.moveLeft(fastSpeed);
      } else if (err >= 6) {
        driveTrain.moveRight(-medSpeed);
        driveTrain.moveLeft(medSpeed);
      } else if (err >= 4) {
        driveTrain.moveRight(-slowSpeed);
        driveTrain.moveLeft(slowSpeed); 
      } else if(err > 1){
        driveTrain.moveRight(-smallAdjustSpeed);
        driveTrain.moveLeft(smallAdjustSpeed);      
      } else if(err <= -20){
        driveTrain.moveRight(fastSpeed);
        driveTrain.moveLeft(-fastSpeed);
      } else if (err <= -6) {
        driveTrain.moveRight(medSpeed);
        driveTrain.moveLeft(-medSpeed);
      } else if (err <= -4) {
        driveTrain.moveRight(slowSpeed);
        driveTrain.moveLeft(-slowSpeed);    
      } else if(err < -1){
        driveTrain.moveRight(smallAdjustSpeed);
        driveTrain.moveLeft(-smallAdjustSpeed);

      } 
    }

    
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
    if(err > -2 && err < 2 && hasTarget) 
    {
      counter++;
      if(counter > 12){
        return true;
      }
    }


  return false;
  }

}