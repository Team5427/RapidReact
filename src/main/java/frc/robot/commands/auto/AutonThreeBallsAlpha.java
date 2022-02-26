package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutonThreeBallsAlpha extends SequentialCommandGroup{
    public AutonThreeBallsAlpha(){
        addCommands(new BallVision(0, 0.2, 0.3), new TargetVision(0, true), new Wait(1), new PointTurn(300, true, .15, 0.5, 20), new Wait(1), new BallVision(0, 0.3, 0.4), new TargetVision(0, true));
    }
}
