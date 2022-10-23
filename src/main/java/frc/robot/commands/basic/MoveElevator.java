package frc.robot.commands.basic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class MoveElevator extends CommandBase {

    private double speed;

    public MoveElevator(double speed) {
        addRequirements(RobotContainer.getElevator());
        this.speed = speed;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

        RobotContainer.getElevator().move(speed);

    }

    @Override
    public boolean isFinished() {
        return !RobotContainer.getSecondJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON_2)
                && !RobotContainer.getSecondJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON_2)
                && !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON)
                && !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON);
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.getElevator().stop();
    }
}
