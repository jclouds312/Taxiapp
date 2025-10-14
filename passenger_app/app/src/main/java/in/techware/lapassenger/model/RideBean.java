package in.techware.lapassenger.model;

import java.io.Serializable;

public class RideBean implements Serializable {
    
    private String rideId;
    private String passengerId;
    private String driverId;
    private String driverName;
    private String driverPhone;
    private String driverPhoto;
    private String driverRating;
    private String vehicleNumber;
    private String vehicleModel;
    private String vehicleColor;
    private String vehicleType;
    private String pickupLocation;
    private String dropLocation;
    private double pickupLat;
    private double pickupLng;
    private double dropLat;
    private double dropLng;
    private String distance;
    private String duration;
    private String fare;
    private String baseFare;
    private String timeFare;
    private String distanceFare;
    private String waitingCharges;
    private String totalFare;
    private String paymentMethod;
    private String status; // requested, accepted, arrived, started, completed, cancelled
    private String requestTime;
    private String acceptTime;
    private String startTime;
    private String endTime;
    private String otp;
    private boolean isPaid;
    private String rating;
    private String feedback;
    
    // Driver current location for tracking
    private double driverLat;
    private double driverLng;
    
    // Constructors
    public RideBean() {
    }
    
    // Getters and Setters
    public String getRideId() {
        return rideId;
    }
    
    public void setRideId(String rideId) {
        this.rideId = rideId;
    }
    
    public String getPassengerId() {
        return passengerId;
    }
    
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }
    
    public String getDriverId() {
        return driverId;
    }
    
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public String getDriverPhone() {
        return driverPhone;
    }
    
    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
    
    public String getDriverPhoto() {
        return driverPhoto;
    }
    
    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }
    
    public String getDriverRating() {
        return driverRating;
    }
    
    public void setDriverRating(String driverRating) {
        this.driverRating = driverRating;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public String getVehicleModel() {
        return vehicleModel;
    }
    
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
    
    public String getVehicleColor() {
        return vehicleColor;
    }
    
    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }
    
    public String getVehicleType() {
        return vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public String getPickupLocation() {
        return pickupLocation;
    }
    
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }
    
    public String getDropLocation() {
        return dropLocation;
    }
    
    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }
    
    public double getPickupLat() {
        return pickupLat;
    }
    
    public void setPickupLat(double pickupLat) {
        this.pickupLat = pickupLat;
    }
    
    public double getPickupLng() {
        return pickupLng;
    }
    
    public void setPickupLng(double pickupLng) {
        this.pickupLng = pickupLng;
    }
    
    public double getDropLat() {
        return dropLat;
    }
    
    public void setDropLat(double dropLat) {
        this.dropLat = dropLat;
    }
    
    public double getDropLng() {
        return dropLng;
    }
    
    public void setDropLng(double dropLng) {
        this.dropLng = dropLng;
    }
    
    public String getDistance() {
        return distance;
    }
    
    public void setDistance(String distance) {
        this.distance = distance;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getFare() {
        return fare;
    }
    
    public void setFare(String fare) {
        this.fare = fare;
    }
    
    public String getTotalFare() {
        return totalFare;
    }
    
    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getOtp() {
        return otp;
    }
    
    public void setOtp(String otp) {
        this.otp = otp;
    }
    
    public boolean isPaid() {
        return isPaid;
    }
    
    public void setPaid(boolean paid) {
        isPaid = paid;
    }
    
    public double getDriverLat() {
        return driverLat;
    }
    
    public void setDriverLat(double driverLat) {
        this.driverLat = driverLat;
    }
    
    public double getDriverLng() {
        return driverLng;
    }
    
    public void setDriverLng(double driverLng) {
        this.driverLng = driverLng;
    }
}