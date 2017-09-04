package models;
import javax.persistence.*;

@Entity
@Table(name = "POINTS")
public class PointEntry {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
        return Double.toString(this.x) + Double.toString(this.y)
                + Double.toString(this.r) + Integer.toString(this.result);
    }
}
