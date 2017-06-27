package bigint;

/**
 *
 * @author Eighteensons
 */
public class BigInt {
    // digits are stoed in reversed order.
    int[] digits;
    int size;
    
    BigInt() {
        digits = new int[0];
        size = 0;
    }
    
    BigInt(int s) {
        digits = new int[s];
        size = 0;
    }
    
    BigInt(String s) {
        size = s.length();
        digits = new int[size];
        for (int i = 0; i < size; i++) {
            int j = size - i - 1;
            digits[i] = (int)(s.charAt(j) - '0');
        }
    }
    
    public String toString() {
        String str = new String();
        for (int i = 0; i < size; i++) {
            str = Integer.toString(digits[i]) + str;
        }
        return str;
    }
    
    public BigInt add(BigInt bi) {
        int carry = 0, tmp;
        int newsize = Math.max(size, bi.size) + 1;
        BigInt result = new BigInt(newsize);
        for (int i = 0; i < newsize; i++) {
            if (i < size && i < bi.size) {
                tmp = digits[i] + bi.digits[i] + carry;
                result.digits[i] = tmp % 10;
                carry = tmp / 10;
            } else if (i < size) {
                tmp = digits[i] + carry;
                result.digits[i] = tmp % 10;
                carry = tmp / 10;
            } else if (i < bi.size) {
                tmp = bi.digits[i] + carry;
                result.digits[i] = tmp % 10;
                carry = tmp / 10;
            } else if (carry > 0) {
                result.digits[i] = carry;
            } else return result;
            result.size = i + 1;
        }
        return result;
    }
    
    public BigInt multiply(BigInt bi) {
        int carry, idx = 0, tmp;
        int newsize = size + bi.size;
        BigInt result = new BigInt(newsize);
        for (int i = 0; i < bi.size; i++) {
            carry = 0;
            for (int j = 0; j < size; j ++) {
                idx = i + j;
                tmp = result.digits[idx] + digits[j] * bi.digits[i] + carry;
                result.digits[idx] = tmp % 10;
                carry = tmp / 10;
            }
            if (carry > 0) {
                idx++;
                result.digits[idx] = carry;
            }
        }
        result.size = idx + 1;
        return result;
    }
    
    public static void main(String[] args) {
        BigInt IntA = new BigInt("13579");
        BigInt IntB = new BigInt("2468");
        System.out.println("IntA = " + IntA);
        System.out.println("IntB = " + IntB);
        System.out.println("IntA + IntB = " + IntA.add(IntB));
        System.out.println("IntA * IntB = " + IntA.multiply(IntB));
    }
}
