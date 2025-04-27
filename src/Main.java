import services.ATMService;

public class Main {
    public static void main(String[] args) {
        ATMService atmService = new ATMService();
        ATMInterface atmInterface = new ATMInterface(atmService); // interface grafica meio frutiger aero;3
        atmInterface.start(); 
    }
}