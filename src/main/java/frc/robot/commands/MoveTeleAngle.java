package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class MoveTeleAngle extends CommandBase
{
    private double speed;
    private double encLimit1;
    private double encLimit2;
    private DigitalInput lmt1;
    private DigitalInput lmt2;
    private Encoder enc1;
    private Encoder enc2;
    private Climber climber;

    public MoveTeleAngle(double speed)
    {
        addRequirements(RobotContainer.getClimber());
        this.speed = speed;
    }
    @Override
    public void initialize() 
    {
        climber = RobotContainer.getClimber();
        lmt1 = climber.getClimberLmtTele1();
        lmt2 = climber.getClimberLmtTele2();
        enc1 = climber.getTeleEnc1();
        enc2 = climber.getTeleEnc2();
        encLimit1 = Constants.ELEVATOR_TELE_UP_LMT_1;
        encLimit2 = Constants.ELEVATOR_TELE_UP_LMT_2;

        if (!lmt1.get() && !lmt2.get() && enc1.getDistance() <= encLimit1 && enc2.getDistance() <= encLimit2) {
            climber.setTele1(speed);
            climber.setTele2(speed);
        } else if (lmt1.get() || enc1.getDistance() > encLimit1) {
            climber.stopTele1();
        } else if (lmt2.get() || enc2.getDistance() > encLimit2) {
            climber.stopTele2();
        }
    }

    @Override
    public void execute()  
    {
        if (!lmt1.get() && !lmt2.get() && enc1.getDistance() <= encLimit1 && enc2.getDistance() <= encLimit2) {
            climber.setTele1(speed);
            climber.setTele2(speed);
        } else if (lmt1.get() || enc1.getDistance() > encLimit1) {
            climber.stopTele1();
        } else if (lmt2.get() || enc2.getDistance() > encLimit2) {
            climber.stopTele2();
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
        climber.stopTele();
    }
}