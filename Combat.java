import java.util.Timer;
import java.util.TimerTask;

public class Combat {

    Fighter attacker = new Fighter(), defender = new Fighter();
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

    public void showCombat() {
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                // Attacker Plane Loss
                base_damage = attackNum * attacker.getAttack() * 0.2;
                agility_mitigation = base_damage * 0.45 * (Math.min(defender.getAgility() / attacker.getAgility(), 4) -1 );
                relative_speed_bonus = base_damage * 0.65 * (Math.min(attacker.getSpeed() / defender.getSpeed(), 3.5) -1);
                absolute_speed_bonus = base_damage * 0.025 * (Math.min(attacker.getSpeed() / 100, 8));
                planes_destroyed = (base_damage - agility_mitigation + relative_speed_bonus + absolute_speed_bonus) * 0.01 / attacker.getDefence();
                attackNum -= (int) planes_destroyed;

                // Defender Plane Loss
                def_base_damage = defNum * defender.getAttack() * 0.2;
                def_agility_mitigation = base_damage * 0.45 * (Math.min(attacker.getAgility() / defender.getAgility(), 4) -1 );
                def_relative_speed_bonus = base_damage * 0.65 * (Math.min(defender.getSpeed() / attacker.getSpeed(), 3.5) -1);
                def_absolute_speed_bonus = base_damage * 0.025 * (Math.min(defender.getSpeed() / 100, 8));
                def_planes_destroyed = (def_base_damage - def_agility_mitigation + def_relative_speed_bonus + def_absolute_speed_bonus) * 0.01 / defender.getDefence();
                defNum -= (int) def_planes_destroyed;

                System.out.println("Your Plane Loss: " + Math.floor(planes_destroyed) + " ---------" + " Enemy Plane Loss: " + Math.floor(def_planes_destroyed) + "---- [" + attackNum + "] - [" + defNum + "]");
            }
        },0,1000);
    }
}
