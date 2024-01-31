package com.lawn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.logging.Logger;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LawnMower {

    protected int positionX;
    protected int positionY;

    private int orientationDegree;

    private Position2D topRightCornerPosition;
    private Position2D bottomLeftCornerPosition;

    private static final Logger LOGGER = Logger.getLogger(LawnMower.class.getName());

    @Override
    public String toString() {
        return "(" + getPositionX() + "," + getPositionY() + "," + getOrientation() + ")";
    }

    /**
     * Get the orientation of the Lawn Mower
     * Translate the orientation stores in degrees to a cardinal point
     * @return Orientation (N,S,E,W) of the Lawn Mower
     */
    public LawnMowerOrientation getOrientation() {
        return switch (getOrientationDegree()) {
            case 0 -> LawnMowerOrientation.N;
            case 90 -> LawnMowerOrientation.E;
            case 180 -> LawnMowerOrientation.S;
            case 270 -> LawnMowerOrientation.W;
            default -> throw new IllegalStateException("Unexpected value: " + getOrientationDegree());
        };
    }

    /**
     * Set the orientation based on a Cardinal Point String - N,S,E,W
     * @param newOrientation - new Orientation as cardinal point
     */
    public void setOrientation(LawnMowerOrientation newOrientation) {
        switch (newOrientation) {
            case N -> setOrientationDegree(0);
            case E -> setOrientationDegree(90);
            case S -> setOrientationDegree(180);
            case W -> setOrientationDegree(270);
        }
    }

    /**
     * Execute an instruction
     * When the Lawn Mower move it can either change direction or move forward
     * @param instruction - instruction provided
     */
    public void move(LawnMowerInstruction instruction) {
        LOGGER.info("Instruction received : " + instruction);
        if (instruction == LawnMowerInstruction.A) {
            updatePosition();
        } else {
            updateOrientation(instruction);
        }
        LOGGER.info("Moved to : " + this);
    }

    /**
     * Update the orientation based on the instruction received
     * The orientation is stored as degrees with a modulo 360 and is normalized so
     * that it is always positive
     * @param instruction - instruction provided
     */
    private void updateOrientation(LawnMowerInstruction instruction) {
        int newOrientation = switch (instruction) {
            case D -> (getOrientationDegree() + 90) % 360;
            case G -> (getOrientationDegree() - 90) % 360;
            case default -> getOrientationDegree();
        };
        if (newOrientation < 0) {
            newOrientation += 360;
        }
        setOrientationDegree(newOrientation);
    }

    /**
     * Update the position of the Lawn Mower according to its current orientation
     */
    private void updatePosition() {
        switch (getOrientationDegree()) {
            case 0 -> setPositionY(getPositionY() == getTopRightCornerPosition().getPositionY() ? getPositionY() : getPositionY() + 1);
            case 90 -> setPositionX(getPositionX() == getTopRightCornerPosition().getPositionX() ? getPositionX() : getPositionX() + 1);
            case 180 -> setPositionY(getPositionY() == getBottomLeftCornerPosition().getPositionY() ? getPositionY() : getPositionY() - 1);
            case 270 -> setPositionX(getPositionX() == getBottomLeftCornerPosition().getPositionX() ? getPositionX() : getPositionX() - 1);
        }
    }


}
