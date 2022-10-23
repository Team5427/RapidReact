package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class IntakeStart extends CommandBase {
    private Timer timer = new Timer();
    private double ctimer;
    private double speed;
    private boolean isIndef;
    
    public IntakeStart(double ctimer, double speed, boolean isIndef) {
        addRequirements(RobotContainer.getIntake());
        this.ctimer = ctimer;
        this.speed = speed;
        this.isIndef = isIndef;

    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        RobotContainer.getIntake().moveIntake(speed);
    }
    @Override
    public boolean isFinished() {
        if (isIndef) {
            return false;
        } else if (!isIndef) {
            return (timer.get() >= ctimer);
        } else {
            return false;
        }

        
    }
    @Override
    public void end(boolean interupted) {
        timer.reset();
        RobotContainer.getIntake().stopIntake();

    }


    
}
