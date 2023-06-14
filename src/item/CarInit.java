package item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CarInit {
    private static ArrayList<Car> mCarList;
    private static int mTotalCar = 0;

    public static void init() {
        mTotalCar = totalFileToCarList();
        mCarList = new ArrayList<Car>();
        setFileToCarList(mCarList);
    }

    public static int totalFileToCarList() {
        try {
            FileReader fr = new FileReader("Car.txt");
            BufferedReader reader = new BufferedReader(fr);
            String str;
            int num = 0;
            while ((str = reader.readLine()) != null) {
                if (str.contains("ITEM")) {
                    ++num;
                }
            }
            reader.close();
            fr.close();
            return num;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void setFileToCarList(ArrayList<Car> carList) {
        try {
            FileReader fr = new FileReader("car.txt");
            BufferedReader reader = new BufferedReader(fr);
            String str2;
            String[] readCar = new String[7];
            while ((str2 = reader.readLine()) != null) {
                if (str2.contains("ITEM")) {
                    readCar[0] = str2;
                    readCar[1] = reader.readLine();
                    readCar[2] = reader.readLine();
                    readCar[3] = reader.readLine();
                    readCar[4] = reader.readLine();
                    readCar[5] = reader.readLine();
                    readCar[6] = reader.readLine();
                }
                Car caritem = new Car(readCar[0], readCar[1], Integer.parseInt(readCar[2]), readCar[3], readCar[4], readCar[5], readCar[6]) {
                };
                carList.add(caritem);
            }
            reader.close();
            fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Car> getmCarList() {
        return mCarList;
    }

    public static void setmCarList(ArrayList<Car> mCarList) {
        CarInit.mCarList = mCarList;
    }

    public static int getmTotalCar() {
        return mTotalCar;
    }

    public static void setmTotalCar(int mTotalCar) {
        CarInit.mTotalCar = mTotalCar;
    }
}
