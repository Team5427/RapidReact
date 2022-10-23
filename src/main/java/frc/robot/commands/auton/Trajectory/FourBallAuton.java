package frc.robot.commands.auton.Trajectory;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.auton.AutoTiltDown;
import frc.robot.commands.auton.ForwardTimer;
import frc.robot.commands.auton.IntakeStart;
import frc.robot.commands.complex.AutoShoot;

public class FourBallAuton extends SequentialCommandGroup {
    public FourBallAuton() {
        RamseteClass balls1 = new RamseteClass(Robot.pathTraj1);
        
        RamseteClass balls2 = new RamseteClass(Robot.pathTraj2);
        addCommands(
            new AutoTiltDown(),
            new ParallelCommandGroup(
                new SequentialCommandGroup(
                    balls1.getRamCom(),
                    new AutoShoot(true),
                    //new point turn,
                    // balls2,
                    new ForwardTimer(0.5, 0.8),
                    new AutoShoot(true)
                ),
                new IntakeStart(15, Constants.INTAKE_IN_SPEED, false)
            )
        );

    }
}
