package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DynamicShooterTransport extends CommandBase{
    private double pitch, yaw, dynamicSetPoint;
    private boolean hasTarget;
    private double shootingConstant = Constants.COEFFICIENT_DYNAMIC;
    private double yint = Constants.Y_INT_DYNAMIC;
    

    //For auton
    private double startTime = -1;
    private double transportTime = 1;
    private boolean isAuto;

    public DynamicShooterTransport(boolean isAuto){
        addRequirements(RobotContainer.getShooter(), RobotContainer.getTransport());

        this.isAuto = isAuto;
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
        if(pitch >= 4.5 || pitch < -12){
            SmartDashboard.putBoolean("CAN SHOOT???", false);
            dynamicSetPoint = 0;
        } else{
            SmartDashboard.putBoolean("CAN SHOOT???", true);

            dynamicSetPoint = pitch * shootingConstant + yint;

        }

        RobotContainer.getShooter().moveShooterSydID(dynamicSetPoint/60);
        SmartDashboard.putNumber("dynamic Setpoint", dynamicSetPoint);
        SmartDashboard.putNumber("Shooter RPM SysID", RobotContainer.getShooter().getRightEnc().getVelocity());
        SmartDashboard.putNumber("Shooter RPM SysID NUM", RobotContainer.getShooter().getRightEnc().getVelocity());
        // RobotContainer.getShooter().movePercent(.2);

        if(Math.abs(RobotContainer.getShooter().getRightEnc().getVelocity() - dynamicSetPoint) < 50){
            RobotContainer.getTransport().move(Constants.TRANSPORT_SPEED);

            if(startTime == -1){
                startTime = Timer.getFPGATimestamp();
            }
        }

    }

    @Override
    public boolean isFinished(){
        if(isAuto && Timer.getFPGATimestamp() - startTime >= transportTime){
            return true;
        } else if(!isAuto && !RobotContainer.getJoy().getRawButton(Constants.SHOOT_BUTTON)){
            return true;
        }

        return false;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getShooter().stop();
        RobotContainer.getTransport().stop();
    }
}
