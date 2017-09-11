package models;

public class SendInfo {
    private String email;
    private boolean isInArea;
    private PointEntry point;
    private boolean isNew;

    public SendInfo() {}

    public SendInfo(String email, boolean isInArea, PointEntry point, boolean isNew) {
        this.email = email;
        this.isInArea = isInArea;
        this.point = point;
        this.isNew = isNew;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isInArea() {
        return isInArea;
    }

    public void setInArea(boolean inArea) {
        isInArea = inArea;
    }

    public PointEntry getPoint() {
        return point;
    }

    public void setPoint(PointEntry point) {
        this.point = point;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
