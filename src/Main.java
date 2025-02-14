public class Main {

    public static void main(String[] args) {
        // initialize friendly & hostile fighter designs
        // Only small + medium airframes available

        // Airframes: f3/f4 small, f3/f4 medium

        // Engines: 1xe3/e4, 2xe3/e4, 3xe3/e4

        // Weapons: 4xLMG/HMG, 2xCanon

        // Armor: Armor Plate

        // Fuel Tanks: Drop Tanks, Extra Fuel Tanks, Self Sealing

        Fighter spitfire = new Fighter("spitfire");
        Fighter zero = new Fighter("zero");
        Combat combat = new Combat(spitfire,zero, 1000, 1000);

        spitfire.setAirframe("f3 small");
        spitfire.setEngine("1xe3");
        spitfire.setWeapon("4xHMG");
        spitfire.setWeapon("4xHMG");
        spitfire.setWeapon("4xHMG");
        spitfire.setArmor("Armor Plate");
        spitfire.setFuelTank("Extra Fuel Tanks");
        spitfire.setFuelTank("Self Sealing");

        zero.setAirframe("f3 small");
        zero.setEngine("1xe3");
        zero.setWeapon("4xHMG");
        zero.setWeapon("4xHMG");
        zero.setWeapon("4xHMG");
        zero.setArmor("Armor Plate");
        zero.setArmor("Armor Plate");
        zero.setFuelTank("Self Sealing");

        // System.out.println(spitfire.getAirframe());
        // System.out.println(spitfire.getWeight());


        // System.out.println(spitfire);
        // System.out.println(" ");
        // System.out.println(zero);

        // commit

        combat.showCombat();
    }




}
