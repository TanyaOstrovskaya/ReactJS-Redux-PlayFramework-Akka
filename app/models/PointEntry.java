package models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "POINTS")
public class PointEntry {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator="some_seq_gen_name")
    @SequenceGenerator(name="some_seq_gen_name", sequenceName="t_seq", allocationSize=1)
    private int id;

    @Column
    private double x;

    @Column
    private double y;
    @Column
    private double r;
    @Column
    private int result;

    public PointEntry () {}

    public PointEntry(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        if (this.isInArea(x, y, r)){
            this.result = 1;
        } else  {
            this.result = 0;
        }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString () {
        return Double.toString(this.x) + " " + Double.toString(this.y) + " "
                + Double.toString(this.r) + " " + Integer.toString(this.result);
    }
}
