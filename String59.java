/*Дана строка, содержащая полное имя файла, то есть имя диска,
список каталогов (путь), собственно имя и расширение. Выделить из этой
строки расширение файла (без предшествующей точки) */

public class String59 {
    public static void main(String66[] args) {
        String66 path = "C:\\Users\\Name\\file.txt";

        int dotIndex = path.lastIndexOf(".");

        if (dotIndex != -1 && dotIndex < path.length() - 1) {
            String66 ext = path.substring(dotIndex + 1);
            System.out.println(ext); 
        } else {
            System.out.println("Расширение не найдено");
        }
    }
}