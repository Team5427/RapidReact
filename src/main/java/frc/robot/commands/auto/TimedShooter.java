package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TimedShooter extends CommandBase{
    private double time;
    private double setPoint;
    private Timer timer = new Timer();

    private double pitch, yaw, dynamicSetPoint;
    private boolean hasTarget;
    private double shootingConstant = Constants.COEFFICIENT_DYNAMIC;
    private double yint = Constants.Y_INT_DYNAMIC;

    public TimedShooter(double time)
    {
        addRequirements(RobotContainer.getShooter());
        this.time = time;
    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();
    }

    @Override
    public void execute()
    {
        
        hasTarget = (RobotContainer.getLimeLight().getEntry("tv").getDouble(0) == 0)?false:true;
        if (hasTarget) {
            pitch = RobotContainer.getLimeLight().getEntry("ty").getDouble(1000);
            yaw = RobotContainer.getLimeLight().getEntry("tx").getDouble(1000);
        } else {
            pitch = 0;
            yaw = 0;
        }
        if(pitch >= 7 || pitch < -17){
            dynamicSetPoint = 0;
        } else{
            dynamicSetPoint = pitch * shootingConstant + yint;
            dynamicSetPoint /= 100;

        }

        RobotContainer.getShooter().movePercent(dynamicSetPoint);    }

    @Override 
    public boolean isFinished(){
        return timer.get() >= time;
    }
    
    @Override
    public void end(boolean interrupted){
        RobotContainer.getShooter().stop();
        timer.stop();
        timer.reset();
    }

}
