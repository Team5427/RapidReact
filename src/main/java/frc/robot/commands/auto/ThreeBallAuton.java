package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ThreeBallAuton extends SequentialCommandGroup{
    public ThreeBallAuton() {
        addCommands(
            new AutoTiltDown(true), 
            new ParallelCommandGroup(
                new SequentialCommandGroup(
                    new Wait(.1), 
                    new IntakeStart(4, 1, false)), 
                new SequentialCommandGroup(
                    new ForwardTimer(2.1, -.17),
                    new Wait(1), 
                    new TargetVision(true), 
                    new ShooterTransport()
                )), 
            new AlsoPointTurn(215, 0.1, 0.25, 30),
            new Wait(0.2),
            new ParallelCommandGroup(
                new IntakeStart(4, 1, false),
                new ForwardTimer(4, -0.25)),
            new ForwardTimer(1, 0.3),
            new TargetVision(true),
            new ShooterTransport()
        );
    }
}
