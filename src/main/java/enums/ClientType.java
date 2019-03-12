package enums;

public enum ClientType {

    NATURAL_PERSON(1, "Natural Person"),
    LEGAL_PERSON(2, "Legal Person");

      private int cod;
    private String description;

    ClientType(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static ClientType toEnum(Integer cod){
        if (cod == null){ return null; }

        for (ClientType type : ClientType.values()){
            if (cod.equals(type.getCod())){
                return type;
            }
        }

        throw new IllegalArgumentException("Invalid Id: " + cod);
    }

}
