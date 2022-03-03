package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;


public class TeleArmTilt extends CommandBase {
    private double speed;
    private double limit = 0;

    public TeleArmTilt(double speed) {
        addRequirements(RobotContainer.getTelescopicArm());
        this.speed = speed;
    }
    @Override
    public void initialize(){
        if(!RobotContainer.getTelescopicArm().getLeftTiltLimit() || !RobotContainer.getTelescopicArm().getRightTiltLimit()) {
            RobotContainer.getTelescopicArm().stopTilt();
        }

        // Depends on where limit switch is
        // if(RobotContainer.getTelescopicArm().getTiltEncoder() < limit){
        //     RobotContainer.getTelescopicArm().tilt_Arm(speed);
        // }
    }

    @Override 
    public void execute(){
        if(RobotContainer.getTelescopicArm().getLeftTiltLimit() || RobotContainer.getTelescopicArm().getRightTiltLimit()){
            RobotContainer.getTelescopicArm().stopTilt();
        } else {
            RobotContainer.getTelescopicArm().tiltArm(speed);
        }

        // Depends on where limit switch is
    }

    @Override
    public boolean isFinished(){
        return RobotContainer.getTelescopicArm().getLeftTiltLimit() || RobotContainer.getTelescopicArm().getRightTiltLimit() || RobotContainer.getJoy().getRawButton(Constants.ARM_TILT_IN_BUTTON);
        
        // Depends on where limit switch is
        // return RobotContainer.getTelescopicArm().getTiltEncoder() >= limit || RobotContainer.getJoy().getRawButton(Constants.ARM_TILT_IN_BUTTON);

    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTelescopicArm().stopTilt();
    }
}
