package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApartmentRental {

    private List<Apartment> apartments = new ArrayList<>();


    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }


    public List<Apartment> findApartmentByLocation(String location) {
        return apartments.stream()
                .filter(a -> a.getLocation().equals(location))
                .toList();
    }

    public boolean isThereApartmentWithBathroomType(BathRoomType bathRoomType) {
        return apartments.stream()
                .anyMatch(a -> a.getBathRoomType() == bathRoomType);
    }

    public List<Integer> findApartmentsSize() {
        return apartments.stream()
                .map(Apartment::getSize)
                .toList();
    }

    public List<Apartment> findApartmentByExtras(String... extras) {
        return apartments.stream()
                .filter(e -> e.getExtras().containsAll(Arrays.stream(extras).toList()))
                .toList();
    }
}
