package clipper.hotel.models;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "accommodations")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Double totalValue;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    public static Double dayPrice = 89.9;

    public Accommodation() {

    }
    public Accommodation(LocalDate checkInDate, LocalDate checkOutDate, Double totalValue, PaymentMethod paymentMethod, Guest guest) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

        this.paymentMethod = paymentMethod;
        //this.totalValue = totalValue;
        calcTotalValue();
        this.guest = guest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
        calcTotalValue();

    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate; calcTotalValue();
    }

    public Double getTotalValue() {
        return Double.parseDouble(String.format("%.2f", totalValue));
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private void calcTotalValue() {
        if (checkInDate != null && checkOutDate != null) {
            System.out.println(this);
            //long duration = Duration.between( checkInDate.atStartOfDay(), checkOutDate.atStartOfDay()).toDays();
            this.totalValue = dayPrice * calcDaysDistance(checkInDate, checkOutDate);
            System.out.println(this);
        }
    }

    public static Long calcDaysDistance(LocalDate start, LocalDate finish) {
        return Duration.between( start.atStartOfDay(), finish.atStartOfDay()).toDays();
    }

    public boolean requiredFieldsAreNotNull() {
        return checkInDate != null && checkOutDate != null && guest != null && totalValue != null && paymentMethod != null;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                 ", totalValue=" + totalValue +
                //  ", paymentMethod=" + paymentMethod +
                ", guest=" + guest +
                '}';
    }
}

