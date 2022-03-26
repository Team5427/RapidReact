package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveToRange;
import frc.robot.commands.ShooterTransport;

public class CoolAuto extends SequentialCommandGroup{
    public CoolAuto() {
        addCommands(new AutoTiltDown(2, 1), new ParallelCommandGroup(new IntakeStart(3, .55, false), new SequentialCommandGroup( new ForwardTimer(1.5, -.2),new Wait(1), new PointTurn(160, 0.1, 0.2, 40), new TargetVision(true))), new DriveToRange(), new TargetVision(true), new ShooterTransport());

    }

}
