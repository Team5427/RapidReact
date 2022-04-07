// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //speeds
    public static final double SHOOTER_TELEOP_SPEED = 1.0;
    
    public static final double INTAKE_IN_SPEED = 1;

    public static final double INTAKE_OUT_SPEED = -0.8;

    public static final double ELEVATOR_SPEED = 1;
    public static final double ARM_SPEED = 1;
    public static final double TRANSPORT_SPEED = .5;

    //Buttons
    public static final int INTAKE_IN_BUTTON = 2;
    public static final int TILT_BUTTON = 7;
    public static final int SHOOT_BUTTON = 1;
    public static final int VISION_TURN = 11;
    public static final int ELEVATOR_DOWN_BUTTON = 3;
    public static final int ELEVATOR_UP_BUTTON = 5;
    public static final int MANUAL_SHOOTER_BUTTON = 6;
    public static final int ARM_TILT_IN_BUTTON = 4;
    public static final int ARM_TILT_OUT_BUTTON = 12;
    public static final int TRANSPORT_MOVE_BUTTON = 10;
    public static final int TRANSPORT_BACK_BUTTON = 9;


    //Joystick 2 Buttons

    public static final int TRANSPORT_MOVE_BUTTON_2 = 2;
    public static final int AUTO_TILT_OUT_BUTTON_2 = 7;
    public static final int ARM_EXTEND_DOWN_BUTTON_2 = 3;
    public static final int ARM_EXTEND_UP_BUTTON_2 = 5;
    public static final int AUTO_ARM_OUT_BUTTON_2 = 8;
    public static final int ARM_TILT_IN_BUTTON_2 = 8;
    // public static final int ARM_TILT_OUT_BUTTON_2 = 4;
    public static final int ELEVATOR_DOWN_BUTTON_2 = 4;
    public static final int ELEVATOR_UP_BUTTON_2 = 6;
    
    public static final int MANUAL_ARM_LEFT_UP_BUTTON_2 = 10;

    public static final int MANUAL_ARM_LEFT_DOWN_BUTTON_2 = 9;

    public static final int MANUAL_ARM_RIGHT_UP_BUTTON_2 = 12;

    public static final int MANUAL_ARM_RIGHT_DOWN_BUTTON_2 = 11;

    //Motor ports
    public static final int BOTTOM_LEFT_MOTOR = 3;
    public static final int BOTTOM_RIGHT_MOTOR = 5;
    public static final int TOP_RIGHT_MOTOR = 4;
    public static final int TOP_LEFT_MOTOR = 2;

    public static final int TILT_PISTON_RIGHT = 0;
    public static final int TILT_PISTON_LEFT = 1;

    public static final int SHOOTER_RIGHT_MOTOR = 11;
    public static final int SHOOTER_LEFT_MOTOR = 10;

    public static final int ELEVATOR_MOTOR = 9;

    public static final int ARM_LEFT_MOTOR = 6;
    public static final int ARM_RIGHT_MOTOR = 7;
    public static final int ARM_PISTON_RIGHT = 3;
    public static final int ARM_PISTON_LEFT = 2;


    public static final int INTAKE_MOTOR = 13;

    public static final int TRANSPORT_MOTOR = 12;

    public static final int COMPRESSOR_ID = 14;


    //Sensor Ports
    public static final int TILT_SWITCH = 2;

    public static final double COVERED = 2.23;
    public static final int TRANSPORT_SENSOR = 1;

    public static final int ELEVATOR_ENCODER_1 = 0;
    public static final int ELEVATOR_ENCODER_2 = 6;

    public static final int ARM_LEFT_ENCODER_1 = 7;
    public static final int ARM_RIGHT_ENCODER_1 = 3;
    public static final int ARM_LEFT_ENCODER_2 = 8;
    public static final int ARM_RIGHT_ENCODER_2 = 4;
    public static final int ARM_TILT_ENCODER_1 = 1;
    public static final int ARM_TILT_ENCODER_2 = 2;

    public static final int ELEVATOR_LIMIT = 9;
    public static final int ARM_TILT_LIMIT = 0;


    public static final Port LIDAR_PORT = I2C.Port.kOnboard;
    public static final int LIDAR_ADDRESS = 0x62;


    //Constants (numbers, limits, etc.)
    public static final int LIDAR_MIN = 0;
    public static final int LIDAR_MAX = 0;
    public static final int ELEVATOR_ENCODER_LIMIT = 0;
    public static final int ARM_LEFT_ENCODER_LIMIT = 0;
    public static final int ARM_RIGHT_ENCODER_LIMIT = 0;
    public static final int ARM_TILT_ENCODER_LIMIT = 0;

    //Limelight Shi
    public static final double LL_MOUNT_ANGLE_DEG = 0;
    public static final double LL_LENS_HEIGHT_INCHES = 0;
    public static final double GOAL_HEIGHT_INCHES = 0;

    






}
