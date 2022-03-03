package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveArmTilt extends CommandBase{
    
    private double speed;

    public MoveArmTilt(double speed){
        this.speed = speed;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        if(RobotContainer.getTelescopicArm().getLeftTiltLimit() || RobotContainer.getTelescopicArm().getLeftTiltLimit()){
            RobotContainer.getTelescopicArm().stopTilt();
        } else {
            RobotContainer.getTelescopicArm().tiltArm(speed);
        }

        // depends on where limit switch is
        // if(RobotContainer.getTelescopicArm().getTiltEncoder() >= limit){
        //     RobotContainer.getTelescopicArm().stopTilt();
        // }    
    }

    @Override
    public boolean isFinished(){
        if(RobotContainer.getTelescopicArm().getLeftTiltLimit() || RobotContainer.getTelescopicArm().getLeftTiltLimit() || (!RobotContainer.getSecondJoy().getRawButton(Constants.ARM_IN_BUTTON) && !RobotContainer.getSecondJoy().getRawButton(Constants.ARM_DOWN_BUTTON))){
            return true;
        }

        // depends on where limit switch is
        // if(RobotContainer.getTelescopicArm().getTiltEncoder() >= limit || (!RobotContainer.getSecondJoy().getRawButton(Constants.ARM_IN_BUTTON) && !RobotContainer.getSecondJoy().getRawButton(Constants.ARM_DOWN_BUTTON))){
        //     return true;
        // }

        return false;
    }

    @Override
    public void end(boolean interrupted){
        //prat is a dummy dummy
        RobotContainer.getTelescopicArm().stopTilt();
    }
}
