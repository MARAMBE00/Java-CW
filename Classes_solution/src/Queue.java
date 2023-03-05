public class Queue {
    int size = 6; // Size of Circular Queue
    int frontPassenger, rearPassenger;
    Passenger[] waitingList = new Passenger[size];

    Queue() {
        frontPassenger = -1;
        rearPassenger = -1;
    }

    // Check if the queue is full
    boolean isFullOrNot() {
        if (frontPassenger == 0 && rearPassenger == size - 1) {
            return true;
        }
        if (frontPassenger == rearPassenger + 1) {
            return true;
        }
        return false;
    }

    // Check if the queue is empty
    boolean isEmpty() {
        if (frontPassenger == -1)
            return true;
        else
            return false;
    }

    // Adding an element
    void enQueue(Passenger element) {
        if (isFullOrNot()) {
            System.out.println("Queue is full");
        } else {
            if (frontPassenger == -1)
                frontPassenger = 0;
            rearPassenger = (rearPassenger + 1) % size;
            waitingList[rearPassenger] = element;
            System.out.println("Inserted " + element);
        }
    }

    // Removing an element
    Passenger deQueue() {
        Passenger element = new Passenger();
        element = waitingList[frontPassenger];
        if (frontPassenger == rearPassenger) {
            frontPassenger = -1;
            rearPassenger = -1;
        }
        else {
            frontPassenger = (frontPassenger + 1) % size;
        }
        return (element);
    }

    public void show(){
        for (int i = 0; i < waitingList.length; i++) {
            if (waitingList[i] != null) {
                System.out.println(waitingList[i].getFirstName());
            }
        }
    }
}