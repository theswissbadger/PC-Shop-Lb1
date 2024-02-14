package Model;

public class Schnittstelle {
    private int anzahlUsbPorts;
    private int anzahlUsbcPorts;
    private int anzahlHdmiPorts;
    private int anzahlDpPorts;
    private int anzahlRJ45Ports;

    public Schnittstelle(int anzahlUsbPorts, int anzahlUsbcPorts, int anzahlHdmiPorts, int anzahlDpPorts, int anzahlRJ45Ports) {
        this.anzahlUsbPorts = anzahlUsbPorts;
        this.anzahlUsbcPorts = anzahlUsbcPorts;
        this.anzahlHdmiPorts = anzahlHdmiPorts;
        this.anzahlDpPorts = anzahlDpPorts;
        this.anzahlRJ45Ports = anzahlRJ45Ports;
    }

    public Schnittstelle(){

    }

    public int getAnzahlUsbPorts() {
        return anzahlUsbPorts;
    }

    public void setAnzahlUsbPorts(int anzahlUsbPorts) {
        this.anzahlUsbPorts = anzahlUsbPorts;
    }

    public int getAnzahlUsbcPorts() {
        return anzahlUsbcPorts;
    }

    public void setAnzahlUsbcPorts(int anzahlUsbcPorts) {
        this.anzahlUsbcPorts = anzahlUsbcPorts;
    }

    public int getAnzahlHdmiPorts() {
        return anzahlHdmiPorts;
    }

    public void setAnzahlHdmiPorts(int anzahlHdmiPorts) {
        this.anzahlHdmiPorts = anzahlHdmiPorts;
    }

    public int getAnzahlDpPorts() {
        return anzahlDpPorts;
    }

    public void setAnzahlDpPorts(int anzahlDpPorts) {
        this.anzahlDpPorts = anzahlDpPorts;
    }

    public int getAnzahlRJ45Ports() {
        return anzahlRJ45Ports;
    }

    public void setAnzahlRJ45Ports(int anzahlRJ45Ports) {
        this.anzahlRJ45Ports = anzahlRJ45Ports;
    }
}
