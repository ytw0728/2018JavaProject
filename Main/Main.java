package Main;

import Configs.Common;

public class Main {
    public static int[] defaultSize = {1200, 800, 100, 100};
    private static ProgramFrame frame;

    public static void main(String[] args) {
        frame = new ProgramFrame(1350, 900);
        frame.setResizable(Common.RESIZABLE);
        frame.setVisible(true);
        frame.setComponentsParent();
    }
}