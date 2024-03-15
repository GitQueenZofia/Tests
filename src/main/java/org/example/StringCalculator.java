package org.example;

public class StringCalculator {
    public int Calculate(String arg) throws Exception {
        if(arg.isEmpty()) return 0;

        String delimiters = ",|\n";
        String numbers = arg;


        if (arg.startsWith("//")) {
            int delimiterIndex = arg.indexOf("\n");
            if (arg.charAt(2) == '[') {
                delimiters = delimiters + "|" + arg.substring(3, delimiterIndex - 1).replaceAll("\\[|\\]", "|");
            } else {
                delimiters = String.valueOf(arg.charAt(2));
            }
            numbers = arg.substring(delimiterIndex + 1);
        }



        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < delimiters.length(); i++) {
            char currentChar = delimiters.charAt(i);
            if (currentChar == '|') {
                if (i + 1 < delimiters.length() && delimiters.charAt(i + 1) == '|') {
                    continue;
                }
            }
            resultBuilder.append(currentChar);
        }
        delimiters=resultBuilder.toString();

        String[] nums = numbers.split(delimiters);
        int sum = 0;
        StringBuilder negatives = new StringBuilder();

        for(String num: nums){
            int n = Integer.parseInt(num);
            if(n<0){
                throw new Exception("Negatives not allowed.");
            } else if(n<=1000){
                sum += n;
            }
        }
        return sum;
    }
}
