package in.techware.lataxidriver.model;

import java.util.List;

/**
 * Created by Jemsheer K D on 04 May, 2017.
 * Package in.techware.lataxidriver.model
 * Project LaTaxiDriver
 */

public class MapBean extends BaseBean {

    private List<PlaceBean> places;

    public List<PlaceBean> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceBean> places) {
        this.places = places;
    }
}
