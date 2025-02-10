import java.util.ArrayList;

public class Fighter {
    private double attack, agility, defence, speed, range, thrust, weight;
    private double cost;
    private int number;
    private String airframe;
    private ArrayList<Integer> weightHistory;

    public Fighter() {
        number = 0;
        attack = 0;
        agility = 0;
        defence = 0;
        speed = 0;
        range = 0;
        cost = 0.0;
        thrust = 0;
        weight = 0;
        airframe = "";
        weightHistory = new ArrayList<>();
    }

    // set airframe

    public void setAirframe(String airframe) {
        if (airframe.equals("f3 small")) {
            agility = 45;
            defence = 11;
            speed = 450;
            range = 650;
            cost = 6;
            weight = 5;
            weightHistory.add(5);
            this.airframe += "small";
        } else if (airframe.equals("f4 small")) {
            agility = 50;
            defence = 12;
            speed = 475;
            range = 750;
            cost = 7;
            weight = 6;
            weightHistory.add(6);
            this.airframe += "small";
        } else if (airframe.equals("f3 medium")) {
            agility = 35;
            defence = 20;
            speed = 375;
            range = 1100;
            cost = 10;
            weight = 12;
            weightHistory.add(12);
            this.airframe += "medium";
        } else if (airframe.equals("f4 medium")) {
            agility = 40;
            defence = 24;
            speed = 425;
            range = 1300;
            cost = 13;
            weight = 14;
            weightHistory.add(14);
            this.airframe += "medium";
        }
    }

    public void setEngine(String engine) {
        if (engine.equals("1xe3")) {
            speed *= 1.3;
            cost += 16;
            thrust = 25;
        } else if (engine.equals("1xe4")) {
            speed *= 1.45;
            cost += 18;
            thrust = 30;
        } else if (engine.equals("2xe3")) {
            speed *= 1.3;
            cost += 32;
            thrust = 38;
        } else if (engine.equals("2xe4")) {
            speed *= 1.45;
            cost += 36;
            thrust = 45;
        } else if (engine.equals("3xe3")) {
            speed *= 1.3;
            cost += 32;
            thrust = 38;
        } else if (engine.equals("3xe4")) {
            speed *= 1.45;
            cost += 54;
            thrust = 38;
        }

    }

    public void setWeapon(String weapon) {
        if (weapon.equals("4xLMG") && weight <= thrust - 2) {
            attack += 10;
            weight += 2;
            cost += 2;
            weightHistory.add(2);
        } else if (weapon.equals("4xHMG") && weight <= thrust - 5) {
            attack += 18;
            weight += 5;
            cost += 3;
            weightHistory.add(5);
        } else if (weapon.equals("2xCanon") && weight <= thrust - 6) {
            attack += 20;
            weight += 6;
            cost += 5;
            agility -= 2;
            weightHistory.add(6);

        } else {
            System.out.println("WEIGHT EXCEEDS THRUST, UNABLE TO MOUNT" + weapon);
        }
    }

    public void setArmor(String armor) {
        if (armor.equals("Armor Plate")) {
            if (airframe.contains("small") && weight <= thrust - 2) {
                defence += 4;
                range -= 65;
                weight += 2;
                cost += 1;
                weightHistory.add(2);
            } else if (airframe.contains("medium") && weight <= thrust - 6) {
                defence += 6;
                range -= 110;
                weight += 6;
                cost += 3;
                weightHistory.add(6);
            } else {
                System.out.println("WEIGHT EXCEEDS THRUST, UNABLE TO MOUNT " + armor);
            }
        }
    }

    public void setFuelTank(String tank) {
        if (tank.equals("Drop Tanks") && weight <= thrust - 3) {
            if (!airframe.contains("small")) {
                System.out.println("Drop tanks can only be put on small airframes");
            } else {
                range += 162.5;
                weight += 3;
                cost += 1;
                weightHistory.add(3);
            }
        } else if (tank.equals("Extra Fuel Tanks") && weight <= thrust - 2) {
            if (airframe.contains("small")) {
                range += 325;
                weight += 2;
                cost += 1;
                defence -= 2;
                weightHistory.add(2);
            } else if (airframe.contains("medium")) {
                range += 550;
                weight += 5;
                cost += 2;
                defence -= 6;
                weightHistory.add(5);
            }

        } else if (tank.equals("Self Sealing") && weight <= thrust - 1) {
            if (airframe.contains("small")) {
                weight += 1;
                cost += 1;
                defence += 6;
                weightHistory.add(1);
            } else if (airframe.contains("medium")) {
                weight += 1;
                cost += 1.5;
                defence += 8;
                weightHistory.add(1);
            }
        } else {
            System.out.println("WEIGHT EXCEEDS THRUST, UNABLE TO MOUNT " + tank);
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAirframe() {
        if (!this.airframe.equals("")) {
            return this.airframe;
        } else {
            return "no airframe detected";
        }
    }

    public ArrayList<Integer> getWeight() {
        return weightHistory;
    }

    public double getAttack() {
        return attack;
    }

    public double getAgility() {
        return agility;
    }

    public double getDefence() {
        return defence;
    }

    public double getSpeed() {
        return speed;
    }

    public double getRange() {
        return range;
    }

    public String toString () {
            String str = "";
            str += "Max Speed: " + speed + " km/h  " + "  Air Defence: " + defence + "\n";
            str += "Range: " + range + " km  " + "         Attack: " + attack + "\n";
            str += "Weight: " + weight + "           Agility: " + agility + "\n";
            str += "Thrust: " + thrust + "           Cost: " + cost;
            return str;
        }
}
