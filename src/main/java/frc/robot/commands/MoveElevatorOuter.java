package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class MoveElevatorOuter extends CommandBase
{
    private double speed;
    private double encLimit1;
    private double encLimit2;
    private DigitalInput lmt1;
    private DigitalInput lmt2;
    private Encoder enc1;
    private Encoder enc2;
    private Climber elevator;

    public MoveElevatorOuter(double speed)
    {
        addRequirements(RobotContainer.getElevator());
        this.speed = speed;
    }
    @Override
    public void initialize() 
    {
        elevator = RobotContainer.getElevator();
        lmt1 = elevator.getElevatorLmtOuter1();
        lmt2 = elevator.getElevatorLmtOuter2();
        enc1 = elevator.getElevatorEncOuter1();
        enc2 = elevator.getElevatorEncOuter2();

        if (!lmt1.get() && !lmt2.get() && enc1.getDistance() <= encLimit1 && enc2.getDistance() <= encLimit2) {
            elevator.setOuter1(speed);
            elevator.setOuter2(speed);
        } else if (lmt1.get() || enc1.getDistance() > encLimit1) {
            elevator.stopOuter1();
        } else if (lmt2.get() || enc2.getDistance() > encLimit2) {
            elevator.stopOuter2();
        }
    }

    @Override
    public void execute()  
    {
        if (!lmt1.get() && !lmt2.get() && enc1.getDistance() <= encLimit1 && enc2.getDistance() <= encLimit2) {
            elevator.setOuter1(speed);
            elevator.setOuter2(speed);
        } else if (lmt1.get() || enc1.getDistance() > encLimit1) {
            elevator.stopOuter1();
        } else if (lmt2.get() || enc2.getDistance() > encLimit2) {
            elevator.stopOuter2();
        }
    }

    @Override
    public boolean isFinished() 
    {
        if (!RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_DOWN_BUTTON) && !RobotContainer.getJoy().getRawButton(Constants.ELEVATOR_UP_BUTTON))
        {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) 
    {
        elevator.stopOuter();
    }
}