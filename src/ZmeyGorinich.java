import java.util.HashMap;
import java.util.Map;

public class ZmeyGorinich extends Creature implements Attack, Healthing{
    private int level;
    private int timesRegen;
    private int valuesRegen;
    private int scoreRegen;
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    {
        this.setName("ZmeyGorinich");
        this.scoreRegen = 0;
    }

    ZmeyGorinich() {}

    ZmeyGorinich (int level) {
        if (level == 0) {
            this.setLevel(0);
            this.setHealth(150);
            this.setTimesRegen(0);
            this.setValuesRegen(0);
        }
        else if (level == 1) {
            this.setLevel(1);
            this.setHealth(300);
            this.setTimesRegen(3);
            this.setValuesRegen(30);

        } else if (level == 2) {
            this.setLevel(2);
            this.setHealth(450);
            this.setTimesRegen(3);
            this.setValuesRegen(50);
        }
    }

    private int getLevel() { return level; }

    private void setLevel(int level) {
        this.level = level;
    }

    private int getTimesRegen() {
        return timesRegen;
    }

    private void setTimesRegen(int times_regen) {
        this.timesRegen = times_regen;
    }

    private int getValuesRegen() { return valuesRegen; }

    private void setValuesRegen(int valuesRegen) { this.valuesRegen = valuesRegen; }

    private int getScoreRegen() { return scoreRegen; }

    private void setScoreRegen(int scoreRegen) { this.scoreRegen = scoreRegen; }

    @Override
    public void plusHealth(int health, int valuesRegen) {
        setHealth(health + valuesRegen);
        if (getLevel() == 0 && getHealth() > 150)
            setHealth(150);
        else if (getLevel() == 1 && getHealth() > 300)
            setHealth(300);
        else if (getLevel() == 2 && getHealth() > 450)
            setHealth(450);
        System.out.println(ANSI_YELLOW + "Змей Горыныч восстанавливает здоровье!!!"  + ANSI_RESET);
    }

    @Override
    public int hit(HashMap<String, Weapon> map, String s) {
        scoreRegen++;
        if (scoreRegen % 5 == 0 && timesRegen > 0) {
            plusHealth(getHealth(), valuesRegen);
            setTimesRegen(this.timesRegen - 1);
        }
        Weapon tmp = map.get(s);
        System.out.println("Змей Горыныч дышит огнем на героя и" +
                " наносит ему урон = " + tmp.getPower());
        return tmp.getPower();
    }
}
