package cz.cvut.fel.NSS_house_service.exceptions;

public class ApplianceNotFoundException extends RuntimeException {
  public ApplianceNotFoundException(String message) {
    super(message);
  }
}
