package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ArmAutoTiltOut extends CommandBase{
    private double limit = 0;
    private double speed;
    public ArmAutoTiltOut(double speed){
        addRequirements(RobotContainer.getTelescopicArm());
        this.speed = speed;
    }

    @Override
    public void initialize(){
        if(!RobotContainer.getTelescopicArm().getLeftTiltLimit() || !RobotContainer.getTelescopicArm().getLeftTiltLimit()){
            RobotContainer.getTelescopicArm().tiltArm(speed);
        }

        // depends on where limit switch is
        // if(RobotContainer.getTelescopicArm().getTiltEncoder() < limit){
        //     RobotContainer.getTelescopicArm().tiltArm(speed);
        // }
    }

    @Override
    public void execute(){
        if(RobotContainer.getTelescopicArm().getLeftTiltLimit() || RobotContainer.getTelescopicArm().getLeftTiltLimit()){
            RobotContainer.getTelescopicArm().stopTilt();
        }

        // depends on where limit switch is
        // if(RobotContainer.getTelescopicArm().getTiltEncoder() >= limit){
        //     RobotContainer.getTelescopicArm().stopTilt();
        // }
    }

    @Override
    public boolean isFinished(){
        if(RobotContainer.getTelescopicArm().getLeftTiltLimit() || RobotContainer.getTelescopicArm().getLeftTiltLimit()){
            return true;
        }

        // depends on where limit switch is
        // if(RobotContainer.getTelescopicArm().getTiltEncoder() >= limit){
        //     return true;
        // }

        return false;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTelescopicArm().stopTilt();
    }
}
