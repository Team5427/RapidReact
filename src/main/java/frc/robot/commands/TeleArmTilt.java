package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;


public class TeleArmTilt extends CommandBase {
    private double speed;

    public TeleArmTilt(double speed) {
        addRequirements(RobotContainer.getTelescopicArm());
        this.speed = speed;
    }
    @Override
    public void initialize(){

    }

    @Override 
    public void execute(){
        if((RobotContainer.getTelescopicArm().getTiltLimit() && RobotContainer.getJoy().getRawButton(Constants.ARM_TILT_IN_BUTTON)) || (RobotContainer.getJoy().getRawButton(Constants.ARM_TILT_OUT_BUTTON) && RobotContainer.getTelescopicArm().getTiltEncoderLimit())){
            RobotContainer.getTelescopicArm().stopTilt();
        } else if((RobotContainer.getTelescopicArm().getTiltLimit() && RobotContainer.getJoy().getRawButton(Constants.ARM_TILT_IN_BUTTON_2)) || (RobotContainer.getJoy().getRawButton(Constants.ARM_TILT_OUT_BUTTON_2) && RobotContainer.getTelescopicArm().getTiltEncoderLimit())){
            RobotContainer.getTelescopicArm().stopTilt();
        }
         else {
            RobotContainer.getTelescopicArm().tiltArm(speed);
        }

        // Depends on where limit switch is
    }

    @Override
    public boolean isFinished(){
        return !RobotContainer.getSecondJoy().getRawButton(Constants.ARM_TILT_IN_BUTTON) && !RobotContainer.getSecondJoy().getRawButton(Constants.ARM_TILT_OUT_BUTTON) && !RobotContainer.getSecondJoy().getRawButton(Constants.ARM_TILT_IN_BUTTON_2) && !RobotContainer.getSecondJoy().getRawButton(Constants.ARM_TILT_OUT_BUTTON_2);
        
        // Depends on where limit switch is
        // return RobotContainer.getTelescopicArm().getTiltEncoder() >= limit || RobotContainer.getJoy().getRawButton(Constants.ARM_TILT_IN_BUTTON);

    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTelescopicArm().stopTilt();
    }
}
