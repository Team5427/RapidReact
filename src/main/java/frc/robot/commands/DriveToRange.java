package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveToRange extends CommandBase{
    private boolean hasTarget;
    private int counter;
    private boolean isCW;
    private PhotonCamera cam;
    private PhotonTrackedTarget target;
    private double fastSpeed = -.2;
    private double medSpeed = -.2;
    private double slowSpeed = -.1;
    private double Pnum = 0.6;

    private double tolerence = 5;

    private double pitch = -19;
    private double err;
    private double abserr = Math.abs(err);

    public DriveToRange(){
        addRequirements(RobotContainer.getDriveTrain());
    }

    @Override
    public void initialize()
    {
        System.out.println("Target Tracking Started");
        cam = new PhotonCamera("photoncam");
        hasTarget = false;
        counter = 0;
    }

    @Override
    public void execute()
    {

        if(cam.getLatestResult().hasTargets()){
            hasTarget = cam.getLatestResult().hasTargets();
            target = cam.getLatestResult().getBestTarget();
            err = target.getPitch() - pitch;
        }

        if(target.getPitch() >= 0){
            RobotContainer.getDriveTrain().moveLeft(fastSpeed);
            RobotContainer.getDriveTrain().moveRight(fastSpeed);
        } else if (target.getPitch() < 0 && err > 5) {
            RobotContainer.getDriveTrain().moveLeft(-.05);
            RobotContainer.getDriveTrain().moveRight(-.05);
        } else{
            RobotContainer.getDriveTrain().moveLeft(-.05);
            RobotContainer.getDriveTrain().moveRight(-.05);
        }
    }

    @Override
    public boolean isFinished(){

        if (Math.abs(err) < 0.5){
            // counter++;
            // if (counter >= 9) {
            //     return true;
            // }
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
