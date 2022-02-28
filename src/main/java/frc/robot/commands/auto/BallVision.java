package frc.robot.commands.auto;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class BallVision extends CommandBase {

    private DriveTrain driveTrain = RobotContainer.getDriveTrain();

    private double slowSpeed;
    private double fastSpeed;
    private double err;
    private double setSpeedLeft;
    private double setSpeedRight;

    private PhotonCamera cam;
    private PhotonTrackedTarget target;
    private boolean hasTarget;
    private double pitch;
    /**
     * Creates a new MoveStraight.
     */
  
    //bias based on distance model in case it is needed
    public BallVision(double slowSpeed, double fastSpeed)
    {
      addRequirements(RobotContainer.getDriveTrain());
      this.slowSpeed = slowSpeed;
      this.fastSpeed = fastSpeed;
    }

    @Override
    public void initialize() {
        cam = new PhotonCamera("photoncam2");
        System.out.println("Ball Tracking Started");
    }

    @Override
    public void execute() {

        if(cam.getLatestResult().hasTargets()){
            hasTarget = cam.getLatestResult().hasTargets();
            target = cam.getLatestResult().getBestTarget();
            err = target.getYaw();
            pitch = target.getPitch();
        }

        
        if(!hasTarget){
            driveTrain.moveRight(slowSpeed);
            driveTrain.moveRight(-slowSpeed);
        } else {
            if (err < -3) {
                setSpeedLeft = -slowSpeed;
                setSpeedRight = -fastSpeed;
            } if (err > 3) {
                setSpeedRight = -slowSpeed;
                setSpeedLeft = -fastSpeed;
            } else if (err <= 3 && err >= -3) {
                setSpeedRight = -fastSpeed;
                setSpeedLeft = -fastSpeed;
            }
            
            driveTrain.moveLeft(setSpeedLeft);
            driveTrain.moveRight(setSpeedRight);
        }

    }

    @Override
    public boolean isFinished() {
        if (pitch <= 0 && (err >= -3 || err <= 3) ) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Ball Track Finished");
        driveTrain.stop();
    }
    
}