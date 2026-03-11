package project20280.exercises;

import java.util.HashMap;
import java.util.Map;


public class Wk6 {

    static long normalCalls = 0;
    static long memoCalls = 0;

    public static long fibonacci(int n) {
        normalCalls++;
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static long fibonacciMemo(int n, Map<Integer, Long> memo) {
        memoCalls++;
        if (n <= 1) {
            return (long) n;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    public static void fibnoacchiPerformance(){
        long oneMinute = 60_000_000_000L;

        int bestNaiveN = -1;
        long bestNaiveValue = -1;
        long bestNaiveCalls = 0;
        long bestNaiveTime = 0;

        for (int n = 0; n <= 10; n++) {
            normalCalls = 0;
            long start = System.nanoTime();
            long value = fibonacci(n);
            long time = System.nanoTime() - start;

            if (time < oneMinute) {
                bestNaiveN = n;
                bestNaiveValue = value;
                bestNaiveCalls = normalCalls;
                bestNaiveTime = time;
            } else {
                break;
            }
        }

        int bestMemoN = -1;
        long bestMemoValue = -1;
        long bestMemoTime = 0;
        long bestMemoCalls = 0;

        for (int n = 0; n <= 92; n++) {
            memoCalls = 0;
            Map<Integer, Long> memo = new HashMap<>();
            long start = System.nanoTime();
            long value = fibonacciMemo(n, memo);
            long time = System.nanoTime() - start;

            if (time < oneMinute) {
                bestMemoN = n;
                bestMemoValue = value;
                bestMemoTime = time;
                bestMemoCalls = memoCalls;
            } else {
                break;
            }
        }

        System.out.println("Normal recursion:");
        System.out.println("Largest n under 1 minute = " + bestNaiveN);
        System.out.println("Fibonacci number = " + bestNaiveValue);
        System.out.println("Recursive calls = " + bestNaiveCalls);
        System.out.println("Time (ns) = " + bestNaiveTime);

        System.out.println();

        System.out.println("Memoisation:");
        System.out.println("Largest n under 1 minute = " + bestMemoN);
        System.out.println("Fibonacci number = " + bestMemoValue);
        System.out.println("Function calls = " + bestMemoCalls);
        System.out.println("Time (ns) = " + bestMemoTime);
    }


    public static long tribonacci(int n){
    if (n <= 1){
        return n;
    }

    return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }

    public static int McCarthy91(int n){
        if (n > 100){
            return n - 10;
        }

        else{
            return (McCarthy91(McCarthy91(n + 11)));
        }
    }

    public static void main(String[] args) {
        //System.out.println(fibonacci(5));

        //fibnoacchiPerformance();

        //System.out.println(tribonacci(9));


        System.out.println(McCarthy91(87));
    }
}
