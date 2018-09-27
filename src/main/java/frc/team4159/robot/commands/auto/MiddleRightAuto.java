package frc.team4159.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4159.robot.commands.cube.LiftUp;
import frc.team4159.robot.commands.cube.OuttakeWheels;
import frc.team4159.robot.commands.cube.ResetLiftTopPosition;
import frc.team4159.robot.commands.cube.RunLift;
import frc.team4159.robot.commands.drive.RunMotionProfile;
import frc.team4159.robot.commands.drive.RunMotionProfileReverse;
import frc.team4159.robot.commands.led.SolidLED;

import static frc.team4159.robot.util.TrajectoryCSV.MID_TO_RIGHT_L;
import static frc.team4159.robot.util.TrajectoryCSV.MID_TO_RIGHT_R;

public class MiddleRightAuto extends CommandGroup {

    public MiddleRightAuto() {
        addParallel(new SolidLED());
        addParallel(new RunLift());
        addSequential(new ResetLiftTopPosition());
        addSequential(new LiftUp());
        addSequential(new RunMotionProfile(MID_TO_RIGHT_L, MID_TO_RIGHT_R));
        addSequential(new OuttakeWheels(1));
    }
}
