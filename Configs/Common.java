package Configs;

import Configs.Numerics.Settings;

public class Common {
    public static boolean RESIZABLE = false;
    public static String INPUTERRORMSG = "올바르지 않은 입력 방식입니다.\n루트노드는 하나이어야 합니다.\n또한 각 노드는 단계를 건너뛸 수 없으며, 각 단계는 tab으로 구분됩니다.";
    public static String OVERCHILDNUMMSG = "올바르지 않은 입력입니다.\n현재 한 노드 당 허용된 자식의 갯수는 '" + Settings.CHILDRENNUM+ "개' 입니다.\nConfigs.Numerics.Settings을 통해 CHILDRENNUM값을 변경해주세요.";
}
