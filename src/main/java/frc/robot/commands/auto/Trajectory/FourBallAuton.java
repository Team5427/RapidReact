package frc.robot.commands.auto.Trajectory;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.auto.AutoShoot;
import frc.robot.commands.auto.ForwardTimer;
import frc.robot.commands.auto.IntakeStart;

public class FourBallAuton extends SequentialCommandGroup {
    public FourBallAuton() {
        PratsRamseteCommand balls1 = new RamseteClass(Robot.pathTraj1).getRamCom();
        PratsRamseteCommand balls2 = new RamseteClass(Robot.pathTraj2).getRamCom();
        addCommands(
            new ParallelCommandGroup(
                new SequentialCommandGroup(
                    balls1,
                    new AutoShoot(true),
                    //new point turn,
                    balls2,
                    new ForwardTimer(0.5, 0.8),
                    new AutoShoot(true)
                ),
                new IntakeStart(15, Constants.INTAKE_IN_SPEED, false)
            )
        );

    }
}
