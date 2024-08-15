import java.awt.Image;

public class Pet {

    private String name;
    private Image image;
    private int food;
    private int health;
    private int mood;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public Pet(String name, Image image) {
        this.name = name;
        this.image = image;
        this.food = 100;
        this.health = 100;
        this.mood = 100;
    }

    public int feed() {
        food = Math.min(food + 10, 100);
        return food;
    }

    public int heal() {
        health = 100;
        return health;
    }

    public int addMood() {
        mood = Math.min(mood + 10, 100);
        return mood;
    }

}
