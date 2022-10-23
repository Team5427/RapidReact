package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.complex.AutoShoot;

public class TwoBallAuton extends SequentialCommandGroup{
    public TwoBallAuton() {
        addCommands(
            new AutoTiltDown(), 
            new ParallelCommandGroup(
                new SequentialCommandGroup(
                    new Wait(.1), 
                    new IntakeStart(4, 1, false)), 
                new SequentialCommandGroup(
                    new ForwardTimer(1, -.4),
                    new Wait(0.3),
                    new AutoShoot(true)
                )
            )
        );

    }

}
