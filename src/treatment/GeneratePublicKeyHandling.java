package treatment;

import java.math.BigInteger;

public class GeneratePublicKeyHandling {

    public static boolean primesTreatment(BigInteger p, BigInteger q) {

        try {
            BigInteger n = p.multiply(q);

            if (n.compareTo(BigInteger.valueOf(26)) <= 0) throw new Exception();
            else if (!p.isProbablePrime(1) || !q.isProbablePrime(1)) throw new NumberFormatException();
            return true;
        } catch (NumberFormatException e) {
            System.out.println("You entered a non-prime value, try again\n" +
                    "Suggestion: " + p.nextProbablePrime() + " and " + q.nextProbablePrime());
        } catch (Exception e) {
            System.out.println("You need p * q to be greater than 26, " + p + " * " + q + " is " + p.multiply(q));
        }

        return false;
    }

    public static boolean coprimeTreatment(BigInteger e, BigInteger pn, BigInteger n) {

        try {
            if (e.compareTo(BigInteger.ZERO) < 0) throw new UnsupportedOperationException();
            else if (e.compareTo(pn) > 0) throw new IllegalStateException();
            else if (!e.isProbablePrime(1)) throw new NumberFormatException();
            else if (pn.gcd(e).compareTo(BigInteger.ONE) != 0) throw new ArithmeticException();
            return true;
        } catch (UnsupportedOperationException f){
            System.out.println("You entered a negative number, try again");
        } catch (IllegalStateException f) {
            System.out.println("You entered a prime number bigger than Phi(n), try again\n" +
                    "Suggestion: " + n.subtract(pn).nextProbablePrime());
        } catch (NumberFormatException f) {
            System.out.println("You entered a non-prime value, try again\n" +
                    "Suggestion: " + e.nextProbablePrime());
        } catch (ArithmeticException f) {
            System.out.println("You entered a non-coprime value, try gain\n" +
                    "Suggestion: " + e.nextProbablePrime());
        }

        return false;
    }
}
