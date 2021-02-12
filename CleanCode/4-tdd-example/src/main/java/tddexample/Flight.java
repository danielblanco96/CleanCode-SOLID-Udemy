package tddexample;

import java.time.LocalDateTime;

public class Flight {
    private String reference;
    private LocalDateTime departureTime;
    private String origin;
    private String destination;

    public Flight(String reference, LocalDateTime departureTime, String origin,
            String destination) {
        this.reference = reference;
        this.departureTime = departureTime;
        this.origin = origin;
        this.destination = destination;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
        result = prime * result + ((destination == null) ? 0 : destination.hashCode());
        result = prime * result + ((origin == null) ? 0 : origin.hashCode());
        result = prime * result + ((reference == null) ? 0 : reference.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Flight other = (Flight) obj;
        if (departureTime == null) {
            if (other.departureTime != null)
                return false;
        } else if (!departureTime.equals(other.departureTime))
            return false;
        if (destination == null) {
            if (other.destination != null)
                return false;
        } else if (!destination.equals(other.destination))
            return false;
        if (origin == null) {
            if (other.origin != null)
                return false;
        } else if (!origin.equals(other.origin))
            return false;
        if (reference == null) {
            if (other.reference != null)
                return false;
        } else if (!reference.equals(other.reference))
            return false;
        return true;
    }

}
