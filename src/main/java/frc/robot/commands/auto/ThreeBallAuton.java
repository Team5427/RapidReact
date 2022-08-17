package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ThreeBallAuton extends ParallelCommandGroup{
    public ThreeBallAuton() {
        addCommands(
            new SequentialCommandGroup(
                new AutoTiltDown(true), 
                new ParallelCommandGroup(
                    new SequentialCommandGroup(
                        new Wait(.1), 
                        new IntakeStart(4, 1, false)), 
                    new SequentialCommandGroup(
                        new ForwardTimer(1, -.5),
                        new Wait(.1),
                        new TargetVision(true),
                        new AutoTransport()
                    )
                ),
                new StablePointTurn(218, 0.05, 0.20, 25),
                new ParallelCommandGroup(
                    new IntakeStart(4, 1, false),
                    new ForwardTimer(.35, -0.6)),
                new ForwardTimer(0.7, 0.8),
                new Wait(.1),
                new StablePointTurn(181, 0.05, 0.3, 30),
                new AutoShoot(true)
            ),
            new AutoDynamicShooter()
        
        );
    }
}
