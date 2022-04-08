package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ThreeBallAuton extends SequentialCommandGroup{
    public ThreeBallAuton() {
        addCommands(
            new AutoTiltDown(true), 
            new ParallelCommandGroup(
                new SequentialCommandGroup(
                    new Wait(.1), 
                    new IntakeStart(4, 1, false)), 
                new SequentialCommandGroup(
                    new ForwardTimer(1.4, -.25),
                    new BumpTransport(.2, -Constants.TRANSPORT_SPEED),
                    new Wait(.8), 
                    new TargetVision(true), 
                    new ShooterTransport()
                )), 
            new AlsoPointTurn(205, 0.1, 0.2, 50),
            new Wait(0.2),
            new ParallelCommandGroup(
                new IntakeStart(4, 1, false),
                new SequentialCommandGroup(
                    new ForwardTimer(1.1, -0.3),
                    new Wait(1)
                )
            ),
            new ForwardTimer(1, 0.3),
            new Wait(1),
            new TargetVision(true),
            new ShooterTransport()
        );
    }
}
