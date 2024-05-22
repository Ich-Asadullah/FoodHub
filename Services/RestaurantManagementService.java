package Services;

import Models.Restaurant;

import java.util.ArrayList;

public class RestaurantManagementService implements Manageable<Restaurant> {
    private ArrayList<Restaurant> restaurants;

    public RestaurantManagementService() {
        restaurants = new ArrayList<>();
    }

    @Override
    public void add(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        for (Restaurant r : restaurants) {
            if (r.getId() == restaurant.getId()) {
                r.setName(restaurant.getName());
                r.setCuisine(restaurant.getCuisine());
                r.setRating(restaurant.getRating());
                r.setMenu(restaurant.getMenu());
            }
        }
    }

    @Override
    public void delete(int restaurantId) {
        restaurants.removeIf(r -> r.getId() == restaurantId);
    }

    public Restaurant getRestaurantById(int restaurantId) {
        for (Restaurant r : restaurants) {
            if (r.getId() == restaurantId) {
                return r;
            }
        }
        return null;
    }
}
