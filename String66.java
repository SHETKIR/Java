/*Дана строка-предложение. Зашифровать ее, поместив вначале все
символы, расположенные на четных позициях строки, а затем, в обратном
порядке, все символы, расположенные на нечетных позициях (например,
строка «Программа» превратится в «ргамамроП»). */


public class String66 {
    public static void main(String[] args) {
        String input = "Привет";
        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 0) {
                even.append(input.charAt(i));
            } else {
                odd.append(input.charAt(i));
            }
        }

        String result = even.toString() + odd.reverse().toString();
        System.out.println(result);
    }
}