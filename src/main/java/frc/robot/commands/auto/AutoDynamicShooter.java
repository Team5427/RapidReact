package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutoDynamicShooter extends CommandBase{
    private double pitch, yaw, dynamicSetPoint;
    private boolean hasTarget;
    private double shootingConstant = Constants.COEFFICIENT_DYNAMIC;
    private double yint = Constants.Y_INT_DYNAMIC;

    public AutoDynamicShooter(){
        addRequirements(RobotContainer.getShooter());
    }

    @Override
    public void execute(){
        hasTarget = (RobotContainer.getLimeLight().getEntry("tv").getDouble(0) == 0)?false:true;
        if (hasTarget) {
            pitch = RobotContainer.getLimeLight().getEntry("ty").getDouble(1000);
            yaw = RobotContainer.getLimeLight().getEntry("tx").getDouble(1000);

            if(pitch >= 4.5 || pitch < -12){
                SmartDashboard.putBoolean("CAN SHOOT???", false);
                dynamicSetPoint = 5500;
            } else{
                SmartDashboard.putBoolean("CAN SHOOT???", true);
    
                dynamicSetPoint = pitch * shootingConstant + yint;
    
            }

            RobotContainer.getShooter().moveShooterSydID(dynamicSetPoint/60);

        } else {
            RobotContainer.getShooter().moveShooterSydID(5500);
        }
        

        SmartDashboard.putNumber("dynamic Setpoint", dynamicSetPoint);
        // SmartDashboard.putNumber("Shooter RPM SysID", RobotContainer.getShooter().getRightEnc().getVelocity());
        // SmartDashboard.putNumber("Shooter RPM SysID NUM", RobotContainer.getShooter().getRightEnc().getVelocity());
        // RobotContainer.getShooter().movePercent(.2);

    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getShooter().stop();
    }
}
