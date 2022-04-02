// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants;
// import frc.robot.RobotContainer;

// public class MoveTilt extends CommandBase {
//     private  double speed;
//     public MoveTilt(double speed) {
//         addRequirements(RobotContainer.getTilt());
//         this.speed = speed;

//     }
//     @Override
//     public void execute() {
//         RobotContainer.getTilt().setSpeed(speed);
//     }
//     @Override
//     public boolean isFinished() {
//         return !RobotContainer.getJoy().getRawButton(Constants.TILT_DOWN_BUTTON) && !RobotContainer.getJoy().getRawButton(Constants.TILT_UP_BUTTON);
        
//     }
//     @Override
//     public void end(boolean interupted) {
//           RobotContainer.getTilt().stop();

//     }


    
// }
