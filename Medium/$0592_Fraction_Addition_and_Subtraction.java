package Medium;

import java.util.List;
import java.util.ArrayList;

// https://leetcode.com/problems/fraction-addition-and-subtraction/

// This is a math simulation problem. We need to parse the expression and simulate the addition of fractions.
// The key is to find the greatest common divisor of the numerator and denominator of the result fraction.
// The greatest common divisor can be found using the Euclidean algorithm.

public class $0592_Fraction_Addition_and_Subtraction {
    class Fraction {
        long numerator, denominator;
    }

    public long greatestCommonDivisor(long a, long b) {
        if (b == 0) return a;
        return greatestCommonDivisor(b, a % b);
    }

    public String fractionAddition(String expression) {
        int l = expression.length();
        char[] c = expression.toCharArray();
        List<Fraction> fractions = new ArrayList<Fraction>();

        // Parsing the expression
        for (int i = 0; i < l;) {
            Fraction f = new Fraction();
            boolean negative = c[i] == '-';
            if (c[i] == '-' || c[i] == '+') ++i;

            while (c[i] != '/') {
                f.numerator = (f.numerator * 10) + (c[i] - '0');
                ++i;
            }
            if (negative) f.numerator *= -1;
            ++i;
            while (i < l && c[i] != '+' && c[i] != '-') {
                f.denominator = (f.denominator * 10) + (c[i] - '0');
                ++i;
            }
            fractions.add(f);
        }

        // Simulate addition
        Fraction result = new Fraction();
        result.denominator = 1;
        for (Fraction f: fractions) {
            long numerator1 = result.numerator * f.denominator;
            long numerator2 = f.numerator * result.denominator;
            result.denominator = result.denominator * f.denominator;
            result.numerator = numerator1 + numerator2;
        }

        long gcd = Math.abs(greatestCommonDivisor(result.numerator, result.denominator));
        result.numerator /= gcd;
        result.denominator /= gcd;
        return String.format("%d/%d", result.numerator, result.denominator);
    }
}