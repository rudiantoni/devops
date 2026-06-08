import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class Main {
    static String ARG_VALUE_SEPARATOR = "=";

    public static void main(String[] args) {
        Integer size = parseArgOrDefault(args, "--size", Integer.class, 32);

        System.out.printf("Generating Base64 encoded string for a %d byte array%n", size);

        byte[] bytes = new byte[size];
        new SecureRandom().nextBytes(bytes);

        String result = Base64.getEncoder().encodeToString(bytes);
        System.out.printf("Result: %s%n", result);
    }

    public static <T> T parseArgOrDefault(
            String[] args, String argName, Class<T> returnType, T defaultValue
    ) {
        try {
            String expectedPrefix = argName + ARG_VALUE_SEPARATOR;

            String foundArgValuePair = Arrays.stream(args)
                    .filter(it -> it.startsWith(expectedPrefix))
                    .findFirst()
                    .orElse(null);

            if (foundArgValuePair != null) {
                String expectedValue = foundArgValuePair.substring(expectedPrefix.length());
                Object parsedValue;
                if (returnType == Integer.class) {
                    parsedValue = Integer.valueOf(expectedValue);
                } else {
                    throw new IllegalArgumentException(
                            "Unsupported argument type: " + returnType.getName()
                    );
                }
                return returnType.cast(parsedValue);
            }

            System.out.println("Using default value of " + defaultValue + " for arg " + argName);
            return defaultValue;
        } catch (Exception e) {
            System.out.println("Unable to parse argument " + argName);
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}

