package com.mpfinal.mp_final.Model.Animals;

import com.mpfinal.mp_final.Model.External.Client;
import com.mpfinal.mp_final.Model.System.ExtensionManager;
import com.mpfinal.mp_final.Model.System.IDGenerator;
import com.mpfinal.mp_final.Model.CustomExceptions.ObjectNotFoundException;

import java.io.Serializable;

public abstract class Animal extends ExtensionManager implements Serializable {

    private String name;
    private String race;
    private String IDChip;
    private Client client;
    private MedicalCard medicalCard;

    /**
     * Base constructor.
     * @param name
     * @param race
     */
    public Animal(String name, String race)  {
        super();

        setName(name);
        setRace(race);
    }

    /**
     * Constructor used when IDChip is provided.
     * @param name
     * @param race
     * @param IDChip
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
         * Finds an Animal with given IDChip
         * @param idAnimalSearch
         * {@link #isIDChipExists(String IDChip)}
         * @return Searched animal
         * @throws ObjectNotFoundException
         */
        public static Animal getAnimalByChipID(String idAnimalSearch) throws ObjectNotFoundException {
            Animal searchedAnimal = null;
            //Check if IDChip exists
            if(!isIDChipExists(idAnimalSearch)){
                throw new ObjectNotFoundException("Animal with provided IDCHip does not exists");
            }
            //Search for animal
            try
            {
                searchedAnimal = getExtent(Animal.class)
                        .stream()
                        .filter(e -> e.getIDChip().equals(idAnimalSearch))
                        .findFirst().get();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            return searchedAnimal;
        }


        /**
         * Verifies if provided IDChip is unique
         * @param IDChip String
         * @return true if no same id is found or when no Animal class extension were created
         */
        private static boolean isIDChipExists(String IDChip){
            boolean isExist = false;
            try {
                isExist = getExtent(Animal.class)
                        .stream()
                        .anyMatch(e -> e.getIDChip().equals(IDChip));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return isExist;
        }
        //endregion Static Methods

    //region Getters and Setters

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getIDChip() {
            return IDChip;
        }

        /**
         * Assign value to IDChip if it's unique
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
         * @return
         * @throws ObjectNotFoundException
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
         * Adds client and create connection if client is null.
         * If not - removes existing connection and create a new one.
         * (recursive call after setting this.client = null)
         * @param client
         * {@link #removeClient(Client)}
         **/
        public void addClient(Client client){
            if(client != null ){
                if(this.client == null) {
                    this.client = client;
                    client.addAnimal(this);
                }else if(this.client.getClientID() != client.getClientID()) {
                    removeClient(this.client);
                    addClient(client);
                    client.addAnimal(this);
                }
            }
        }

        /**
         * Removes client and connection between.
         * @param client
         **/
        public void removeClient(Client client){
            if(client != null && this.client != null && this.client.getClientID() == client.getClientID()){
                this.client = null;
                client.removeAnimal(this);
            }
        }
    //endregion Association Client

    //region Association MedicalCard

        /**
         * Adds medicalCard and connection between.
         * @param medicalCard
         */
        public void addMedicalCard(MedicalCard medicalCard){
            if(this.medicalCard == null && medicalCard != null){
                this.medicalCard = medicalCard;
            }
        }

        /**
         * Removes medicalCard and connection between.
         * @param medicalCard
         */
        public void removeMedicalCard(MedicalCard medicalCard){
            if(medicalCard != null
                    && this.medicalCard != null
                    && this.medicalCard.getIDMedicalCard() == medicalCard.getIDMedicalCard()){
                this.medicalCard = null;
                medicalCard.removeAnimal(this);
            }
        }

    //endregion Association MedicalCard


}
