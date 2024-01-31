package com.lawn.service;

import com.lawn.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LawnMowerService {

    private static final Logger LOGGER = Logger.getLogger(LawnMowerService.class.getName());

    public int numberOfLawnMower;

    @Autowired
    Lawn lawn;

    /**
     *     Instructions are expected to be received as follows :
     * <p>
     *     1st line : Position of top right corner of the lawn
     * <p>
     *     2nd line : Initial Orientation of the first lawnmower
     *     3rd line (no space) : Instructions for the first lawnmower
     * <p>
     *     4th line : Initial Orientation of the potential second lawnmower
     *     5th line (no space) : Instructions for the potential second lawnmower
     * <p>
     *     and so on...
     *
     * @param instructions - string message as defined above
     * @return - the position of the lawn mower(s) at the end of the simulation
     * @throws RuntimeException
     */
    public String runSimulation(String instructions) throws RuntimeException {
        String[] lines = instructions.split("\\r?\\n|\\r");
        String response = "";

        configureLawn(lines[0]);

        // Count number of lines to infer number of LawnMower to handle
        if ((lines.length -1) % 2 == 0) {
            this.numberOfLawnMower = (lines.length -1) / 2;
        } else {
            throw new RuntimeException("Incorrect number of lines ! Please make sure to have 2 lines for each lawn mower");
        }
        // Can't use multithreading given that we must move each lawn mower one by one
        // Iterate for each lawn mower
        LawnMower lawnMower = new LawnMower();
        lawnMower.setTopRightCornerPosition(lawn.getTopRightCornerPosition());
        lawnMower.setBottomLeftCornerPosition(lawn.getBottomLeftCornerPosition());

        for (int indexLine = 1; indexLine < lines.length; indexLine += 2) {
            int mutableLineIndex = indexLine;
            String[] initialPositionStr = lines[mutableLineIndex].split(" ");
            lawnMower.setPositionX(Integer.parseInt(initialPositionStr[0]));
            lawnMower.setPositionY(Integer.parseInt(initialPositionStr[1]));
            lawnMower.setOrientation(LawnMowerOrientation.valueOf(initialPositionStr[2]));
            System.out.println("-----");
            LOGGER.info(" Initial Lawn Position : " + lawnMower);

            // Execute instructions
            mutableLineIndex++;
            String[] lawnInstructions = lines[mutableLineIndex].split("");
            for (String lawnInstruction : lawnInstructions)
                lawnMower.move(LawnMowerInstruction.valueOf(lawnInstruction));
            LOGGER.info("Final Position  : " + lawnMower);
            response = response.concat(lawnMower.toString() + "\n");
        }
        return response;
    }

    /**
     * Configured the corners coordinate of the Lawn used for the simulation
     * We assumed the left bottom corner is fixed
     * @param topRightCornerPosition2D - position of the top right corner as 'x y'
     */
    private void configureLawn(String topRightCornerPosition2D) {
        String[] positionStr = topRightCornerPosition2D.split(" ");
        this.lawn.setTopRightCornerPosition(new Position2D(Integer.parseInt(positionStr[0]), Integer.parseInt(positionStr[1])));
        LOGGER.info("Lawn configured !");
    }

}
