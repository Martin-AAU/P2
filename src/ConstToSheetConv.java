import java.util.Vector;

public class ConstToSheetConv {

    public int calcLength(int x1, int y1, int x2, int y2) {
        double result = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        double maxDistance = Math.sqrt((width-0)*(width-0) + (height-0)*(height-0));

        if(result < (maxDistance * 0.2)) {
            return 1;
        } else if(result < (maxDistance * 0.4)) {
            return 2;
        } if(result < (maxDistance * 0.6)) {
            return 4;
        } if(result < (maxDistance * 0.8)) {
            return 8;
        } else {
            return 16;
        }
    }

    public int calcPlayTime(int x1, int y1, int x2, int y2, int x3, int y3) {
        Vector<Integer> v1 = new Vector<>(Math.abs(x1-x2), Math.abs(y1-y2));
        Vector<Integer> v2 = new Vector<>(Math.abs(x2-x3), Math.abs(y2-y3));

        double angle = Math.atan2(v2.get(1), v2.get(0)) - Math.atan2(v1.get(1), v1.get(0));
        if (angle < 0) angle += 2 * Math.PI;

        if(angle > Math.PI) {
            angle = (2 * Math.PI) - angle;
        }

        if(angle < (Math.PI * 0.2)) {
            return 1;
        } else if(angle < (Math.PI * 0.4)) {
            return 2;
        } if(angle < (Math.PI * 0.6)) {
            return 4;
        } if(angle < (Math.PI * 0.8)) {
            return 8;
        } else {
            return 16;
        }
    }
}
