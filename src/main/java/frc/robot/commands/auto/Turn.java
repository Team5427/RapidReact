package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Turn extends CommandBase{
    private double angle;
    private double tolerance;
    public Turn(double angle){
        addRequirements(RobotContainer.getDriveTrain());
        this.angle = angle;
        tolerance = 5;
    }

    @Override
    public void execute(){
        if(RobotContainer.getAHRS().getYaw() > tolerance){
            RobotContainer.getDriveTrain().setLeft(-.5);
            RobotContainer.getDriveTrain().setRight(.5);
        }
        if(RobotContainer.getAHRS().getYaw() < -tolerance){
            RobotContainer.getDriveTrain().setLeft(.5);
            RobotContainer.getDriveTrain().setRight(-.5);
        }
    }

    @Override
    public boolean isFinished(){
        return RobotContainer.getAHRS().getYaw() < tolerance && RobotContainer.getAHRS().getYaw() > -tolerance;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getDriveTrain().stop();
    }
    
}
