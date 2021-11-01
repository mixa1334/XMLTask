package by.epam.xmltask.entity;

public class HotelCharacteristic {
    private int numberOFStars;
    private String name;
    private boolean TVPresence;

    public HotelCharacteristic() {

    }

    public HotelCharacteristic(int numberOFStars, String name, boolean TVPresence) {
        this.numberOFStars = numberOFStars;
        this.name = name;
        this.TVPresence = TVPresence;
    }

    public int getNumberOFStars() {
        return numberOFStars;
    }

    public void setNumberOFStars(int numberOFStars) {
        this.numberOFStars = numberOFStars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTVPresence() {
        return TVPresence;
    }

    public void setTVPresence(boolean TVPresence) {
        this.TVPresence = TVPresence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelCharacteristic that = (HotelCharacteristic) o;

        if (numberOFStars != that.numberOFStars) return false;
        if (TVPresence != that.TVPresence) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = numberOFStars;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (TVPresence ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("HotelCharacteristic{");
        sb.append("numberOFStars=").append(numberOFStars);
        sb.append(", name='").append(name).append('\'');
        sb.append(", TVPresence=").append(TVPresence);
        sb.append('}');
        return sb.toString();
    }
}
