package frc.robot.commands.basic;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Transport;

public class TransportIdle extends CommandBase {
    private Transport transport;
    private Timer timer;
    public TransportIdle() {
        timer = new Timer();
        timer.reset();
        addRequirements(RobotContainer.getTransport());
        transport = RobotContainer.getTransport();
    }

    @Override
    public void execute() {
        double proxVal = transport.getProxVal();
        if (proxVal < Constants.COVERED) {
            timer.start();
            if (timer.get() > 0.75) {
                transport.move(.1);
            } else {
                transport.move(.3);
            }
        } else if (proxVal > Constants.BALL_TOO_FAR) { //tune this, most likely by raising it
            transport.move(-.2);
            timer.stop();
            timer.reset();
        } else {
            transport.stop();
            timer.stop();
            timer.reset();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
