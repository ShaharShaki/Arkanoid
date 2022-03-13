//208524181

import java.util.List;

/**
 * @author Shahar Shaki
 * Line class create an object "Line", check it middle point, it length, start and end point.
 * also check it gradient on axes and intersections with other lines.
 */
public class Line {

    private static final double EPSILON = Math.pow(10, -2);

    //Fields
    private Point start;
    private Point end;

    /**.
     * constructor
     * @param start point
     * @param end point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**.
     * constructor
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     *
     * @return the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     *
     * @return Returns the middle point of the line
     */
    public Point middle() {
        return new Point(((this.start.getX() + this.end.getX()) / 2), ((this.start.getY() + this.end.getY()) / 2));
    }

    /**
     *
     * @return Returns the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     *
     * @return Returns the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**.
     * check if gradient is valid (so we will not dividing by zero)
     * @param lineToCheck Line.
     * @return return true if gradient valid and false otherwise.
     */
    private boolean checkUndefinedGradient(Line lineToCheck) {

        //if the line is null return false.
        if (lineToCheck == null) {
            return false;
        }
        //if the difference is 0 it means the gradient is invalid (division by 0)
        if ((lineToCheck.start.getX() - lineToCheck.end.getX() == 0)) {
            return false;
        }
        //otherwise return true, it means gradient is valid.
        return true;
    }

    /**
     * get a line and return it's gradient.
     * @param lineToCheck a line to check it gradient.
     * @return return the gradient
     */
    private double getGradient(Line lineToCheck) {
        return ((lineToCheck.start.getY() - lineToCheck.end.getY())
                / (lineToCheck.start.getX() - lineToCheck.end.getX()));
    }

    /**
     * arrrange the points in order to make it easy to check intersections.
     * @param lineToArrange a line needed to be arranged.
     * @return return the new line after arranging it for checking.
     */
    private Line arrangingLineByX(Line lineToArrange) {

        //if the line is null return null
        if (lineToArrange == null) {
            return null;
        }

        Line line = lineToArrange;

        //create temp point
        Point temp;

        /*
        we want the start point x value will be smaller than the end point x value so we switch if needed.
         */
        if (line.start.getX() >= line.end.getX()) {
            temp = line.start;
            line.start = line.end;
            line.end = temp;
        }

        //return the line after arrangement.
        return line;
    }

    /**.
     * method arrange line by its y value so the start point will get the min y value of both points
     * @param lineToArrange the line needed to be arranged.
     * @return Line after arranging or null
     */
    private Line arrangingLineByY(Line lineToArrange) {
        if (lineToArrange == null) {
            return null;
        }

        //create temp point
        Point temp;

        /*
        we want the start point y value will be smaller than the end point y value so we switch if needed.
         */
        if (lineToArrange.start.getY() > lineToArrange.end.getY()) {
            temp = lineToArrange.start;
            lineToArrange.start = lineToArrange.end;
            lineToArrange.end = temp;
        }

        //return the line after arrangement.
        return lineToArrange;
    }


    /**
     * check if 2 lines is intersecting.
     * @param other the other line.
     * @return true if intersecting and false otherwise.
     */
    public boolean isIntersecting(Line other) {

        //if other is null return false
        if (other == null) {
            return false;
        }
        double xToCheck;

        //next 2 doubles hold the gradient of each line.
        double m1;
        double m2;

        //create temp line for checking, and make changes.
        Line tempLine;

        //init l1 and l2 to the given lines.
        Line l1 = this;
        Line l2 = other;

        /*
        arranging the lines.
         */
        l1 = arrangingLineByX(l1);
        l2 = arrangingLineByX(l2);

        /*
        arrange the lines to l1 start x value will be smaller than l2 start x value.
         */
        if (l1.start.getX() >= l2.start.getX()) {
            tempLine = l1;
            l1 = l2;
            l2 = tempLine;
        }
        /*
        if both gradient valid and equal, check if the l1.end = l2.start, means the lines "touch" in that point.
         */
        if (checkUndefinedGradient(l1) && checkUndefinedGradient(l2)) {
            //hold both gradients.
            m1 = getGradient(l1);
            m2 = getGradient(l2);

            //hold the 'b' in the equation y = mx + b
            double b1 = l1.start.getY() - (m1 * l1.start.getX());
            double b2 = l2.start.getY() - (m2 * l2.start.getX());

            /*
            if both gradients are equal and there is continuity of both lines return true.
             */
            if (m1 == m2) {
                if (l1.end.equals(l2.start)) {
                    return true;
                }
                /*
                if b1=b2 check if l2 start point x value is in the range of l1 start and end points x value.
                 */
                if (b1 == b2) {
                    if (l1.start.getX() <= l2.start.getX() && l2.start.getX() <= l1.end.getX()) {
                        return true;
                    }
                    //it means the the lines has no intersection point.
                    return false;
                }
            }
            //we can get the 'x' value if we have the gradients and 'b' (x = b2-b1/m1-m2)
            xToCheck = ((b2 - b1) / (m1 - m2));

            //if the x to check is in the range of both lines, return true, otherwise return false.
            if ((((l1.start.getX() <= xToCheck) && (xToCheck <= l1.end.getX())))
                    && (((l2.start.getX() <= xToCheck) && (xToCheck <= l2.end.getX())))) {
                return true;
            }
            return false;
        }

        /*
        if only l1 is vertical to axis x.
         */
        if (!checkUndefinedGradient(l1) && checkUndefinedGradient(l2)) {
            l1 = arrangingLineByY(l1);
            double xL1 = l1.start.getX();
            double yL2 = getGradient(l2) * xL1 + l2.start.getY() - (getGradient(l2) * l2.start.getX());
            //check if y value of l2 is in the range of l1 start and end point.
            if (l1.start.getY() <= yL2 && yL2 <= l1.end.getY()) {
                return true;
            }
            return false;
        }

        /*
        if only l2 is vertical to axis x.
         */
        if (!checkUndefinedGradient(l2) && checkUndefinedGradient(l1)) {
            l2 = arrangingLineByY(l2);
            double xL2 = l2.start.getX();
            double yL1 = getGradient(l1) * xL2 + l1.start.getY() - (getGradient(l1) * l1.start.getX());
            //check if y value of l1 is in the range of l2 start and end point.
            if (l2.start.getY() <= yL1 && yL1 <= l2.end.getY()) {
                return true;
            }
            return false;
        }

        /*
        if both lines vertical to axis x than arrange it to check
         */
        l1 = arrangingLineByY(l1);
        l2 = arrangingLineByY(l2);
        if (l1.start.getY() >= l2.start.getY()) {
            tempLine = l1;
            l1 = l2;
            l2 = tempLine;
        }
        //if there is no continuity of both lines, it means there is no intersection.
        if (l1.end.getX() != l2.start.getX()) {
            return false;
        }
        //if y value of both points equal return true, otherwise false. it means the intersect in 1 point exactly.
        if (l1.end.getY() == l2.start.getY()) {
            return true;
        }

        //if l2 is a point and its y value is between l1 start and end, it means there is intersection.
        if (l2.start.getY() == l2.end.getY()) {
            if (l1.start.getY() < l2.start.getY() && l2.start.getY() < l1.end.getY()) {
                return true;
            }
        }
        if (l1.start.getY() < l2.start.getY() && l2.start.getY() < l1.end.getY()) {
            return true;
        }
        return false;
    }

    /**
     * check the intersect point of 2 lines by line equation.
     * @param other a line to check on
     * @return Returns the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {

        //if other is null return null.
        if (other == null) {
            return null;
        }
        Point intersectPoint;
        Line l1 = this;
        Line l2 = other;
        //arrange points in both lines.
        l1 = arrangingLineByX(l1);
        l2 = arrangingLineByX(l2);
        Line tempLine;

        /*
        arrange the line so l1.x value will be smaller than l2.x value.
         */
        if (l1.start.getX() >= l2.start.getX()) {
            tempLine = l1;
            l1 = l2;
            l2 = tempLine;
        }

        /*
        if both line's gradient is valid check intersection point or unified lines.
         */
        if (checkUndefinedGradient(l1) && checkUndefinedGradient(l2)) {
            //get gradient and 'b' value of both lines.
            double m1 = getGradient(l1);
            double m2 = getGradient(l2);
            double b1 = l1.start.getY() - m1 * l1.start.getX();
            double b2 = l2.start.getY() - m2 * l2.start.getX();
            //if lines are unified return null.
            if (m1 == m2 && b1 == b2) {
                //if its the same line return null.
                if (l1.equals(l2)) {
                    return null;
                }
                //if both lines units, return null
                if (l1.start.getX() < l2.start.getX() && l2.start.getX() < l1.end.getX()) {
                    return null;
                }
                if (l1.start.getX() < l2.end.getX() && l2.end.getX() < l1.end.getX()) {
                    return null;
                }
                if (l2.start.getX() < l1.end.getX() && l1.end.getX() < l2.end.getX()) {
                    return null;
                }
            }

            //if the other line is intersecting with this line, return the point, return the point, null otherwise.
            if (isIntersecting(other)) {
                if (m1 == m2) {
                    return l1.end;
                }
                //get the intersection point by the equation x = (b2-b1)/(m1-m2), y = m1*((b2-b1)/(m1-m2) + b1.
                intersectPoint = new Point((b2 - b1) / (m1 - m2), m1 * ((b2 - b1) / (m1 - m2)) + b1);
                return intersectPoint;
            }
            return null;
        }

        /*
        if l1 gradient is invalid and l2 is valid, get the gradient and the 'b' of l2 and check intersection.
         */
        if (!checkUndefinedGradient(l1) && checkUndefinedGradient(l2)) {

            //arrange l1 by y value
            l1 = arrangingLineByY(l1);

            /*
            because we arrange the line so l1 start x will be on the left,so there is only one situation that
            lines can intersect and it's only if l2 start x = l1 start x and the l2 start y is in the range
            of l1 start y and end y (lines touches)
             */
            if (l1.start.getX() == l2.start.getX()) {
                if (l1.start.getY() <= l2.start.getY() && l2.start.getY() <= l1.end.getY()) {
                    intersectPoint = new Point(l2.start.getX(), l2.start.getY());
                    return intersectPoint;
                }
            }
            return null;
        }

        /*
        if l2 gradient is invalid and l1 is valid, get the gradient and the 'b' of l1 and check intersection.
         */
        if (!checkUndefinedGradient(l2) && checkUndefinedGradient(l1)) {

            //arrange l2 by y value
            l2 = arrangingLineByY(l2);
            /*
            if the l2 start point x value is in the range of l1 x values get gradient of l1 and check intersection
             */
            if ((l1.start.getX() <= l2.start.getX()) && (l2.start.getX() <= l1.end.getX())) {
                double m1 = getGradient(l1);
                double b1 = l1.start.getY() - m1 * l1.start.getX();
                double y = m1 * l2.start.getX() + b1;
                //it means there is intersection so return the point.
                if (l2.start.getY() <= y && y <= l2.end.getY()) {
                    intersectPoint = new Point(l2.start.getX(), y);
                    return intersectPoint;
                }
                return null;
            }

        /*
        else it means both lines gradients is invalid so there is three options the line is intersecting.
         */
        } else {

            //arranging points by y value because they are vertical to axis x. start point will be smaller than end.
            l1 = arrangingLineByY(l1);
            l2 = arrangingLineByY(l2);

            /*
            arrange so l1.start.y value will be smaller than l2.start.y value.
             */
            if (l1.start.getY() >= l2.start.getY()) {
                tempLine = l1;
                l1 = l2;
                l2 = tempLine;
            }

            //if both y values on l1.end and l2.start are equal it means there is intersection point between them.
            if (l1.end.getY() == l2.start.getY()) {
                intersectPoint = new Point(l1.end.getX(), l1.end.getY());
                return intersectPoint;
            }

            /*
            if l2 is a point and y value is between l1 start y and end y, there is an intersection.
             */
            if (l2.start.getY() == l2.end.getY()) {
                if (l1.start.getY() <= l2.start.getY() && l2.start.getY() <= l1.end.getY()) {
                    intersectPoint = new Point(l2.start.getX(), l2.start.getY());
                    return intersectPoint;
                }
            }
        }

        //otherwise return null.
        return null;
    }


    /**
     * check if two line are equal.
     * @param other a line to check on.
     * @return equals -- return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        /*
        check if both lines are equals, if l1-l2 is smaller than final epsilon.
         */
        if ((Math.abs(this.start.getX() - other.start.getX()) < EPSILON
                && Math.abs(this.start.getY() - other.start.getY()) < EPSILON
                && Math.abs(this.end.getX() - other.end.getX()) < EPSILON
                && Math.abs(this.end.getY() - other.end.getY()) < EPSILON)
                || (Math.abs(this.start.getX() - other.end.getX()) < EPSILON
                        && Math.abs(this.start.getY() - other.end.getY()) < EPSILON
                        && Math.abs(this.end.getX() - other.start.getX()) < EPSILON
                        && Math.abs(this.end.getY() - other.start.getY()) < EPSILON)) {
            return true;
        }
        return false;
    }

    /**.
     * check the closest intersection points with this line and a given rectangle.
     * @param rect rectangle
     * @return null if there is no intersections or the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //if there is no points.
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }
        //only one point
        List intersectionsList = rect.intersectionPoints(this);
        if (intersectionsList.size() == 1) {
            return (Point) intersectionsList.get(0);
        }
        //if there is 2 points (the max can be), return the one with smaller distance to the start point of the line.
        if (((Point) intersectionsList.get(0)).distance(this.start)
                < ((Point) intersectionsList.get(1)).distance(this.start)) {
            return (Point) intersectionsList.get(0);
        }
        //otherwise return the second one. (the maximum intersections can be is 2).
        return (Point) intersectionsList.get(1);
    }
}



