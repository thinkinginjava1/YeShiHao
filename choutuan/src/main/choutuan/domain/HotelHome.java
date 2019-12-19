package domain;

import java.io.Serializable;
import java.util.Objects;

public class HotelHome implements Serializable {
    private Long name;//这个是home主键
    private String prices;
    private Long volume;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelHome hotelHome = (HotelHome) o;
        return Objects.equals(name, hotelHome.name) &&
                Objects.equals(prices, hotelHome.prices) &&
                Objects.equals(volume, hotelHome.volume) &&
                Objects.equals(user, hotelHome.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prices, volume, user);
    }

    @Override
    public String toString() {
        return "HotelHome{" +
                "name=" + name +
                ", prices='" + prices + '\'' +
                ", volume=" + volume +
                ", user=" + user +
                '}';
    }

    public HotelHome(Long name, String prices, Long volume) {
        this.name = name;
        this.prices = prices;
        this.volume = volume;
    }

    public HotelHome() {
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}
