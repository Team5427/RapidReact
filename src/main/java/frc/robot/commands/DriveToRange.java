package frc.robot.commands;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveToRange extends CommandBase{
    private boolean hasTarget;
    private int counter;
    private double fastSpeed = -.2;
    private double medSpeed = -.1;
    private double slowSpeed = -.5;

    private double pitch = -15.5;
    private double init_err;
    private double err;

    public DriveToRange(){
        addRequirements(RobotContainer.getDriveTrain());
    }

    @Override
    public void initialize()
    {
    }

    @Override
    public void execute()
    {   hasTarget = (RobotContainer.getLimeLight().getEntry("tv").getDouble(0) == 0)?false:true;
        if(hasTarget){
            init_err = RobotContainer.getLimeLight().getEntry("ty").getDouble(pitch) - pitch;
            err = Math.abs(init_err);
        }

        if (err >= 10) {
            RobotContainer.getDriveTrain().moveLeft(fastSpeed * Math.signum(init_err));
            RobotContainer.getDriveTrain().moveRight(fastSpeed * Math.signum(init_err));
        } else if (err >= 6) {
            RobotContainer.getDriveTrain().moveLeft(medSpeed * Math.signum(init_err));
            RobotContainer.getDriveTrain().moveRight(medSpeed * Math.signum(init_err));
        } else if (err >= 3) {
            RobotContainer.getDriveTrain().moveLeft(slowSpeed * Math.signum(init_err));
            RobotContainer.getDriveTrain().moveRight(slowSpeed * Math.signum(init_err));
        } else if (err >= 1) {
            RobotContainer.getDriveTrain().moveLeft((slowSpeed / 3) * Math.signum(init_err));
            RobotContainer.getDriveTrain().moveRight((slowSpeed / 3) * Math.signum(init_err));
        }
    }

    @Override
    public boolean isFinished(){

        if (err < 0.5){
            counter++;
            if (counter >= 7) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getDriveTrain().stop();
        RobotContainer.getDriveTrain().stop();
    }
}