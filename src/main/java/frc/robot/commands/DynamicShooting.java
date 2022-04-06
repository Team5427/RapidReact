package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DynamicShooting extends CommandBase{
    private double pitch, yaw, dynamicSetPoint;
    private boolean hasTarget;
    private double shootingConstant = -1.51;
    private double yint = 75.46;

    public DynamicShooting(){
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
        if(pitch >= 7 || pitch < -17){
            SmartDashboard.putBoolean("CAN SHOOT???", false);
            dynamicSetPoint = 0;
        } else{
            SmartDashboard.putBoolean("CAN SHOOT???", true);

            dynamicSetPoint = pitch * shootingConstant + yint;
            dynamicSetPoint /= 100;

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
