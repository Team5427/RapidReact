package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonThreeBallsAlpha extends SequentialCommandGroup{
    public AutonThreeBallsAlpha(){
        addCommands(new AutoTiltDown(2, 1), new BallVision(0.2, 0.3), new TargetVision(true), new Wait(1), new PointTurn(300, .15, 0.5, 20), new Wait(1), new BallVision(0.3, 0.4), new TargetVision(true));
    }
}
