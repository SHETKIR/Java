/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;

import java.util.Scanner;
/**
 *
 * @author andrey
 */
public class Useoperation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        int num = scanner.nextInt();
        
        int result = Operation.square(num);
        System.out.println("Квадрат числа " + num + " равен " + result);
    }
}
