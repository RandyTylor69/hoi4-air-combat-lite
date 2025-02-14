import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Combat {

    Fighter attacker, defender;
    Timer t = new Timer();


    double base_damage = 0,
            agility_mitigation = 0,
            relative_speed_bonus = 0,
            absolute_speed_bonus = 0,
            planes_destroyed = 0,

            def_base_damage = 0,
            def_agility_mitigation = 0,
            def_relative_speed_bonus = 0,
            def_absolute_speed_bonus = 0,
            def_planes_destroyed = 0;

    int attackNum = 0, defNum = 0;

    public Combat (Fighter attacker, Fighter defender, int attackNum, int defNum) {
        this.attacker = attacker;
        this.defender = defender;
        this.attackNum = attackNum;
        this.defNum = defNum;

    }
    public double detectionPhase(double enemyNum, double friendlyNum) {
        double det = 0.0, weatherPenalty = 0.0;

        if (friendlyNum >= 3000) {
            det = 0.8;
        } else {
            det += friendlyNum * (0.8/3000); // detection from num of own planes in the region
        }

        Random random = new Random(); // detection adjusted from weather penalty
        int weather = random.nextInt(4);
        if (weather == 1) {
            weatherPenalty = 0.9;
        } else if (weather == 2) {
            weatherPenalty = 0.85;
        } else if (weather == 3) {
            weatherPenalty = 0.8;
        } else {
            weatherPenalty = 0.7;
        }
        det*=weatherPenalty;

        int dayNight = random.nextInt(2); // detection adjusted from night penalty
        if (dayNight == 1) {
            det *= 0.8;
        }

        return det;
    }

    public void showCombat() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                // Phase 1: Detection
                double attackDetect = detectionPhase(defNum, attackNum);
                double defendDetect = detectionPhase(attackNum, defNum);

                double maxAttackNum = 3 * attackDetect * defNum;
                double maxDefendNum = 3 * defendDetect * attackNum;

                double actualAttackNum = Math.min(Math.floor(maxAttackNum), Math.floor(attackNum));
                double actualDefendNum = Math.min(Math.floor(maxDefendNum), Math.floor(defNum));

                // Phase 2 : Combat

                base_damage = actualAttackNum * attacker.getAttack() * 0.2;
                agility_mitigation = base_damage * 0.45 * (Math.min(defender.getAgility() / attacker.getAgility(), 4) -1 );
                relative_speed_bonus = base_damage * 0.65 * (Math.min(attacker.getSpeed() / defender.getSpeed(), 3.5) -1);
                absolute_speed_bonus = base_damage * 0.025 * (Math.min(attacker.getSpeed() / 100, 8));
                planes_destroyed = (base_damage - agility_mitigation + relative_speed_bonus + absolute_speed_bonus) * 0.01 / attacker.getDefence();
                attackNum -= (int) planes_destroyed;

                def_base_damage = actualDefendNum * defender.getAttack() * 0.2;
                def_agility_mitigation = base_damage * 0.45 * (Math.min(attacker.getAgility() / defender.getAgility(), 4) -1 );
                def_relative_speed_bonus = base_damage * 0.65 * (Math.min(defender.getSpeed() / attacker.getSpeed(), 3.5) -1);
                def_absolute_speed_bonus = base_damage * 0.025 * (Math.min(defender.getSpeed() / 100, 8));
                def_planes_destroyed = (def_base_damage - def_agility_mitigation + def_relative_speed_bonus + def_absolute_speed_bonus) * 0.01 / defender.getDefence();
                defNum -= (int) def_planes_destroyed;

                System.out.println(attacker.getName() + " [" + Math.floor(planes_destroyed) + "] -----" + defender.getName() + " [" + Math.floor(def_planes_destroyed) + "] ---- [" + attackNum + "] - [" + defNum + "]"
                + " left detection: " + Math.floor(attackDetect * 100) + " right detection: " + Math.floor(defendDetect * 100));
                System.out.println(" ");
            }
        },0,1000);
    }
}
