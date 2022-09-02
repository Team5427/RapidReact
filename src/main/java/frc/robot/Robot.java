/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.DynamicShooting;
// import frc.robot.commands.auto.ArmAutoTiltOut;
// import frc.robot.commands.MoveArm;
import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.MoveTransport;
// import frc.robot.commands.auto.AutoArmExtend;


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
    cam = CameraServer.startAutomaticCapture();
    cam.setFPS(15);
    m_robotContainer = new RobotContainer();
    RobotContainer.getShooter().shooterInitRight();
    RobotContainer.getDriveTrain().getLeftEnc().setPosition(0);
    
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

    // double 
    
    // SmartDashboard.putNumber("x/Shooter RPM", RobotContainer.getShooter().getRightEnc().getVelocity());
    // // SmartDashboard.putNumber("dt speed", RobotContainer.getDriveTrain().get)
    // // 

    // SmartDashboard.putNumber("Drivetrain encoder", RobotContainer.getDriveTrain().getLeftEnc().getPosition());
    // // SmartDashboard.putBoolean("Elevator limit switch", RobotContainer.getElevator().getElevatorLimit());
    // // SmartDashboard.putNumber("Elevator Encoder", RobotContainer.getElevator().getDistance());
    // // SmartDashboard.putNumber("Arm Left Encoder", RobotContainer.getTelescopicArm().getLeftEncoder());
    // // SmartDashboard.putNumber("Arm Right Encoder", RobotContainer.getTelescopicArm().getRightEncoder());
    // // SmartDashboard.putNumber("Proximity", RobotContainer.getTransport().getProxVal());
    SmartDashboard.putNumber("NavX", RobotContainer.getAHRS().getRotation2d().getDegrees());
    // SmartDashboard.putNumber("x/Slider thing", ((1 + RobotContainer.getJoy().getRawAxis(3)) / 2));
    // // SmartDashboard.putNumber("Shooter Voltage", RobotContainer.getShooter().shooterMotorRight.get());
    // SmartDashboard.putBoolean("x/LimeLight Working?", RobotContainer.getLimeLight().getEntry("tv").getDouble(3) != 3);
    // SmartDashboard.putNumber("x/Pitch", RobotContainer.getLimeLight().getEntry("ty").getDouble(10000));
    // SmartDashboard.putNumber("x/Shooter Percentage", RobotContainer.getShooter().getShooterMotorLeft().get());

    // if(RobotContainer.getLimeLight().getEntry("ty").getDouble(1000) >= 7 || RobotContainer.getLimeLight().getEntry("ty").getDouble(1000) < -17){
    //   SmartDashboard.putBoolean("x/CAN SHOOT?", false);
    // } else{
    //   SmartDashboard.putBoolean("x/CAN SHOOT?", true);
    // }


    CommandScheduler.getInstance().run();
    wantedSetPoint = ((RobotContainer.getJoy().getRawAxis(3) * 3000) + 3000);
    
    // dynamicSetPoint = distToRPM(distFromGoal);
    //We will see which is better ^^^ prolly distance but wtv smh smh :|
    SmartDashboard.putNumber("SetPoint", wantedSetPoint);
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