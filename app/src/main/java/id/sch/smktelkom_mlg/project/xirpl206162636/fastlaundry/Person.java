package id.sch.smktelkom_mlg.project.xirpl206162636.fastlaundry;

/**
 * Created by goldenmedal on 27/11/16.
 */

public class Person {
        //name and address string
        private String name;
        private String address;

        public Person() {
      /*Blank default constructor essential for Firebase*/
        }
        //Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }