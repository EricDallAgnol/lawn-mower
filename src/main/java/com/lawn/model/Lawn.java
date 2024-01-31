package com.lawn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lawn {

    protected Position2D topRightCornerPosition;
    // The left bottom corner is assumed to be set to (0,0) by default
    protected Position2D bottomLeftCornerPosition = new Position2D(0,0);
}
