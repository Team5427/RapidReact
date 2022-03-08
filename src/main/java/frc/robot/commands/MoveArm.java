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
        if(speed > 0 && (RobotContainer.getTelescopicArm().getRightEncoder() < Constants.ARM_RIGHT_ENCODER_LIMIT) || RobotContainer.getTelescopicArm().getLeftEncoder() < Constants.ARM_LEFT_ENCODER_LIMIT) {
            RobotContainer.getTelescopicArm().moveArm(speed);
        }
        

    }

    @Override 
    public void execute(){
        if(speed > 0 && !(RobotContainer.getTelescopicArm().getRightEncoder() < Constants.ARM_RIGHT_ENCODER_LIMIT) || RobotContainer.getTelescopicArm().getLeftEncoder() < Constants.ARM_LEFT_ENCODER_LIMIT) {
            RobotContainer.getTelescopicArm().stopExtend();
        }

    }

    @Override
    public boolean isFinished(){
        return !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON) && !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTelescopicArm().stopExtend();
    }
}