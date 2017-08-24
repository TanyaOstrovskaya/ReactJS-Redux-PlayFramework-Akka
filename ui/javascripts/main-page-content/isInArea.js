export function isInArea(x, y, r) {
    if (( (x>=0) && (y>=0) && (x*x + y*y <= r*r/4) ) ||
        ( (x<=0) && (y>=0) && (y < 2*x + r) ) ||
        ( (x>=0) && (x<=r) && (y>=-r/2) && (y<=0) ) ) {
        return true;
    } else {
        return false;
    }
}
