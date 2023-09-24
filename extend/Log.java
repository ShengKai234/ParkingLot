public class Log {
    String vehicleType;
    String registrationId;
    String entryDateTime;
    String exitDateTime;
    Integer parkingFee;

    public Log(String vehicleType, String registrationId, String entryDateTime, String exitDateTime, Integer parkingFee) {
        this.vehicleType = vehicleType;
        this.registrationId = registrationId;
        this.entryDateTime = entryDateTime;
        this.exitDateTime = exitDateTime;
        this.parkingFee = parkingFee;
    }
}