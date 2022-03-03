package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveArm extends CommandBase{
    private double speed;


    public MoveArm(double speed){
        addRequirements(RobotContainer.getTelescopicArm());
        this.speed = speed;
    }

    @Override
    public void initialize(){
        //Code assumes -speed is down
        //assumes false from limit is not pressed
        if(speed < 0 && (!RobotContainer.getTelescopicArm().getLeftLimit() || !RobotContainer.getTelescopicArm().getRightLimit())){
            RobotContainer.getTelescopicArm().moveArm(speed);
        }
        

    }

    @Override 
    public void execute(){
        if(speed < 0 && (RobotContainer.getTelescopicArm().getLeftLimit() || RobotContainer.getTelescopicArm().getRightLimit())){
            RobotContainer.getTelescopicArm().stopExtend();
        }

    }

    @Override
    public boolean isFinished(){
        return RobotContainer.getTelescopicArm().getLeftLimit() || RobotContainer.getTelescopicArm().getRightLimit() || !RobotContainer.getJoy().getRawButton(Constants.ARM_DOWN_BUTTON);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTelescopicArm().stopExtend();
    }
}
