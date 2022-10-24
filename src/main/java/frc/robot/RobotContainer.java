// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.auton.TwoBallAuton;
import frc.robot.commands.auton.UnbelievablyScuffedAuto;
import frc.robot.commands.basic.DriveWithJoystick;
import frc.robot.commands.basic.MoveArm;
import frc.robot.commands.basic.MoveElevator;
import frc.robot.commands.basic.MoveIntake;
import frc.robot.commands.basic.MoveLeftArm;
import frc.robot.commands.basic.MoveRightArm;
import frc.robot.commands.basic.MoveShooterTeleop;
import frc.robot.commands.basic.MoveTilt;
import frc.robot.commands.basic.MoveTransport;
import frc.robot.commands.basic.TeleArmTilt;
import frc.robot.commands.complex.AutoShoot;
import frc.robot.commands.complex.TargetPID;
import frc.robot.subsystems.ArmTilt;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TeleArmL;
import frc.robot.subsystems.TeleArmR;
import frc.robot.subsystems.Tilt;
import frc.robot.subsystems.Transport;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // Joystick 1
  private static Joystick joy;
  private static Button intakeButton;
  private static Button tiltToggleButton;
  private static Button shooterButton;
  private static Button manualShoot;
  private static Button visionTurn;

  private static Button elevator_down;
  private static Button elevator_up;
  private static Button transport_move;
  private static Button transport_back;
  private static Button reverse_intake;


  // Joystick 2
  private static Joystick joy2;
  
  // private static Button transport_move_2;
  private static Button tilt_in_button;
  private static Button arm_extend_down_2;
  private static Button arm_extend_up_2;  
  private static Button arm_tilt_in_2;
  private static Button manual_extend_up_right_2;
  private static Button manual_extend_up_left_2;
  private static Button manual_extend_down_right_2;
  private static Button manual_extend_down_left_2;

  // Motor Controllers
  public static CANSparkMax shooterMotorRight;
  public static CANSparkMax shooterMotorLeft;
  public static CANSparkMax topRight, topLeft, bottomRight, bottomLeft;
  public static RelativeEncoder topLeftEnc, topRightEnc, bottomRightEnc, bottomLeftEnc;
  public static MotorControllerGroup left, right;
  public static MotorController intakeMotor;
  public static VictorSPX transportMotor;
  public static MotorController elevatorMotor;
  public static MotorController armLeftMotor;
  public static MotorController armRightMotor;
  public static DifferentialDrive drive;

  //Pneumatics
  public static Compressor compressor;
  public static Solenoid tilt_right_piston;
  public static Solenoid tilt_left_piston;
  public static Solenoid arm_right_piston;
  public static Solenoid arm_left_piston;


  //Sensors
  private static RelativeEncoder shooterRightEnc;
  private static RelativeEncoder shooterLeftEnc;
  private static AnalogInput transport_sensor;
  private static DigitalInput armRightLimit;
  private static DigitalInput armLeftLimit;
  private static DigitalInput elevatorLimit;
  private static Encoder elevatorEncoder;
  private static Encoder armleftEncoder;
  private static Encoder armRightEncoder;

  // Subsystems
  private static Shooter shooter;
  private static Tilt tilt;
  private static Transport transport;
  private static Intake intake;
  private static Elevator elevator;
  private static TeleArmL teleArmL;
  private static TeleArmR teleArmR;
  private static ArmTilt armTilt;
  private static DriveTrain driveTrain;
  private static Limelight limelight;

  // PID controllers
  private static SparkMaxPIDController pidcontrol_shooter_Right;
  private static SparkMaxPIDController pidcontrol_shooter_Left;

  public static PowerDistribution pdp;

  private static AHRS ahrs;

  private static SendableChooser<Command> autonChooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    limelight = new Limelight(NetworkTableInstance.getDefault().getTable("limelight-steelta"));

    topLeft = new CANSparkMax(Constants.TOP_LEFT_MOTOR, MotorType.kBrushless);
    topLeft.setInverted(true);
    topLeft.setIdleMode(IdleMode.kBrake);
    topRight = new CANSparkMax(Constants.TOP_RIGHT_MOTOR, MotorType.kBrushless);
    topRight.setIdleMode(IdleMode.kBrake);
    bottomLeft = new CANSparkMax(Constants.BOTTOM_LEFT_MOTOR, MotorType.kBrushless);
    bottomLeft.setInverted(true);
    bottomLeft.setIdleMode(IdleMode.kBrake);
    bottomRight = new CANSparkMax(Constants.BOTTOM_RIGHT_MOTOR, MotorType.kBrushless);
    bottomRight.setIdleMode(IdleMode.kBrake);

    left = new MotorControllerGroup(topLeft, bottomLeft);
    right = new MotorControllerGroup(topRight, bottomRight);
    drive = new DifferentialDrive(left, right);
    drive.setSafetyEnabled(false);

    topLeftEnc = topLeft.getEncoder();
    topRightEnc = topRight.getEncoder();
    
    compressor = new Compressor(Constants.COMPRESSOR_ID, PneumaticsModuleType.CTREPCM);
    compressor.enableDigital();

    tilt_left_piston = new Solenoid(Constants.COMPRESSOR_ID, PneumaticsModuleType.CTREPCM, Constants.TILT_PISTON_LEFT);
    tilt_right_piston = new Solenoid(Constants.COMPRESSOR_ID, PneumaticsModuleType.CTREPCM, Constants.TILT_PISTON_RIGHT);

    arm_left_piston = new Solenoid(Constants.COMPRESSOR_ID, PneumaticsModuleType.CTREPCM, Constants.ARM_PISTON_LEFT);
    arm_right_piston = new Solenoid(Constants.COMPRESSOR_ID, PneumaticsModuleType.CTREPCM, Constants.ARM_PISTON_RIGHT);

    transportMotor = new WPI_VictorSPX(Constants.TRANSPORT_MOTOR);
    transportMotor.configFactoryDefault();
    transportMotor.setNeutralMode(NeutralMode.Brake);
    transportMotor.setInverted(true);

    transport_sensor = new AnalogInput(Constants.TRANSPORT_SENSOR);

    intakeMotor = new WPI_VictorSPX(Constants.INTAKE_MOTOR);

    elevatorMotor = new WPI_VictorSPX(Constants.ELEVATOR_MOTOR);
    elevatorLimit = new DigitalInput(Constants.ELEVATOR_LIMIT);

    armLeftMotor = new WPI_VictorSPX(Constants.ARM_LEFT_MOTOR);
    armLeftMotor.setInverted(false);
    armRightMotor = new WPI_VictorSPX(Constants.ARM_RIGHT_MOTOR);
    armRightMotor.setInverted(true);
    
    elevatorEncoder = new Encoder(Constants.ELEVATOR_ENCODER_1, Constants.ELEVATOR_ENCODER_2);

    armleftEncoder = new Encoder(Constants.ARM_LEFT_ENCODER_1, Constants.ARM_LEFT_ENCODER_2);
    armRightEncoder = new Encoder(Constants.ARM_RIGHT_ENCODER_1, Constants.ARM_RIGHT_ENCODER_2);

    shooterMotorRight = new CANSparkMax(Constants.SHOOTER_RIGHT_MOTOR, MotorType.kBrushless);
    shooterMotorLeft = new CANSparkMax(Constants.SHOOTER_LEFT_MOTOR, MotorType.kBrushless);
    shooterMotorLeft.setInverted(true);
    shooterMotorRight.setInverted(false);

    shooterMotorLeft.follow(shooterMotorRight, true);
    pidcontrol_shooter_Right = shooterMotorRight.getPIDController();
    pidcontrol_shooter_Left = shooterMotorLeft.getPIDController();
    shooterRightEnc = shooterMotorRight.getEncoder();
    shooterLeftEnc = shooterMotorLeft.getEncoder();

    shooter = new Shooter(shooterMotorRight, shooterMotorLeft, shooterRightEnc, shooterLeftEnc, pidcontrol_shooter_Right, pidcontrol_shooter_Left);
    tilt = new Tilt(tilt_left_piston, tilt_right_piston);
    transport = new Transport(transportMotor, transport_sensor);
    intake = new Intake(intakeMotor);
    elevator = new Elevator(elevatorMotor, elevatorEncoder, elevatorLimit);
    teleArmL = new TeleArmL(armLeftMotor, armleftEncoder, armLeftLimit);
    teleArmR = new TeleArmR(armRightMotor, armRightEncoder, armRightLimit);
    armTilt = new ArmTilt(arm_left_piston, arm_right_piston);
    ahrs = new AHRS(SerialPort.Port.kMXP);

    driveTrain = new DriveTrain(left, right, drive, topLeftEnc, topRightEnc, ahrs);
    driveTrain.setDefaultCommand(new DriveWithJoystick());

    shooter.setDefaultCommand(new MoveShooterTeleop());
    
    autonChooser = new SendableChooser<Command>();

    autonChooser.addOption("2 Ball", new TwoBallAuton());
    autonChooser.addOption("1 Ball", new UnbelievablyScuffedAuto());
    // autonChooser.addOption("Pathweaver 3 Ball", ramClass.getRamCom().andThen(() -> driveTrain.setVolts(0, 0)));

    SmartDashboard.putData("Auton", autonChooser);
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // Joystick 1
    joy = new Joystick(0);

    tiltToggleButton = new JoystickButton(joy, Constants.TILT_BUTTON);
    manualShoot = new JoystickButton(joy, Constants.MANUAL_SHOOTER_BUTTON);
    intakeButton = new JoystickButton(joy, Constants.INTAKE_IN_BUTTON);
    elevator_down = new JoystickButton(joy, Constants.ELEVATOR_DOWN_BUTTON);
    elevator_up = new JoystickButton(joy, Constants.ELEVATOR_UP_BUTTON);
    transport_move = new JoystickButton(joy, Constants.TRANSPORT_MOVE_BUTTON);
    transport_back = new JoystickButton(joy, Constants.TRANSPORT_BACK_BUTTON);
    shooterButton = new JoystickButton(joy, Constants.SHOOT_BUTTON);
    reverse_intake = new JoystickButton(joy, 4);
    visionTurn = new JoystickButton(joy, Constants.VISION_TURN);

    tiltToggleButton.whenPressed(new MoveTilt());
    reverse_intake.whenPressed(new MoveIntake(-Constants.INTAKE_IN_SPEED));
    manualShoot.whenPressed(new MoveShooterTeleop());
    intakeButton.whileHeld(new MoveIntake(Constants.INTAKE_IN_SPEED));
    elevator_down.whileHeld(new MoveElevator(Constants.ELEVATOR_SPEED));
    elevator_up.whileHeld(new MoveElevator(-Constants.ELEVATOR_SPEED));
    transport_move.whileHeld(new MoveTransport(Constants.TRANSPORT_SPEED));
    transport_back.whileHeld(new MoveTransport(-.25));
    shooterButton.whenPressed(new AutoShoot(false));
    visionTurn.whenPressed(new TargetPID(true, 3, false, visionTurn));

    // Joystick 2ca
    joy2 = new Joystick(1);

    tilt_in_button = new JoystickButton(joy2, Constants.AUTO_TILT_OUT_BUTTON_2);
    arm_extend_down_2 = new JoystickButton(joy2, Constants.ARM_EXTEND_DOWN_BUTTON_2);
    arm_extend_up_2 = new JoystickButton(joy2, Constants.ARM_EXTEND_UP_BUTTON_2);
    arm_tilt_in_2 = new JoystickButton(joy2, Constants.ARM_TILT_IN_BUTTON_2);
    manual_extend_up_left_2 = new JoystickButton(joy2, Constants.MANUAL_ARM_LEFT_UP_BUTTON_2);
    manual_extend_down_left_2 = new JoystickButton(joy2, Constants.MANUAL_ARM_LEFT_DOWN_BUTTON_2);
    manual_extend_up_right_2 = new JoystickButton(joy2, Constants.MANUAL_ARM_RIGHT_UP_BUTTON_2);
    manual_extend_down_right_2 = new JoystickButton(joy2, Constants.MANUAL_ARM_RIGHT_DOWN_BUTTON_2);

    tilt_in_button.whenPressed(new MoveTilt());
    arm_extend_down_2.whileHeld(new MoveArm(Constants.ARM_SPEED));
    arm_extend_up_2.whileHeld(new MoveArm(-Constants.ARM_SPEED));
    arm_tilt_in_2.whenPressed(new TeleArmTilt());
    manual_extend_up_left_2.whileHeld(new MoveLeftArm(-Constants.ARM_SPEED));
    manual_extend_down_left_2.whileHeld(new MoveLeftArm(Constants.ARM_SPEED));
    manual_extend_up_right_2.whileHeld(new MoveRightArm(-Constants.ARM_SPEED));
    manual_extend_down_right_2.whileHeld(new MoveRightArm(Constants.ARM_SPEED));
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public static Command getAutonomousCommand() {
    return new TwoBallAuton();
  }

  public static Shooter getShooter(){return shooter;}
  public static Joystick getJoy(){return joy;}
  public static Intake getIntake() {return intake;}
  public static Tilt getTilt() {return tilt;}
  public static Transport getTransport(){return transport;}
  public static Elevator getElevator(){return elevator;}
  public static TeleArmL getTeleArmL(){return teleArmL;}
  public static TeleArmR getTeleArmR(){return teleArmR;}
  public static ArmTilt getArmTilt(){return armTilt;}
  public static DriveTrain getDriveTrain(){return driveTrain;}
  public static AHRS getAHRS(){return ahrs;}
  public static Joystick getSecondJoy(){return joy2;}
  public static Limelight getLimeLight(){return limelight;}

}

// if (codedoesnotwork)
  // run
// else (codeworkplz;)