package frc.robot.commands.basic;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class MoveShooterTeleop extends CommandBase {

    public MoveShooterTeleop() {
        addRequirements(RobotContainer.getShooter());
    }

    @Override
    public void initialize() {
        RobotContainer.getShooter().shooterInitRight();
    }

    @Override
    public void execute() {
        double dial = RobotContainer.getJoy().getRawAxis(3);
        dial = (dial * 3000) + 3000;
        SmartDashboard.putNumber("DIAL", dial / 60);
        if (RobotContainer.getJoy().getRawButton(6)) {
            RobotContainer.getShooter().moveShooterSydID(dial / 60);
        } else {
            RobotContainer.getShooter().moveShooterSydID(1500 / 60);
        }

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getShooter().stop();
    }
}