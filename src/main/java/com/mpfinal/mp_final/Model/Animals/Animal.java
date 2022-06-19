package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import com.mpfinal.mp_final.Model.CustomModelExceptions.ObjectNotFoundException;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class Animal extends ExtensionManager implements Serializable {

    private String name;
    private String race;
    private String IDChip;
    private Client client;
    private MedicalCard medicalCard;

    /**
     * Base constructor.
     * @param name String
     * @param race String
     */
    public Animal(String name, String race)  {
        super();
        setName(name);
        setRace(race);
    }

    /**
     * Constructor used when IDChip is provided.
     * @param name String
     * @param race String
     * @param IDChip String
     */
    public Animal(String name, String race, String IDChip)  {
        this(name, race);
        try {
            setIDChip(IDChip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //region Static methods
        /**
         * Finds an Animal with given IDChip.
         * @param idChipAnimalSearch String
         * {@link #isIDChipExists(String IDChip)}
         * @return Animal
         * @throws ObjectNotFoundException when id is not present
         * @throws ClassNotFoundException when the animal extension is not created
         */
        public static Animal getAnimalByChipID(String idChipAnimalSearch) throws ObjectNotFoundException, ClassNotFoundException {
            Animal searchedAnimal;
            //Check if IDChip exists
            if(!isIDChipExists(idChipAnimalSearch)){
                throw new ObjectNotFoundException("Animal with provided IDCHip does not exists");
            }
            //Search for animal

                searchedAnimal = getExtent(Animal.class)
                        .stream()
                        .filter(e -> e.getIDChip().equals(idChipAnimalSearch))
                        .findFirst().get();

            return searchedAnimal;
        }


        /**
         * Verifies if provided IDChip is unique.
         * @param IDChip String
         * @return true if no same id is found or when no Animal class extension were created
         */
        private static boolean isIDChipExists(String IDChip){

            boolean isExist = false;
            try {
                isExist = Stream.concat(
                                  getExtent(com.mpfinal.mp_final.Model.Animals.Cat.class).stream()
                                , getExtent(com.mpfinal.mp_final.Model.Animals.Dog.class).stream()
                        )
                        .anyMatch(e -> e.getIDChip() != null && e.getIDChip().equals(IDChip));
            } catch (ClassNotFoundException e) {
                return isExist;
            }
            return isExist;
        }
        //endregion Static Methods

    //region Getters and Setters

        public String getName() {
            return name;
        }

        /**
         * Sets the name of animal.
         * @param name String
         */
        public void setName(String name) {
            this.name = name;
        }

        public String getRace() {
            return race;
        }

        /**
         * Sets the race of animal.
         * @param race String
         */
        public void setRace(String race) {
            this.race = race;
        }

        public String getIDChip() {
            return IDChip;
        }

        /**
         * Assign value to IDChip if it's unique.
         * @param IDChip String
         * {@link #isIDChipExists(String IDChip)}
         * @throws Exception when the IDChip already exists.
         */
        public void setIDChip(String IDChip) throws Exception {
            if (IDChip != null && isIDChipExists(IDChip)) {
                throw new Exception("This IDChip already exist!");
            }else {
                this.IDChip = IDChip;
            }
        }

        /**
         * Returns assigned medical card.
         * @return MedicalCard
         * @throws ObjectNotFoundException when no medical card is assigned
         */
        public MedicalCard getMedicalCard() throws ObjectNotFoundException {
            if(medicalCard != null) {
                return medicalCard;
            }else {
                throw new ObjectNotFoundException("No medical card assigned to this animal!");
            }
        }

    //endregion Getters and Setters

    //region Association Client
        /**
         * Adds client and creates connection if client is null.
         * If not - removes existing connection and create a new one.
         * (recursive call after setting this.client = null)
         * @param client Client
         * {@link #removeClient(Client)}
         **/
        public void addClient(Client client){
            if(client != null ){
                if(this.client == null) {
                    this.client = client;
                    client.addAnimal(this);
                }else if(this.client.getId() != client.getId()) {
                    removeClient(this.client);
                    addClient(client);
                    client.addAnimal(this);
                }
            }
        }

        /**
         * Removes client and connection between.
         * @param client Client
         **/
        public void removeClient(Client client){
            if(client != null && this.client != null && this.client.getId() == client.getId()){
                this.client = null;
                client.removeAnimal(this);
            }
        }
    //endregion Association Client

    //region Association MedicalCard

        /**
         * Adds medicalCard and creates connection between.
         * @param medicalCard MedicalCard
         */
        public void addMedicalCard(MedicalCard medicalCard){
            if(this.medicalCard == null && medicalCard != null){
                this.medicalCard = medicalCard;
            }
        }

        /**
         * Removes medicalCard and connection between.
         * @param medicalCard MedicalCard
         */
        public void removeMedicalCard(MedicalCard medicalCard){
            if(medicalCard != null
                    && this.medicalCard != null
                    && this.medicalCard.getId() == medicalCard.getId()){
                this.medicalCard = null;
                medicalCard.removeAnimal(this);
            }
        }

    //endregion Association MedicalCard


}
