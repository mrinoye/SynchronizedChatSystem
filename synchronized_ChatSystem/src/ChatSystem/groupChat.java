package ChatSystem;

import java.util.Scanner;

public class groupChat {
	static class Chat 
    {
        int currentTurn = 0;
        boolean[] hasSaidBye = new boolean[3];
        int participantsLeft = 3;

        public synchronized void sendMessage(String msg, int senderId) {
            while (senderId != currentTurn) {
                try {
                    wait();}
                catch (InterruptedException e) {
                    e.printStackTrace();}
            }
            System.out.println("Participant " + (senderId + 1) + ": " + msg);
            if (msg.equalsIgnoreCase("Bye")) {
                hasSaidBye[senderId] = true;
                participantsLeft--;
            }
            if (participantsLeft == 0) {
                notifyAll();
                return;
            }
            currentTurn = (currentTurn + 1) % 3;
            notifyAll();
            while (hasSaidBye[currentTurn]) {
                currentTurn = (currentTurn + 1) % 3;}
        }
    }

    static class Participant1 implements Runnable {
        Chat chat;

        public Participant1(Chat chat) {
            this.chat = chat;}

        public void run() {
            Scanner scanner = new Scanner(System.in);
            String message;
            while (true) {
                synchronized (chat) {
                    if (chat.participantsLeft == 0) {
                        break;}
                    if (chat.currentTurn == 0) {
                        System.out.print("Participant 1, enter your message: ");
                        message = scanner.nextLine();
                        chat.sendMessage(message, 0);
                        if (chat.participantsLeft == 0) {
                            break;}
                    }
                }
            }
        }
    }

    static class Participant2 implements Runnable {
        Chat chat;

        public Participant2(Chat chat) {
            this.chat = chat;}

        public void run() {
            Scanner scanner = new Scanner(System.in);
            String message;
            while (true) {
                synchronized (chat) {
                    if (chat.participantsLeft == 0) {
                        break;
                    }
                    if (chat.currentTurn == 1) {
                        System.out.print("Participant 2, enter your message: ");
                        message = scanner.nextLine();
                        chat.sendMessage(message, 1);
                        if (chat.participantsLeft == 0) {
                            break;}
                    }
                }
            }
        }
    }

    static class Participant3 implements Runnable {
        Chat chat;

        public Participant3(Chat chat) {
            this.chat = chat;}

        public void run() {
            Scanner scanner = new Scanner(System.in);
            String message;
            while (true) {
                synchronized (chat) {
                    if (chat.participantsLeft == 0) {
                        break;
                    }
                    if (chat.currentTurn == 2) {
                        System.out.print("Participant 3, enter your message: ");
                        message = scanner.nextLine();
                        chat.sendMessage(message, 2);
                        if (chat.participantsLeft == 0) {
                            break;}
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Chat chat = new Chat();
        Thread thread1 = new Thread(new Participant1(chat));
        Thread thread2 = new Thread(new Participant2(chat));
        Thread thread3 = new Thread(new Participant3(chat));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();}
        catch (InterruptedException e) {
            e.printStackTrace();
		}
    }
}
