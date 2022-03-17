package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveToRange extends CommandBase{
    public DriveToRange(){
        addRequirements(RobotContainer.getDriveTrain(), RobotContainer.getLidar());
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(RobotContainer.getLidar().getDistance() < Constants.LIDAR_MIN){
            RobotContainer.getDriveTrain().moveLeft(-.5);
            RobotContainer.getDriveTrain().moveRight(-.5);
        } else if(RobotContainer.getLidar().getDistance() > Constants.LIDAR_MAX){
            RobotContainer.getDriveTrain().moveLeft(.5);
            RobotContainer.getDriveTrain().moveRight(.5);
        }
    }

    @Override
    public boolean isFinished(){
        if(RobotContainer.getLidar().getDistance() < Constants.LIDAR_MIN){
            return true;
        } else if(RobotContainer.getLidar().getDistance() > Constants.LIDAR_MAX){
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
