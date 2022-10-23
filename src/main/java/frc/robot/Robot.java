/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import java.io.IOException;
import java.nio.file.Path;

import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.basic.MoveShooterTeleop;
import frc.robot.commands.basic.MoveTransport;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private Command m_autonomousCommand;

  public static RobotContainer m_robotContainer;
  public static double wantedSetPoint;
  public static double dynamicSetPoint;

  public static double pitch;
  public static double yaw;
  public static boolean hasTarget;
  private static double limelightMountAngleDegrees = Constants.LL_MOUNT_ANGLE_DEG;
  private static double limelightLensHeightInches = Constants.LL_LENS_HEIGHT_INCHES;
  private static double goalHeightInches = Constants.GOAL_HEIGHT_INCHES;
  private static double angleToGoalDegrees = limelightMountAngleDegrees + pitch;
  private static double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);
  public static double distFromGoal = (goalHeightInches - limelightLensHeightInches)/Math.tan(angleToGoalRadians);

  private static UsbCamera cam;

  public static Trajectory pathTraj1;
  public static Trajectory pathTraj2;
  String trajectoryJSON1 = "PathOutput/output/huh.wpilib.json";
  Trajectory trajectory1 = new Trajectory();
  String trajectoryJSON2 = "PathOutput/output/yes.wpilib.json";
  Trajectory trajectory2 = new Trajectory();


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() 
  {
    // PRAT STOP DELETING COMMENTS!!!!
    // THEY EXIST FOR A REASON
    // SmartDashboard.putNumber("Change RPM", 4560);
    // SmartDashboard.putData("Auto Tilt Arm Out", new ArmAutoTiltOut(Constants.ARM_TILT_SPEED));
    // SmartDashboard.putData("Teleop Tilt Arm Out", new MoveArm(Constants.ARM_TILT_SPEED));
    // SmartDashboard.putData("Teleop Tilt Arm in", new ArmAutoTiltOut(Constants.ARM_TILT_SPEED));
    // SmartDashboard.putData("Auto Extend Arm", new AutoArmExtend(Constants.ARM_SPEED));
    // SmartDashboard.putData("Manual Retract Arm", new AutoArmExtend(-Constants.ARM_SPEED));
    // SmartDashboard.putData("Manual Transport", new MoveTransport(Constants.TRANSPORT_SPEED));

    try{
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON1);
      trajectory1 = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
      pathTraj1 = trajectory1;
    }catch(IOException ex){
      DriverStation.reportError("Unable to open trajectory" + trajectoryJSON1, ex.getStackTrace());
    }
    try{
      Path trajectoryPath2 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON2);
      trajectory2 = TrajectoryUtil.fromPathweaverJson(trajectoryPath2);
      pathTraj2 = trajectory2;
    }catch(IOException ex){
      DriverStation.reportError("Unable to open trajectory" + trajectoryJSON2, ex.getStackTrace());
    }

    cam = CameraServer.startAutomaticCapture();
    cam.setFPS(15);
    m_robotContainer = new RobotContainer();
    RobotContainer.getShooter().shooterInitRight();
    RobotContainer.getDriveTrain().getLeftEnc().setPosition(0);
    RobotContainer.getDriveTrain().getRightEnc().setPosition(0);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {

    SmartDashboard.putNumber("NavX", RobotContainer.getAHRS().getRotation2d().getDegrees());
    SmartDashboard.putNumber("x/Slider thing", ((1 + RobotContainer.getJoy().getRawAxis(3)) / 2));

    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() 
  {
  }

  @Override
  public void disabledPeriodic() {
    
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() 
  {
    RobotContainer.getDriveTrain().getLeftEnc().setPosition(0);
    RobotContainer.getDriveTrain().getRightEnc().setPosition(0);


    m_autonomousCommand = RobotContainer.getAutonomousCommand();

    if(m_autonomousCommand != null)
    {
      m_autonomousCommand.schedule();
    }
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // RobotContainer.shooterMotorLeft.setInverted(false);

    
    // RobotContainer.shooterMotorRight.setInverted(true);

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

}