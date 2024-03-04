package vista;

public class Menus {
    private static int PADDING = 3;
    private static void lineSeparator(int size){
        while (size > 0) {
            System.out.printf("-");
            size--;
        }
        System.out.printf(System.lineSeparator());
    }

    private static int  separatorSizeCalculator(String[] options){
        if (options == null || options.length == 0)
            return 0;
        int size = 0;
        for (String s : options){
            size = Math.max(size, s.length());
        }
        return size;
    }

    public static void showOptions(String[] options){
        int separatorSize = Menus.separatorSizeCalculator(options) + PADDING;
        Menus.lineSeparator(separatorSize);
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + "- " + options[i]);
        }
    }

    public static void showOptionsWithTitle(String[] options, String title){
        int separatorSize = Menus.separatorSizeCalculator(options) + PADDING;
        separatorSize = Math.max(separatorSize, title.length());
        Menus.lineSeparator(separatorSize);
        Menus.showTitle(separatorSize, title);
        showOptions(options);
    }

    public static void showTitle(int totalSize, String text){
        int padding = (totalSize - text.length()) / 2;
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println(text);
    }
}
