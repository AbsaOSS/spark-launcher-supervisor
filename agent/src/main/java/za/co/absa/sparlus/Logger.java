package za.co.absa.sparlus;

public class Logger {
    public static void info(String msg) {
        System.out.println(prefixed("[INFO] " + msg));
    }

    public static void warn(String msg) {
        final String str = prefixed("[WARNING] " + msg);

        final StringBuilder separatorLineBuilder = new StringBuilder();
        for (int i = 0; i < str.length() + 2; i++) {
            separatorLineBuilder.append("─");
        }
        final String separatorLine = separatorLineBuilder.toString();


        System.out.println("\n"
            + "\t┌" + separatorLine + "┐\n"
            + "\t│ " + str + " │\n"
            + "\t└" + separatorLine + "┘\n"
        );
    }

    private static String prefixed(String msg) {
        return "[Spark Launcher Supervisor] " + msg;
    }
}
