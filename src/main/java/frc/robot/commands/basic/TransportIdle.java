package frc.robot.commands.basic;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Transport;

public class TransportIdle extends CommandBase {
    private Transport transport;
    public TransportIdle() {
        addRequirements(RobotContainer.getTransport());
        transport = RobotContainer.getTransport();
    }

    @Override
    public void execute() {
        double proxVal = transport.getProxVal();
        if (proxVal < Constants.COVERED) {
            transport.move(.4);
        } else if (proxVal > Constants.BALL_TOO_FAR) { //tune this, most likely by raising it
            transport.move(-.1);
        } else {
            transport.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
