package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutoTransport extends CommandBase{
    private double pitch, yaw, dynamicSetPoint;
    private boolean hasTarget;
    private double shootingConstant = Constants.COEFFICIENT_DYNAMIC;
    private double yint = Constants.Y_INT_DYNAMIC;
    private Timer timer = new Timer();
    private Timer timer2 = new Timer();
    

    //For auton
    private boolean inRange;
    private boolean isAuto;

    public AutoTransport(){
        addRequirements(RobotContainer.getTransport());

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
            dynamicSetPoint = 5590;
        }

        if(pitch >= 7 || pitch < -17){
            dynamicSetPoint = 0;
            inRange = false;
        } else{
            dynamicSetPoint = pitch * shootingConstant + yint;
            inRange = true;

        }

        // SmartDashboard.putBoolean("Has Target", hasTarget);
        // SmartDashboard.putBoolean("Velocity", (Math.abs(RobotContainer.getShooter().getRightEnc().getVelocity() - dynamicSetPoint) < 500));
        // SmartDashboard.putBoolean("In Range", inRange);
        // SmartDashboard.putBoolean("Target", (Math.abs(yaw) < 2));

        if(hasTarget && (Math.abs(RobotContainer.getShooter().getRightEnc().getVelocity() - dynamicSetPoint) < 500) && inRange && (Math.abs(yaw) < 2)){

            RobotContainer.getTransport().move(Constants.TRANSPORT_SPEED);
            timer2.start();

    }

    }

    @Override
    public boolean isFinished(){
        if((timer2.get() > 1.75)){
            // SmartDashboard.putBoolean("Running Transport", false);
            return true;
        }
        // SmartDashboard.putBoolean("Running Transport", true);

        return false;
    }

    @Override
    public void end(boolean interrupted){
        RobotContainer.getTransport().stop();
    }
}
