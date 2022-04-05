package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveToRange;
import frc.robot.commands.ShooterTransport;

public class CoolAuto extends SequentialCommandGroup{
    public CoolAuto() {
        addCommands( new ParallelCommandGroup(
            new SequentialCommandGroup(
                new Wait(.4) , new ParallelCommandGroup(
                     new AutoTiltDown(true), new SequentialCommandGroup(
                         new Wait(.3), new IntakeStart(4, .20, false)))), new SequentialCommandGroup(
                             new Wait(0), new ForwardTimer(2.1, -.17),new Wait(1), new PointTurn(160, 0.1, 0.2, 40), new TargetVision(true)))
                    , new DriveToRange(), new TargetVision(true), new ShooterTransport());

    }

}
