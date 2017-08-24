package actors;

public class PointActorProtocol {

    public static class Point {

        public final double x;
        public final double y;
        public final double r;
        public final boolean result;

        public Point(double x, double y, double r, boolean result) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.result = result;
        }

        public Point(double x, double y, double r) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.result = this.isInArea(x, y, r);
        }

        private boolean isInArea (double x, double y, double r) {
            if (( (x>=0) && (y>=0) && (x*x + y*y <= r*r/4) ) ||
                    ( (x<=0) && (y>=0) && (y < 2*x + r) ) ||
                    ( (x>=0) && (x<=r) && (y>=-r/2) && (y<=0) ) ) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString () {
            return "x: " + x + " y: " + y +  " r: " + r +  " res: " + result ;
        }
    }
}