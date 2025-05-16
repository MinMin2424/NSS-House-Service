/*
 * Created by minmin_tranova on 16.05.2025
 */

package cz.cvut.fel.NSS_house_service.entities;

public class Room {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
