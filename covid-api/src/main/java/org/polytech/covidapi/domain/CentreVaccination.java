package org.polytech.covidapi.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class CentreVaccination {
    @Id
    private Integer id;

    private String name;
    private String postalCode;
    private String address;
    private String city;

        public Integer getId() {
            return id;
        }
        
        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name){
            this.name = name;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode){
            this.postalCode = postalCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address){
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city){
            this.city = city;
        }
}
