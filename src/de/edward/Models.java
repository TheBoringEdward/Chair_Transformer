package de.edward;

import java.awt.*;
import java.util.Objects;

import static java.awt.Color.*;
import static java.awt.Color.PINK;

public class Models {

    // Someone will hang me for this.

    public double[][][] getPol(String model) {
        if (Objects.equals(model, "chair")){
            return chair;
        } if (Objects.equals(model, "cube")){
            return cube;
        }
        else { // Placeholder
            return chair;
        }
    }

    public Color[] getColour(String model) {
        if (Objects.equals(model, "chair")){
            return chairColour;
        } if (Objects.equals(model, "cube")){
            return cubeColour;
        }
        else { // Placeholder
            return chairColour;
        }
    }

    double[][][] cube = {
            {
                    {-1,-1,1},
                    {-1,-1,-1},
                    {1,-1,1}
            },
            {
                    {1,-1,-1},
                    {1,-1,1},
                    {-1,-1,-1}
            },
            {
                    {-1,1,-1},
                    {-1,1,1},
                    {1,1,1}
            },
            {
                    {1,1,1},
                    {1,1,-1},
                    {-1,1,-1}
            },
            {
                    {-1,1,1},
                    {-1,-1,-1},
                    {-1,-1,1}
            },
            {
                    {-1,-1,-1},
                    {-1,1,1},
                    {-1,1,-1}
            },
            {
                    {1,-1,-1},
                    {1,1,1},
                    {1,-1,1}
            },
            {
                    {1,1,1},
                    {1,-1,-1},
                    {1,1,-1}
            },
            {
                    {-1,-1,1},
                    {1,-1,1},
                    {1,1,1}
            },
            {
                    {1,1,1},
                    {-1,1,1},
                    {-1,-1,1}
            },
            {
                    {1,-1,-1},
                    {-1,-1,-1},
                    {1,1,-1}
            },
            {
                    {-1,1,-1},
                    {1,1,-1},
                    {-1,-1,-1}
            }
    };

    Color[] cubeColour = {
            BLUE,
            BLUE,
            RED,
            RED,
            GREEN,
            GREEN,
            ORANGE,
            ORANGE,
            MAGENTA,
            MAGENTA,
            YELLOW,
            YELLOW
    };

    // You really shouldn't...
    // This array saves all the polygons of the chair
    double[][][] chair = {
            {   //  Bottom
                    {3,2.5,0},
                    {3,-2.5,0},
                    {-3,-2.5,0}
            },
            {   //  Bottom
                    {-3,-2.5,0},
                    {-3,2.5,0},
                    {3,2.5,0}
            },
            {   //  Bottom Side
                    {-3,-2.5,0},
                    {3,-2.5,0},
                    {-1,-1.5,1}
            },
            {   //  Bottom Side
                    {3,2.5,0},
                    {-3,2.5,0},
                    {-1,1.5,1}
            },
            {   // Bottom Front
                    {-1,1.5,1},
                    {-1,-1.5,1},
                    {3,-2.5,0}
            },
            {   // Bottom Front
                    {3,-2.5,0},
                    {3,2.5,0},
                    {-1,1.5,1}
            },
            {   // Bottom Back
                    {-1,-1.5,1},
                    {-1,1.5,1},
                    {-3,-2.5,0}
            },
            {   // Bottom Back
                    {-3,2.5,0},
                    {-3,-2.5,0},
                    {-1,1.5,1}
            },
            {   // Bottom Side Panel
                    {3,2.5,3},
                    {-1,1.5,1},
                    {-3,2.5,4}
            },
            {   // Bottom Side Panel
                    {-1,-1.5,1},
                    {3,-2.5,3},
                    {-3,-2.5,4}
            },
            {   // Ass
                    {-1,1.5,1},
                    {-1,-1.5,1},
                    {-3,-2.5,4}
            },
            {   // Ass
                    {-3,-2.5,4},
                    {-3,2.5,4},
                    {-1,1.5,1}
            },
            {   // Dick
                    {3,2.5,3},
                    {3,-2.5,3},
                    {-1,-1.5,1}
            },
            {   // Dick
                    {-1,-1.5,1},
                    {-1,1.5,1},
                    {3,2.5,3}
            },
            {    // Surface
                    {-3,2.5,4},
                    {-3,-2.5,4},
                    {3,2.5,3}
            },
            {   // Surface
                    {3,-2.5,3},
                    {3,2.5,3},
                    {-3,-2.5,4}
            },
            {   // Upper Front
                    {-3,-2.5,4},
                    {-3,2.5,4},
                    {-4,-2.5,9}
            },
            {   // Upper Front
                    {-4,2.5,9},
                    {-4,-2.5,9},
                    {-3,2.5,4}
            },
            {   // Upper Side Panel?
                    {-4,2.5,9},
                    {-3,2.5,4},
                    {-4,1.5,5}
            },
            {   // Upper Side Panel?
                    {-3,-2.5,4},
                    {-4,-2.5,9},
                    {-4,-1.5,5}
            },
            {   // Upper Upper Back
                    {-4,-2.5,9},
                    {-4,2.5,9},
                    {-4,-1.5,5}
            },
            {   // Upper Upper Back
                    {-4,2.5,9},
                    {-4,1.5,5},
                    {-4,-1.5,5}
            },
            {   // Lower Upper Back
                    {-3,2.5,4},
                    {-3,-2.5,4},
                    {-4,-1.5,5}
            },
            {   // Lower Upper Back
                    {-4,1.5,5},
                    {-3,2.5,4},
                    {-4,-1.5,5}
            }
    };

    // My naming convention is a fucking fever-dream

    // "Texture"
    Color[] chairColour = {
            BLACK,
            BLACK,
            BLUE,
            BLUE,
            GREEN,
            GREEN,
            GREEN,
            GREEN,
            ORANGE,
            ORANGE,
            BLUE,
            BLUE,
            BLUE,
            BLUE,
            RED,
            RED,
            GREEN,
            GREEN,
            ORANGE,
            ORANGE,
            YELLOW,
            YELLOW,
            PINK,
            PINK
    };

    // 14 points
    // {x,       y,     z}
    // {towards, right, up}

    // You really shouldn't save data like this. Good thing I never intend to use this for anything else...
    // Oh yes, this array represents all the verts of the chair. Now imagine, I want to convert this to polygons...
    double[][] chairP = {
            {3,-2.5,0},
            {3,2.5,0},
            {-3,-2.5,0},
            {-3,2.5,0},
            {-1,-1.5,1},
            {-1,1.5,1},
            {3,-2.5,3},
            {3,2.5,3},
            {-3,-2.5,4},
            {-3,2.5,4},
            {-4,-1.5,5},
            {-4,1.5,5},
            {-4,-2.5,9},
            {-4,2.5,9}
    };

}
