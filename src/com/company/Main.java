package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input LRUList len");
        int len = input.nextInt();
        input.nextLine();

        LRUList<String> stringLRUList = new LRUList<>(len);

        TestName names = new TestName();
        System.out.println(helpNode());
        for (; ; ) {
            String command = input.nextLine();
            switch (command) {
                case "over":
                    return;

                case "addN":
                    System.out.println("Input a round time");
                    int round = input.nextInt();
                    input.nextLine();
                    for(int  i = 0; i < round ; i++) {
                        stringLRUList.add(names.getName(i%2 == 1));
                    }
                    System.out.println(stringLRUList);
                    break;

                case "seeN":
                    if(stringLRUList.len() == 0) {
                        System.out.println("Empty List");
                    }
                    else {
                        System.out.println(stringLRUList);
                    }
                    break;

                case "getN":
                    System.out.println("Input a index");
                    int index = input.nextInt();
                    if(stringLRUList.len() == 0) {
                        System.out.println("Empty List");
                    }
                    else if(index > stringLRUList.size()) {
                        System.out.println("Out of list");
                    }
                    else {
                        System.out.println(stringLRUList.get(index));
                        input.nextLine();
                    }
                    break;

                case "help" :
                    System.out.println(helpNode());
                    break;

                default:
                    System.out.println("Wrong Command");
                    break;
            }
        }

    }

    public static String helpNode() {
        return "Executable Commands：\n" +
                "addN: Add a Random name , nextLine must input a round time\n" +
                "seeN: See all names which in the LRUList\n" +
                "getN: Get a name in the LRUList , nextLine must input a index\n" +
                "help: Show this tip\n" +
                "over : Out Program";
    }
}
