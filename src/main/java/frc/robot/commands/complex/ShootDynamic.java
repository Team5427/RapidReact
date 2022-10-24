package frc.robot.commands.complex;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;

public class ShootDynamic extends CommandBase {
    private double pitch, yaw, dynamicSetPoint;
    private boolean hasTarget;
    private double A = Constants.DISTANCE_CURVE_A;
    private double B = Constants.DISTANCE_CURVE_B;
    private double C = Constants.DISTANCE_CURVE_C;
    private Timer timer = new Timer();
    private Timer timer2 = new Timer();
    private Limelight ll;
    
    private boolean isAuto;

    public ShootDynamic(boolean isAuto){
        addRequirements(RobotContainer.getShooter(), RobotContainer.getTransport());
        this.isAuto = isAuto;
        ll = RobotContainer.getLimeLight();
    }

    @Override
    public void initialize() {
        timer.reset();
        timer2.reset();
    }

    @Override
    public void execute(){
        hasTarget = ll.targetVisible();
        if (hasTarget) {
            pitch = ll.targetY();
            yaw = ll.targetX();
            dynamicSetPoint = (A * Math.pow(pitch, 2)) + (B * pitch) + C;
        } else {
            dynamicSetPoint = 4000; //revs up as soon as button pressed
        }

        RobotContainer.getShooter().moveShooterSydID(dynamicSetPoint/60);

        if(
            hasTarget //target on screen
            && (Math.abs(RobotContainer.getShooter().getRightEnc().getVelocity() - dynamicSetPoint) < 225) //shooter up to speed
            && Math.abs(yaw) < 2 //aimed at target
            && RobotContainer.getDriveTrain().getAvgAbsoluteWheelSpeeds() < 0.5 //needs to be tuned too //FIXME
        ){
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
