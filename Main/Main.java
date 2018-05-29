package Main;

import javax.swing.*;

public class Main {
    public static int[] defaultSize = {600, 400, 100, 100};
    private static ProgramFrame frame;

    public static void main(String[] args) {
        frame = new ProgramFrame(1200, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setComponentsParent();
    }
}