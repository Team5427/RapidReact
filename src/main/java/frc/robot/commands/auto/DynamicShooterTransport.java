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
    private double A = Constants.DISTANCE_CURVE_A;
    private double B = Constants.DISTANCE_CURVE_B;
    private double C = Constants.DISTANCE_CURVE_C;
    private Timer timer = new Timer();
    private Timer timer2 = new Timer();
    

    //For auton
    private boolean inRange;
    private boolean isAuto;

    public DynamicShooterTransport(boolean isAuto){
        addRequirements(RobotContainer.getShooter(), RobotContainer.getTransport());

        this.isAuto = isAuto;
        inRange = true;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer2.reset();
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
        if(
            (pitch >= 7.5 || pitch < -12.54) && 
            hasTarget){
            SmartDashboard.putBoolean("CAN SHOOT???", false);
            dynamicSetPoint = 0;
            inRange = false;
        } else if (hasTarget) {
            inRange = true;
            SmartDashboard.putBoolean("CAN SHOOT???", true);


        }
        
        dynamicSetPoint = (A * pitch * pitch) + (B * pitch) + C;

        RobotContainer.getShooter().moveShooterSydID(dynamicSetPoint/60);
        SmartDashboard.putNumber("dynamic Setpoint", dynamicSetPoint);

        if(hasTarget && (Math.abs(RobotContainer.getShooter().getRightEnc().getVelocity() - dynamicSetPoint) < 100)){
            RobotContainer.getTransport().move(Constants.TRANSPORT_SPEED);
            timer2.start();
        }

    }

    @Override
    public boolean isFinished(){
        if(isAuto && (timer2.get() > 2)){
            return true;
        } else if(!isAuto && !RobotContainer.getJoy().getRawButton(1)){
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
