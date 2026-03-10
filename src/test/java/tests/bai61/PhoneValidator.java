package tests.bai61;

public class PhoneValidator {
    public static boolean isValid(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (!(Character.isDigit(c) || c == '+' || c == ' ')) {
                return false;
            }
        }

        String normalized = phone.replace(" ", "");

        if (normalized.startsWith("+84")) {
            normalized = "0" + normalized.substring(3);
        } else if (!normalized.startsWith("0")) {
            return false;
        }

        if (normalized.length() != 10) {
            return false;
        }

        for (int i = 0; i < normalized.length(); i++) {
            if (!Character.isDigit(normalized.charAt(i))) {
                return false;
            }
        }

        return normalized.startsWith("03")
                || normalized.startsWith("05")
                || normalized.startsWith("07")
                || normalized.startsWith("08")
                || normalized.startsWith("09");
    }
}