package sample;

/**
 * This class will contain information such as given name, description, and how much mana was put into it
 */
public class Spells {

    private int whiteMana = 0;
    private int blueMana = 0;
    private int blackMana = 0;
    private int redMana = 0;
    private int greenMana = 0;
    private int colorlessMana = 0;

    private String name;

    /**
     * New spell
     */
    public Spells(){}

    // SETTERS AND GETTERS //
    public void setName(String name){
        this.name = name;
    }

    public void setWhiteMana(int mana){
        this.whiteMana = mana;
    }

    public void setBlueMana(int mana){
        this.blueMana = mana;
    }

    public void setBlackMana(int mana){
        this.blackMana = mana;
    }

    public void setRedMana(int mana) {
        this.redMana = mana;
    }

    public void setGreenMana(int mana){
        this.greenMana = mana;
    }

    public void setColorlessMana(int mana) {
        this.colorlessMana = mana;
    }

    public int getWhiteMana(){
        return whiteMana;
    }

    public int getBlueMana(){
        return blueMana;
    }

    public int getBlackMana(){
        return blackMana;
    }

    public int getRedMana(){
        return redMana;
    }

    public int getGreenMana() {
        return greenMana;
    }

    public int getColorlessMana() {
        return colorlessMana;
    }

    public String toString() {
        return  "NAME: " + this.name + "\n" +
                "WHITE MANA: " + this.whiteMana + "\n" +
                "BLUE MANA: " + this.blueMana + "\n" +
                "BLACK MANA: " + this.blackMana + "\n" +
                "RED MANA: " + this.redMana + "\n" +
                "GREEN MANA: " + this.greenMana + "\n" +
                "COLORLESS MANA: " + this.colorlessMana + "\n";
    }
}
