package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DynamicShooting2 extends CommandBase{
    private double pitch, yaw, dynamicSetPoint;
    private boolean hasTarget;
    private double shootingConstant = 0.034;
    private double shootingContstantB = -1.055;
    private double yint = 74.14;

    public DynamicShooting2(){
        addRequirements(RobotContainer.getShooter());
    }

    @Override
    public void execute(){
        hasTarget = (RobotContainer.getLimeLight().getEntry("tv").getDouble(0) == 0)?false:true;
        if (hasTarget) {
            pitch = RobotContainer.getLimeLight().getEntry("ty").getDouble(1000);
            yaw = RobotContainer.getLimeLight().getEntry("tx").getDouble(1000);
        } else {
            pitch = 0;
            yaw = 0;
        }
        if(pitch > 4 || pitch < -17){
            dynamicSetPoint = 0;
        } else{
            dynamicSetPoint = pitch * pitch * shootingConstant + pitch * shootingContstantB + yint;

        }

        RobotContainer.getShooter().movePercent(dynamicSetPoint);
    }

    @Override
    public boolean isFinished(){
        return !RobotContainer.getJoy().getRawButton(Constants.MANUAL_SHOOT_BUTTON);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getShooter().stop();
    }
}
