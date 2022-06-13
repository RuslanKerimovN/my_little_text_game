import java.util.HashMap;
import java.util.Map;

public class Hero extends Creature implements Attack, Healthing {
    private int valuesRegen;

    Hero() {}

    Hero (String name) {
        this.setName(name);
        this.setHealth(100);
        this.setValuesRegen(30);
    }

    public int getValuesRegen() {
        return valuesRegen;
    }

    private void setValuesRegen(int valuesRegen) {
        this.valuesRegen = valuesRegen;
    }

    @Override
    public void plusHealth(int health, int valuesRegan) {
        setHealth(health + valuesRegan);
        if (getHealth() > 100)
            setHealth(100);
        System.out.println(getName() + " восстанавливает здоровье");
    }

    @Override
    public int hit(HashMap<String, Weapon> map, String s) {
        for (Map.Entry<String, Weapon> entry : map.entrySet()) {
            if (entry.getKey().equals(s)) {
                System.out.println(getName() + " достает " + s + " и наносит урон = "
                        + entry.getValue().getPower() + " по Змею Горынычу");
                return entry.getValue().getPower();
            }
        }
        return 0;
    }
}
