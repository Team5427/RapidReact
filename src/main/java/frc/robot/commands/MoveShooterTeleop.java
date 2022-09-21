package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class MoveShooterTeleop extends CommandBase
{
    public static double setPointFinal;
    
    public MoveShooterTeleop()
    {
        addRequirements(RobotContainer.getShooter());
    }

    @Override
    public void initialize() {

        setPointFinal = SmartDashboard.getNumber("Change RPI", 4560);
        RobotContainer.getShooter().shooterInitRight();
    }

    @Override
    public void execute() {

        // RobotContainer.getShooter().moveShooter(5000);
        
        // RobotContainer.getShooter().movePercent((RobotContainer.getJoy().getRawAxis(3) + 1)/2);
        double dial = RobotContainer.getJoy().getRawAxis(3);
        dial = (dial * 3000) + 3000;
        SmartDashboard.putNumber("SETPOINT NEW", dial/60);

        RobotContainer.getShooter().moveShooterSydID(dial/60);
        
        SmartDashboard.putNumber("Shooter RPM SysID", RobotContainer.getShooter().getRightEnc().getVelocity());
        SmartDashboard.putNumber("Shooter RPM SysID NUM", RobotContainer.getShooter().getRightEnc().getVelocity());

        // System.out.println("Shooter is running " + RobotContainer.getJoy().getRawButton(1));
    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getJoy().getRawButton(Constants.SHOOT_BUTTON);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
    }
}