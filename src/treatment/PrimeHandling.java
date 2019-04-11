package treatment;

public class PrimeHandling {

    public static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        } else if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        double max = Math.sqrt(n);

        for (int i = 5; i <= max; i += 2) {
            if (n % i == 0) return false;
        }

        return true;
    }
}
