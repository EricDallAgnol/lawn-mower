# Lawn Mower Simulation

_Small SpringBoot project about a lawn mower simulation_

### Context :

The company MowItNow has decided to develop an automatic lawn mower, intended for rectangular surfaces.

The mower can be programmed to traverse the entire surface. The position of the mower is represented by a combination of coordinates (x,y) and a letter indicating the orientation according to the English cardinal notation (N,E,W,S). The lawn is divided into a grid to simplify navigation.

For example, the position of the mower can be "0, 0, N", which means it is located in the bottom left corner of the lawn, facing North. To control the mower, a simple sequence of letters is sent to it. The possible letters are "D", "G", and "A". "D" and "G" rotate the mower 90° to the right or left respectively, without moving it. "A" means to move the mower one square in the direction it is facing, without changing its orientation.

If the position after movement is outside the lawn, the mower does not move, retains its orientation, and processes the next command. It is assumed that the square directly north of position (x, y) has coordinates (x, y+1).


### Inputs :

To program the mower, an input file is provided as follows:

- The first line corresponds to the coordinates of the top right corner of the lawn, with the bottom left corner assumed to be (0,0).
- The rest of the file allows controlling all the mowers that have been deployed. Each mower has two lines:
  - The first line gives the initial position of the mower, as well as its orientation. The position and orientation are provided in the form of 2 digits and a letter, separated by a space.
  - The second line is a series of instructions ordering the mower to explore the lawn. The instructions are a sequence of characters without spaces. Each mower moves sequentially, meaning the second mower only moves when the first one has fully executed its series of instructions.
- When a mower completes a series of instructions, it communicates its position and orientation.

### Objective :

Design and write a program in Java. This program should implement the specification above and pass the following test.

### Test :

- The following file is provided as input: "5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA"
- The expected output (final position of the mowers) is: 1 3 N 5 1 E 
>Note: The input data is injected in the form of a file.