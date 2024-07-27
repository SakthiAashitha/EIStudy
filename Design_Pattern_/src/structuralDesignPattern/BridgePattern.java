package structuralDesignPattern;


import java.util.Scanner;

// Implementor Interface
// Defines the general methods for devices that can be controlled by remotes.
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}

// Concrete Implementor - TV
// Specific implementation of a Device, representing a TV.
class TV implements Device {
    private int volume;

    @Override
    public void turnOn() {
        System.out.println("TV is now ON");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is now OFF");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("TV volume set to " + volume);
    }
}

// Concrete Implementor - Radio
// Specific implementation of a Device, representing a Radio.
class Radio implements Device {
    private int volume;

    @Override
    public void turnOn() {
        System.out.println("Radio is now ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio is now OFF");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Radio volume set to " + volume);
    }
}

// Abstraction
// Defines the interface for the remote controls, holding a reference to a Device.
abstract class RemoteControl {
    protected Device device;

    protected RemoteControl(Device device) {
        this.device = device;
    }

    abstract void turnOn();
    abstract void turnOff();
    abstract void setVolume(int volume);
}

// Refined Abstraction - BasicRemote
// A basic remote control with standard functionalities.
class BasicRemote extends RemoteControl {

    public BasicRemote(Device device) {
        super(device);
    }

    @Override
    void turnOn() {
        device.turnOn();
    }

    @Override
    void turnOff() {
        device.turnOff();
    }

    @Override
    void setVolume(int volume) {
        device.setVolume(volume);
    }
}

// Refined Abstraction - AdvancedRemote
// An advanced remote control with additional functionalities like mute.
class AdvancedRemote extends RemoteControl {

    public AdvancedRemote(Device device) {
        super(device);
    }

    @Override
    void turnOn() {
        device.turnOn();
    }

    @Override
    void turnOff() {
        device.turnOff();
    }

    @Override
    void setVolume(int volume) {
        device.setVolume(volume);
    }

    void mute() {
        System.out.println("Muting the device");
        device.setVolume(0);
    }
}

// Client Code
// Demonstrates the usage of the Bridge Pattern by allowing the user to choose a device and remote type.
public class BridgePattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Device device = null;
        RemoteControl remote = null;

        while (true) {
            // User selects the type of device to control
            System.out.println("\nSelect device type:");
            System.out.println("1. TV");
            System.out.println("2. Radio");
            System.out.println("Type 'exit' to quit.");
            String deviceChoice = scanner.nextLine();

            if (deviceChoice.equalsIgnoreCase("exit")) {
                break; // Exit the loop if user types 'exit'
            }

            // Create the corresponding device based on user input
            switch (deviceChoice) {
                case "1":
                    device = new TV();
                    break;
                case "2":
                    device = new Radio();
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    continue;
            }

            // User selects the type of remote control
            System.out.println("Select remote type:");
            System.out.println("1. Basic Remote");
            System.out.println("2. Advanced Remote");
            String remoteChoice = scanner.nextLine();

            // Create the corresponding remote based on user input
            switch (remoteChoice) {
                case "1":
                    remote = new BasicRemote(device);
                    break;
                case "2":
                    remote = new AdvancedRemote(device);
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    continue;
            }

            // User selects the operation to perform on the device
            System.out.println("Select operation:");
            System.out.println("1. Turn ON");
            System.out.println("2. Turn OFF");
            System.out.println("3. Set Volume");
            if (remote instanceof AdvancedRemote) {
                System.out.println("4. Mute");
            }
            String operation = scanner.nextLine();

            // Execute the selected operation
            switch (operation) {
                case "1":
                    remote.turnOn();
                    break;
                case "2":
                    remote.turnOff();
                    break;
                case "3":
                    System.out.print("Enter volume level: ");
                    int volume = Integer.parseInt(scanner.nextLine());
                    remote.setVolume(volume);
                    break;
                case "4":
                    if (remote instanceof AdvancedRemote) {
                        ((AdvancedRemote) remote).mute();
                    } else {
                        System.out.println("Invalid operation for Basic Remote.");
                    }
                    break;
                default:
                    System.out.println("Invalid operation. Please select 1, 2, 3, or 4.");
            }
        }

        scanner.close();
        System.out.println("Exiting the program.");
    }
}

