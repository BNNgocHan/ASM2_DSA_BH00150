import java.util.LinkedList;
import java.util.Scanner;

class MessageSystem {
    static class Message {
        private String message;

        public Message(String content) {
            this.message = content;
        }

        public String getMessage() {
            return message;
        }
    }

    static class MessQueue {
        private LinkedList<Message> messages = new LinkedList<>();

        public void enqueueMessage(Message message) {
            messages.addLast(message);
        }

        public Message dequeueMessage() {
            return messages.poll();
        }

        public boolean isEmpty() {
            return messages.isEmpty();
        }

        private int size() {
            return messages.size();
        }
        
        
    }

    static class MessStack {
        private LinkedList<Message> messages = new LinkedList<>();

        public void pushMess(Message message) {
            messages.push(message);
        }

        public Message popMess() {
            return messages.pop();
        }

        public boolean isEmpty() {
            return messages.isEmpty();
        }
    }

    public static void main(String[] args) {
        MessQueue messageQueue = new MessQueue();
        MessStack messageStack = new MessStack();

        Scanner scanner = new Scanner(System.in);
        int messageLimit = 5; // Số lượng tin nhắn giới hạn

        // Nhập tin nhắn từ người dùng và xử lý
        while (true) {
            System.out.print("Enter your message (Type 'Stop' to finish): ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("Stop")) {
                break;
            }

            processMessages(userInput, messageQueue, messageStack);
            if (messageQueue.size() == messageLimit) {
                System.out.println("Reached the maximum message limit. Exiting input.");
                break;
            }
        }

        // Hiển thị tin nhắn từ ngăn xếp
        displayMessages(messageStack);

        // Đóng Scanner sau khi sử dụng
        scanner.close();
    }

    private static void processMessages(String inputMess, MessQueue messageQueue, MessStack messageStack) {
        if (!inputMess.isEmpty() && inputMess.length() <= 250) {
            Message receivedMessage = new Message(inputMess);
            messageQueue.enqueueMessage(receivedMessage);
            messageStack.pushMess(receivedMessage);
            System.out.println("Message added successfully.");
        } else {
            System.out.println("Error: Message must have at least 1 character and not exceed 250 characters.");
        }
    }

    private static void displayMessages(MessStack messageStack) {
        System.out.println("\nDisplaying messages:");
        while (!messageStack.isEmpty()) {
            Message message = messageStack.popMess();
            System.out.println(message.getMessage());
        }
    }
}
