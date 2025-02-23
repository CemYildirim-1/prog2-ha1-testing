package prog2.ha1.testing;

// behaviour inspired by https://www.online-calculator.com/
public class Calculator {

    private String screen = "0";

    private double latestValue;

    private String latestOperation = "";

    public String readScreen() { // was steht jetzt auf dem Bildschirm
        return screen;
    }

    public double getLatestValue() {
        return latestValue;
    }

    public String getLatestOperation() {
        return latestOperation;
    }

    public void pressDigitKey(int digit) { // also die Tasten 0-9
        if (digit > 9 || digit < 0) throw new IllegalArgumentException();

        screen = switch (screen) {
            case "0" -> "";
            case "-0" -> "-";
            default -> screen;
        };

        if (latestOperation.isEmpty()) {
            screen = screen + digit;
        } else {
            latestValue = Double.parseDouble(screen);
            screen = Integer.toString(digit);
        }
    }

    public void pressClearKey() { // die Taste CE
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }

    public void pressBinaryOperationKey(String operation) { // also die Tasten /,x,-,+
        latestOperation = operation;
    }

    public void pressUnaryOperationKey(String operation) { // also die Tasten Wurzel, %, 1/x

    }

    public void pressDotKey() { // die Komma- bzw. Punkt-Taste
        if (!screen.endsWith(".")) screen = screen + ".";
    }

    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
    } // die +/- Taste

    public void pressEqualsKey() { // die Taste =
        try {
            if (latestOperation.equals("/") && screen.equals("0")) throw new Exception("Error");
            var result = switch (latestOperation) {
                case "+" -> latestValue + Double.parseDouble(screen);
                case "-" -> latestValue - Double.parseDouble(screen);
                case "x" -> latestValue * Double.parseDouble(screen);
                case "/" -> latestValue / Double.parseDouble(screen);
                default -> throw new IllegalArgumentException();
            };
            screen = Double.toString(result);
            if (screen.endsWith(".0")) screen = screen.substring(0, screen.length() - 2);
        } catch (Exception e) {
            screen = e.getMessage();
        }
    }
}