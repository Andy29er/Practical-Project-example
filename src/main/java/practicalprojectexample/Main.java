package practicalprojectexample;

import practicalprojectexample.utils.SessionManager;

public class Main {
    public static void main(String[] args) {
        SessionManager.getSessionFactory(); // to force an initialization


        SessionManager.shutdown();
    }
}