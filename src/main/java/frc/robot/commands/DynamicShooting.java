package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DynamicShooting extends CommandBase{
    private double pitch, yaw, dynamicSetPoint;
    private boolean hasTarget;
    private double A = Constants.DISTANCE_CURVE_A;
    private double B = Constants.DISTANCE_CURVE_B;
    private double C = Constants.DISTANCE_CURVE_C;

    public DynamicShooting(){
        addRequirements(RobotContainer.getShooter());
    }

    @Override
    public void execute(){
        hasTarget = (RobotContainer.getLimeLight().getEntry("tv").getDouble(0) == 0)?false:true;
        if (hasTarget) {
            pitch = RobotContainer.getLimeLight().getEntry("ty").getDouble(1000);
            yaw = RobotContainer.getLimeLight().getEntry("tx").getDouble(1000);

            if(pitch >= -7.5 || pitch < 12.54){
                SmartDashboard.putBoolean("CAN SHOOT???", false);
                dynamicSetPoint = 0;
            } else{
                SmartDashboard.putBoolean("CAN SHOOT???", true);
    
                dynamicSetPoint = (A * pitch * pitch) + (B * pitch) + C;
    
            }
            RobotContainer.getShooter().movePercent(1);
            // RobotContainer.getShooter().moveShooterSydID(dynamicSetPoint/60);
            SmartDashboard.putNumber("dynamic Setpoint", dynamicSetPoint);
        } else {
            pitch = 0;
            yaw = 0;
        }
        


        // SmartDashboard.putNumber("Shooter RPM SysID", RobotContainer.getShooter().getRightEnc().getVelocity());
        // SmartDashboard.putNumber("Shooter RPM SysID NUM", RobotContainer.getShooter().getRightEnc().getVelocity());
        // RobotContainer.getShooter().movePercent(.2);

    }

    @Override
    public boolean isFinished(){
        return !RobotContainer.getJoy().getRawButton(Constants.SHOOT_BUTTON);
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getShooter().stop();
    }
}
