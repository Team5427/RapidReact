package frc.robot.commands.auto;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveForwardPID extends CommandBase{
    public double distance;
    public double startingAngle;
    public double tolerance, correction;
    public double startPosition;

    public void MoveForwardPID(double distance){
        addRequirements(RobotContainer.getDriveTrain());
        this.distance = distance;
    }

    @Override
    public void initialize(){
        startingAngle = RobotContainer.getAHRS().getYaw();
        tolerance = 5;
        correction = .05;
        startPosition = RobotContainer.getDriveTrain().getDistance(); // distance in meters
    }

    @Override
    public void execute(){
        if(RobotContainer.getAHRS().getYaw() > startingAngle + tolerance){
            RobotContainer.getDriveTrain().setLeft(Constants.AUTONOMOUS_DRIVE_SPEED + correction);
            RobotContainer.getDriveTrain().setRight(Constants.AUTONOMOUS_DRIVE_SPEED);
        }else if(RobotContainer.getAHRS().getYaw() > startingAngle + tolerance){
            RobotContainer.getDriveTrain().setLeft(Constants.AUTONOMOUS_DRIVE_SPEED);
            RobotContainer.getDriveTrain().setRight(Constants.AUTONOMOUS_DRIVE_SPEED + correction);
        }else{
            RobotContainer.getDriveTrain().setLeft(Constants.AUTONOMOUS_DRIVE_SPEED);
            RobotContainer.getDriveTrain().setRight(Constants.AUTONOMOUS_DRIVE_SPEED);
        }
    }

    @Override
    public boolean isFinished(){
        return RobotContainer.getDriveTrain().getDistance() - startPosition >= distance;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getDriveTrain().stop();
    }


   
}
