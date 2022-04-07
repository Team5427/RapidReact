package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TwoBallAuton extends SequentialCommandGroup{
    public TwoBallAuton() {
        addCommands(
            new AutoTiltDown(true), 
            new ParallelCommandGroup(
                new SequentialCommandGroup(
                    new Wait(.1), 
                    new IntakeStart(4, .20, false)), 
                new SequentialCommandGroup(
                    new ForwardTimer(2.1, -.17),
                    new Wait(1), 
                    new PointTurn(160, 0.1, 0.2, 40), 
                    new TargetVision(true), 
                    new ShooterTransport()
                )
            )
        );

    }

}
