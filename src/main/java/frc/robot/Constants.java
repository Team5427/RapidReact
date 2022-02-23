// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int SHOOTER_TELEOP = 1;

    public static final double SHOOTER_TELEOP_SPEED = 1.0;
    public static final double ElevatorSpeed=0.6;
    public static final double Telescopic_Arm_Speed=0.8;
    //Buttons
    public static final int ELEVATOR_UP_BUTTON = 5;
    public static final int ELEVATOR_DOWN_BUTTON = 3;

    //-----------------MOTORS-------------
    public static final int SHOOTER_MOTOR_RIGHT = 2;
    public static final int SHOOTER_MOTOR_LEFT = 3;

    public static final int ELEVATOR_LEFT_MOTOR=13;
    public static final int ELEVATOR_RIGHT_MOTOR=2;

    public static final int TELESCOPIC_ARM_LEFT_MOTOR=4;
    public static final int TELESCOPIC_ARM_RIGHT_MOTOR=7;

    public static final int TELESCOPIC_ARM_UP_MOTOR=9;
    public static final int TELESCOPIC_ARM_DOWN_MOTOR=0;
    

    //-----------------PORTS-------------
    //Elevator
    public static final int ELEVATOR_LEFT_PORT1=0;
    public static final int ELEVATOR_LEFT_PORT2=1;

    public static final int ELEVATOR_RIGHT_PORT1=6;
    public static final int ELEVATOR_RIGHT_PORT2=7;

    public static final int ELEVATOR_LIMIT_LEFT=9;
    public static final int ELEVATOR_LIMIT_RIGHT=8;

    //Telescopic Arm
    //random ports
    public static final int TELESCOPIC_ARM_LEFT_PORT1=8;
    public static final int TELESCOPIC_ARM_LEFT_PORT2=6;
    public static final int TELESCOPIC_ARM_RIGHT_PORT1=2;
    public static final int TELESCOPIC_ARM_RIGHT_PORT2=5;

    public static final int TELESCOPIC_ARM_LIMIT_LEFT=3;
    public static final int TELESCOPIC_ARM_LIMIT_RIGHT=1;

}
