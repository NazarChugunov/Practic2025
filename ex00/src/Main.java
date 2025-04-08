public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println("The following arguments are passed:");
            for (String arg : args) {
                System.out.println(arg);
            }
        } else {
            System.out.println("There are no command line arguments.");
        }
    }
}