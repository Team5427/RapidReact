package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveToRange extends CommandBase{
    private boolean hasTarget;
    private double err;
    private int counter;
    private boolean isCW;
    private PhotonCamera cam;
    private PhotonTrackedTarget target;
    private double fastSpeed = .3;
    private double medSpeed = .2;
    private double slowSpeed = .1;

    private double tolerence = 5;

    private double pitch;

    public DriveToRange(){
        addRequirements(RobotContainer.getDriveTrain());
    }

    @Override
    public void initialize()
    {
        System.out.println("Target Tracking Started");
        cam = new PhotonCamera("photoncam");
        hasTarget = false;
    }

    @Override
    public void execute()
    {

        if(cam.getLatestResult().hasTargets()){
            hasTarget = cam.getLatestResult().hasTargets();
            target = cam.getLatestResult().getBestTarget();

        }

        // move back
        if(target.getPitch() - pitch > tolerence){
            RobotContainer.getDriveTrain().moveLeft(fastSpeed);
            RobotContainer.getDriveTrain().moveRight(fastSpeed);
        } else if(target.getPitch() - pitch > tolerence / 2){
            RobotContainer.getDriveTrain().moveLeft(medSpeed);
            RobotContainer.getDriveTrain().moveRight(medSpeed);
        } else if(target.getPitch() - pitch > tolerence / 3){
            RobotContainer.getDriveTrain().moveLeft(slowSpeed);
            RobotContainer.getDriveTrain().moveRight(slowSpeed);
        }
        // move forward
        if(target.getPitch() - pitch < -tolerence){
            RobotContainer.getDriveTrain().moveLeft(-fastSpeed);
            RobotContainer.getDriveTrain().moveRight(-fastSpeed);
        } else if(target.getPitch() - pitch < -tolerence / 2){
            RobotContainer.getDriveTrain().moveLeft(-medSpeed);
            RobotContainer.getDriveTrain().moveRight(-medSpeed);
        } else if(target.getPitch() - pitch < -tolerence / 3){
            RobotContainer.getDriveTrain().moveLeft(-slowSpeed);
            RobotContainer.getDriveTrain().moveRight(-slowSpeed);
        }
    }

    @Override
    public boolean isFinished(){
        
        if(Math.abs(target.getPitch() - pitch) < tolerence / 4){
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getDriveTrain().stop();
        RobotContainer.getDriveTrain().stop();
    }
}
