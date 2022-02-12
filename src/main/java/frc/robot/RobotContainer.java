// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.DigitalGlitchFilterJNI;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.MoveShooterTeleop;
import frc.robot.commands.MoveTilt;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tilt;
import frc.robot.subsystems.Transport;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static Joystick joy;
  private static Button shooterTeleop;
  private static Button tiltUp;
  private static Button tiltDown;
  private static Button transport_move;

  //motors 
  public static CANSparkMax shooterMotorRight;
  public static CANSparkMax shooterMotorLeft;
  public static CANSparkMax topRight, topLeft, bottomRight, bottomLeft;
  public static MotorController tiltMotor;
  public static MotorController transportMotor;

  //sensors
  private static RelativeEncoder shooterRightEnc;
  private static RelativeEncoder shooterLeftEnc;
  private static DigitalInput tilt_limit;
  private static AnalogInput transport_sensor;


  //subsystems
  private static Shooter shooter;
  private static Tilt tilt;
  private static Transport transport;

  private static SparkMaxPIDController pidcontrol_shooter_Right;
  private static SparkMaxPIDController pidcontrol_shooter_Left;

  public static PowerDistribution pdp;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
  shooterMotorRight = new CANSparkMax(Constants.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);
  shooterMotorLeft = new CANSparkMax(Constants.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);

  topLeft = new CANSparkMax(Constants.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);
  topRight = new CANSparkMax(Constants.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);
  bottomLeft = new CANSparkMax(Constants.SHOOTER_MOTOR_RIGHT, MotorType.kBrushless);
  bottomRight = new CANSparkMax(Constants.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);
  tiltMotor = new WPI_VictorSPX(Constants.TILT_MOTOR);
  tilt_limit = new DigitalInput(Constants.TILT_SWITCH);
  transportMotor = new WPI_VictorSPX(Constants.TRANSPORT_MOTOR);
  transport_sensor = new AnalogInput(Constants.TRANSPORT_SENSOR);

  pidcontrol_shooter_Right = shooterMotorRight.getPIDController();
  pidcontrol_shooter_Left = shooterMotorLeft.getPIDController();

  // Configure the button bindings

  shooterRightEnc = shooterMotorRight.getEncoder();
  shooterLeftEnc = shooterMotorLeft.getEncoder();

  pdp = new PowerDistribution(0, ModuleType.kCTRE);


  

  shooterMotorLeft.setInverted(false);

  
  shooterMotorRight.setInverted(true);

  shooter = new Shooter(shooterMotorRight, shooterMotorLeft, shooterRightEnc, shooterLeftEnc, pidcontrol_shooter_Right, pidcontrol_shooter_Left);
  tilt = new Tilt(tiltMotor, tilt_limit);
  transport = new Transport(transportMotor, transport_sensor);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    shooterTeleop = new JoystickButton(joy, 1);

    shooterTeleop = new JoystickButton(joy, Constants.SHOOTER_TELEOP_BUTTON);
    tiltUp = new JoystickButton(joy, Constants.TILT_UP_BUTTON);
    tiltDown = new JoystickButton(joy, Constants.TILT_DOWN_BUTTON);
    transport_move = new JoystickButton(joy, Constants.TRANSPORT_BUTTON);


    shooterTeleop.whenPressed(new MoveShooterTeleop(.5));
    tiltUp.whenPressed(new MoveTilt(Constants.TILT_UP_SPEED));
    tiltDown.whenPressed(new MoveTilt(Constants.TILT_DOWN_SPEED));
    transport_move.whenPressed(new MoveTilt(Constants.TRANSPORT_SPEED));
    
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }

  public static Shooter getShooter(){return shooter;}
  public static Joystick getJoy(){return joy;}
  public static  Tilt getTilt() {
    return tilt;
  }
  public static Transport getTransport(){
    return transport;
  }
}
