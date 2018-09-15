package frc.team4159.robot.commands.cube;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4159.robot.OI;
import frc.team4159.robot.Robot;
import frc.team4159.robot.subsystems.CubeHolder;
import frc.team4159.robot.subsystems.Superstructure;

public class LiftCube extends Command {

    private CubeHolder cubeHolder;
    private OI oi;

    public LiftCube() {
        cubeHolder = Superstructure.getInstance().getCubeHolder();
        oi = OI.getInstance();
        requires(cubeHolder);
    }

    @Override
    protected void execute() {

        /*
         * Intake and outtake wheel logic
         */
        if(oi.intakeButton() && oi.outtakeButton()) {
            cubeHolder.stopFlywheels();

        } else if(oi.intakeButton()) {
            cubeHolder.intake();

        } else if(oi.outtakeButton()) {
            cubeHolder.outtake();

        } else {
            cubeHolder.stopFlywheels();
        }


        /*
         * Open claw if trigger is pressed. Default closes.
         */
        if(oi.openClaw()) {
            cubeHolder.open();
        } else {
            cubeHolder.close();
        }

        /* Automated arm movement */
        if (oi.setSwitchHeight()) {
            cubeHolder.setToSwitch();
        } else if(oi.setLiftTargetZero())  {
            cubeHolder.setToBottom();
        }

        cubeHolder.setRawLift(oi.getSecondaryY());
        cubeHolder.update();

        if(Robot.oi.toggleLifterRawMode()){
            cubeHolder.toggleLifterRawMode();
        }

        cubeHolder.logDashboard();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        cubeHolder.stopFlywheels();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
