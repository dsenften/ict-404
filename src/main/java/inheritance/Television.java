package inheritance;

public class Television implements ElectronicDevice {

    @Override
    public void turnOn() { }
    
    @Override
    public void turnOff() { }

    public void changeChannel(int channel) {}
    public void initializeScreen() {}
}
