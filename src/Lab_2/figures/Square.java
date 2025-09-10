package Lab_2.figures;

import Lab_2.annotations.Repeater;

public class Square {
    int side = 0;
    int xPos = 0;
    int yPos = 0;

    public Square(int side, int xPos, int yPos) {
        this.side = side;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int calculateArea()
    {
        return side*side;
    }

    public int calculatePerimeter()
    {
        return side*4;
    }

    @Repeater(times = 3)
    protected void printCoordinates()
    {
        System.out.println("Coordinates of square x: " + xPos + ", y: " + yPos);
    }

    @Repeater(times = 1)
    protected void printPerimeterDifference(Square other)
    {
        System.out.println("Difference between Perimeters of 2 squares is " + (this.calculatePerimeter() - other.calculatePerimeter()));
    }

    @Repeater(times = 2)
    private void printPerimeterAndArea()
    {
        System.out.println("Perimeter of this square is " + this.calculatePerimeter() + " area of square is "+ this.calculateArea());
    }

    @Repeater(times = 3)
    private void displaceXPosBySide()
    {
        this.xPos += this.side;
    }
}
