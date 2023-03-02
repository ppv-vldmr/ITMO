import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Generate {
    public static void main(String[] args) {
        String outputFileName = "output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            int cnt = 0;
            for (int warehouseId = 1; warehouseId <= 3; warehouseId++) {
                for (int zoneId = 1; zoneId <= 7; zoneId++) {
                    for (int workerId = 1; workerId <= 8; workerId++) {
                        String line;

                        boolean staffflag = warehouseId == 1 && (workerId == 1 || workerId == 5);
                        staffflag |= warehouseId == 2 && (workerId == 2 || workerId == 3 || workerId == 6 || workerId == 8);
                        staffflag |= warehouseId == 3 && (workerId == 4 || workerId == 7);

                        long rangebegin = Timestamp.valueOf("2023-02-24 09:00:00").getTime();
                        long rangeend = Timestamp.valueOf("2023-02-24 19:00:00").getTime();
                        long diff = rangeend - rangebegin + 1;
                        long r = (long)(Math.random() * diff);

                        Timestamp rand = new Timestamp(rangebegin + r);
                        String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rand);
                        if (staffflag) {
                            cnt++;
                            line = String.format("%d, %d, null, '%s', %d, %d, '%s'", cnt, workerId, s, warehouseId, zoneId, "enter");
                            writer.write("(" + line + "),\n");
                            rand = new Timestamp(rangebegin + r + 300000);
                            s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rand);
                            cnt++;
                            line = String.format("%d, %d, null, '%s', %d, %d, '%s'", cnt, workerId, s, warehouseId, zoneId, "exit");
                            writer.write("(" + line + "),\n");
                        }

                        boolean outstaffflag = warehouseId == 1 && (workerId == 1 || workerId == 4);
                        outstaffflag |= warehouseId == 2 && (workerId == 2 || workerId == 5 || workerId == 8);
                        outstaffflag |= warehouseId == 3 && (workerId == 3 || workerId == 6 || workerId == 7);

                        if (outstaffflag) {
                            r = (long)(Math.random() * diff);
                            rand = new Timestamp(rangebegin + r);
                            s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rand);
                            cnt++;
                            line = String.format("%d, null, %d, '%s', %d, %d, '%s'", cnt, workerId, s, warehouseId, zoneId, "enter");
                            writer.write("(" + line + "),\n");
                            rand = new Timestamp(rangebegin + r + 300000);
                            s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(rand);
                            cnt++;
                            line = String.format("%d, null, %d, '%s', %d, %d, '%s'", cnt, workerId, s, warehouseId, zoneId, "exit");
                            writer.write("(" + line + "),\n");
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
